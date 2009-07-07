package org.intellij.lang.batch.runner;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessTerminatedListener;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.util.text.StringUtil;

/**
 * @author wibotwi
 */
public class BatchCommandLineState extends CommandLineState {
    private BatchRunConfiguration runConfiguration;

    public BatchCommandLineState(BatchRunConfiguration runConfiguration, ExecutionEnvironment env) {
        super(env);
        this.runConfiguration = runConfiguration;
    }

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

        commandLine.setEnvParams(runConfiguration.getEnvs());
        commandLine.setPassParentEnvs(runConfiguration.isPassParentEnvs());
        return commandLine;
    }

}
