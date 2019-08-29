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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    @Nullable
    @Override
    protected RunContentDescriptor doExecute(@NotNull RunProfileState state,
                                             @NotNull ExecutionEnvironment env) throws ExecutionException {
        FileDocumentManager.getInstance().saveAllDocuments();

        ExecutionResult executionResult = state.execute(env.getExecutor(), this);
        if (executionResult == null || executionResult.getExecutionConsole() == null) return null;

        final RunContentBuilder contentBuilder = new RunContentBuilder(executionResult, env);
        return contentBuilder.showRunContent(env.getContentToReuse());
    }

}
