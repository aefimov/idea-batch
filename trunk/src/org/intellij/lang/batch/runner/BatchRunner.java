package org.intellij.lang.batch.runner;

import com.intellij.execution.configurations.RunProfile;
import com.intellij.execution.executors.DefaultRunExecutor;
import com.intellij.execution.runners.DefaultProgramRunner;
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
        return executorId.equals(DefaultRunExecutor.EXECUTOR_ID) && profile instanceof BatchRunConfiguration;
    }
}
