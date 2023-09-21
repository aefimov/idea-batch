package org.intellij.lang.batch.runner;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessTerminatedListener;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.ide.macro.Macro;
import com.intellij.ide.macro.MacroManager;
import com.intellij.openapi.diagnostic.Logger;
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

        commandLine.getParametersList().addParametersString(expandMacrosInName(runConfiguration.getScriptParameters()));
        if (!StringUtil.isEmptyOrSpaces(runConfiguration.getWorkingDirectory())) {
             commandLine.setWorkDirectory(expandMacrosInName(runConfiguration.getWorkingDirectory()));
        }

        commandLine.withEnvironment(runConfiguration.getEnvs());
        commandLine.withParentEnvironmentType(runConfiguration.isPassParentEnvs() ?
                    GeneralCommandLine.ParentEnvironmentType.CONSOLE :
                    GeneralCommandLine.ParentEnvironmentType.NONE);
        return commandLine;
    }

    public String expandMacrosInName(String name) {
        if (name != null && name.contains("$")) {
            try {
                return MacroManager.getInstance().expandMacrosInString(name, true, getEnvironment().getDataContext());
            } catch (Macro.ExecutionCancelledException e) {
                Logger.getInstance(BatchCommandLineState.class.getName()).info(e);
            }
        }
        return name;
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
