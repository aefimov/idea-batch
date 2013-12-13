package org.intellij.lang.batch.runner;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.ExecutionResult;
import com.intellij.execution.configurations.RunProfile;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.runners.DefaultProgramRunner;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.runners.RunContentBuilder;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * @author wibotwi
 */
public class BatchRunner extends DefaultProgramRunner {
    @NotNull
    public String getRunnerId() {
        return "BatchRunner";
    }

    public boolean canRun(@NotNull String executorId, @NotNull RunProfile profile) {
        return profile instanceof BatchRunConfiguration;
    }

    @Override
    protected RunContentDescriptor doExecute(final Project project,
                                             final RunProfileState state,
                                             final RunContentDescriptor contentToReuse,
                                             final ExecutionEnvironment env) throws ExecutionException {
        FileDocumentManager.getInstance().saveAllDocuments();

        ExecutionResult executionResult = state.execute(env.getExecutor(), this);
        if (executionResult == null || executionResult.getExecutionConsole() == null) return null;

        final RunContentBuilder contentBuilder = new RunContentBuilder(this, executionResult, env);
        return contentBuilder.showRunContent(contentToReuse);
    }

}
