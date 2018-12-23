package org.intellij.lang.batch.runner;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessTerminatedListener;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author wibotwi
 */
public class BatchCommandLineState extends CommandLineState {
    private BatchRunConfiguration runConfiguration;

    public BatchCommandLineState(BatchRunConfiguration runConfiguration, ExecutionEnvironment env) {
        super(env);
        this.runConfiguration = runConfiguration;
    }

    @NotNull
    @Override
    protected OSProcessHandler startProcess() throws ExecutionException {
        GeneralCommandLine commandLine = generateCommandLine();
        OSProcessHandler osProcessHandler = new OSProcessHandler(commandLine.createProcess(), commandLine.getCommandLineString());
        ProcessTerminatedListener.attach(osProcessHandler);
        return osProcessHandler;
    }

    private GeneralCommandLine generateCommandLine() {
        GeneralCommandLine commandLine = new GeneralCommandLine();
        commandLine.setExePath(runConfiguration.getInterpreterPath());
        commandLine.getParametersList().addParametersString("/c");
        commandLine.getParametersList().addParametersString(runConfiguration.getInterpreterOptions());
        if (!StringUtil.isEmptyOrSpaces(runConfiguration.getScriptName())) {
            commandLine.addParameter(runConfiguration.getScriptName());
        }

        commandLine.getParametersList().addParametersString(runConfiguration.getScriptParameters());
        if (!StringUtil.isEmptyOrSpaces(runConfiguration.getWorkingDirectory())) {
            commandLine.setWorkDirectory(runConfiguration.getWorkingDirectory());
        }

        commandLine.withEnvironment(runConfiguration.getEnvs());
        // TODO: remove this after discontinue support of IDEA 14
        // See: https://github.com/aefimov/idea-batch/commit/f90c3ad8e5fbf7ab293d8324c9fc1b0586204fc7
        commandLine.setPassParentEnvironment(runConfiguration.isPassParentEnvs());

        return commandLine;
    }

    @Nullable
    @Override
    protected ConsoleView createConsole(@NotNull Executor executor) throws ExecutionException {
        if (runConfiguration.getScriptName() == null || runConfiguration.getScriptName().trim().isEmpty()) {
            return null;
        }
        return super.createConsole(executor);
    }
}
