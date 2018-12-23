package org.intellij.lang.batch.runner;

import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.actions.RunConfigurationProducer;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.intellij.lang.batch.fileTypes.BatchFileType;

import java.io.File;

/**
 * @author wibotwi, denis.bogdanas@gmail.com
 */
public class BatchRunConfigurationProducer extends RunConfigurationProducer<BatchRunConfiguration> {

    public BatchRunConfigurationProducer() {
        super(BatchConfigurationType.getInstance());
    }

    @Override
    protected boolean setupConfigurationFromContext(BatchRunConfiguration configuration, ConfigurationContext context,
                                                    Ref<PsiElement> sourceElement) {
        try {
            //noinspection ConstantConditions
            PsiFile contextFile = context.getPsiLocation().getContainingFile();
            if (contextFile == null || !BatchFileType.DEFAULT_ASSOCIATED_EXTENSIONS
                    .contains(contextFile.getVirtualFile().getExtension())) {
                return false;
            }
            File scriptParent = new File(contextFile.getVirtualFile().getPath());
            configuration.setScriptName(scriptParent.getName());
            configuration.setWorkingDirectory(scriptParent.getParent());
        } catch (RuntimeException e) {
            return false;
        }
        configuration.setName(configuration.suggestedName());
        if (context.getModule() != null) {
            configuration.setModule(context.getModule());
        }
        return true;
    }

    @Override
    public boolean isConfigurationFromContext(BatchRunConfiguration configuration, ConfigurationContext context) {
        try {
            //noinspection ConstantConditions
            String contextPath =
                    new File(context.getPsiLocation().getContainingFile().getVirtualFile().getPath()).getPath();
            String configPath = new File(configuration.getWorkingDirectory(), configuration.getScriptName()).getPath();
            return contextPath.equals(configPath);
        } catch (RuntimeException e) {
            return false;
        }
    }
}
