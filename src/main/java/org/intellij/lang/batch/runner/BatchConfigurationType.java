package org.intellij.lang.batch.runner;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.RunConfigurationModule;
import com.intellij.openapi.project.Project;
import org.intellij.lang.batch.util.BatchIcons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author wibotwi
 */
public class BatchConfigurationType implements ConfigurationType {
    public static BatchConfigurationType getInstance() {
        ConfigurationType[] configurationTypes = CONFIGURATION_TYPE_EP.getExtensions();

        for (ConfigurationType configurationType : configurationTypes) {
            if (configurationType instanceof BatchConfigurationType) {
                return (BatchConfigurationType) configurationType;
            }
        }

        assert false;

        return null;
    }

    @NotNull
    public String getDisplayName() {
        return "Batch";
    }

    public String getConfigurationTypeDescription() {
        return "Batch run configuration";
    }

    public Icon getIcon() {
        return BatchIcons.BATCH_FILE_ICON;
    }

    @NotNull
    public String getId() {
        return "BatchConfigurationType";
    }

    public ConfigurationFactory[] getConfigurationFactories() {
        return new ConfigurationFactory[]{new BatchConfigurationFactory(this)};
    }

    private static class BatchConfigurationFactory extends ConfigurationFactory {
        public BatchConfigurationFactory(BatchConfigurationType batchConfigurationType) {
            super(batchConfigurationType);
        }

        @NotNull
        @Override
        public RunConfiguration createTemplateConfiguration(@NotNull Project project) {
            return new BatchRunConfiguration(new RunConfigurationModule(project), this, "");
        }
    }
}
