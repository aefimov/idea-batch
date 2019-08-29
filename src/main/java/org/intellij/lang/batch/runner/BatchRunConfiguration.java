package org.intellij.lang.batch.runner;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configuration.EnvironmentVariablesComponent;
import com.intellij.execution.configurations.*;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.runners.RunConfigurationWithSuppressedDefaultRunAction;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.JDOMExternalizerUtil;
import com.intellij.openapi.util.WriteExternalException;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.*;

/**
 * @author wibotwi
 */
public class BatchRunConfiguration extends ModuleBasedConfiguration<RunConfigurationModule, Element> implements CommonBatchRunConfigurationParams, BatchRunConfigurationParams,
        RunConfigurationWithSuppressedDefaultRunAction {

    // common config
    private String interpreterOptions = "";
    private String workingDirectory = "";
    private boolean passParentEnvs = true;
    private Map<String, String> envs = new HashMap<>();

    // run config
    private String scriptName;
    private String scriptParameters;

    protected BatchRunConfiguration(RunConfigurationModule runConfigurationModule, ConfigurationFactory batchConfigurationFactory, String name) {
        super(name, runConfigurationModule, batchConfigurationFactory);
    }

    public static void copyParams(CommonBatchRunConfigurationParams from, CommonBatchRunConfigurationParams to) {
        to.setEnvs(new HashMap<>(from.getEnvs()));
        to.setInterpreterOptions(from.getInterpreterOptions());
        to.setWorkingDirectory(from.getWorkingDirectory());
        to.setPassParentEnvs(from.isPassParentEnvs());
    }

    public static void copyParams(BatchRunConfigurationParams from, BatchRunConfigurationParams to) {
        copyParams(from.getCommonParams(), to.getCommonParams());

        to.setScriptName(from.getScriptName());
        to.setScriptParameters(from.getScriptParameters());
    }

    @Override
    public Collection<Module> getValidModules() {
        return new ArrayList<>(Arrays.asList(ModuleManager.getInstance(getProject()).getModules()));
    }

    @Override
    protected ModuleBasedConfiguration createInstance() {
        return new BatchRunConfiguration(getConfigurationModule(), getFactory(), getName());
    }

    @NotNull
    public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
        return new BatchRunConfigurationEditor(this);
    }

    public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment env) throws ExecutionException {
        BatchCommandLineState state = new BatchCommandLineState(this, env);
        state.setConsoleBuilder(TextConsoleBuilderFactory.getInstance().createBuilder(getProject()));
        return state;
    }

    public String getInterpreterPath() {
        return "cmd.exe";
    }

    @Override
    public boolean isGeneratedName() {
        return Comparing.equal(getName(), suggestedName());
    }

    @Override
    public String suggestedName() {
        String name = (new File(scriptName)).getName();
        int ind = name.lastIndexOf('.');
        if (ind != -1) {
            return name.substring(0, ind);
        }
        return name;
    }

    @Override
    public void readExternal(@NotNull Element element) throws InvalidDataException {
        super.readExternal(element);

        // common config
        interpreterOptions = JDOMExternalizerUtil.readField(element, "INTERPRETER_OPTIONS");
        workingDirectory = JDOMExternalizerUtil.readField(element, "WORKING_DIRECTORY");
        String str = JDOMExternalizerUtil.readField(element, "PARENT_ENVS");
        if (str != null) {
            passParentEnvs = Boolean.parseBoolean(str);
        }
        EnvironmentVariablesComponent.readExternal(element, envs);

        // ???
        getConfigurationModule().readExternal(element);

        // run config
        scriptName = JDOMExternalizerUtil.readField(element, "SCRIPT_NAME");
        scriptParameters = JDOMExternalizerUtil.readField(element, "PARAMETERS");
    }

    @Override
    public void writeExternal(@NotNull Element element) throws WriteExternalException {
        super.writeExternal(element);

        // common config
        JDOMExternalizerUtil.writeField(element, "INTERPRETER_OPTIONS", interpreterOptions);
        JDOMExternalizerUtil.writeField(element, "WORKING_DIRECTORY", workingDirectory);
        JDOMExternalizerUtil.writeField(element, "PARENT_ENVS", Boolean.toString(passParentEnvs));
        EnvironmentVariablesComponent.writeExternal(element, envs);

        // ???
        getConfigurationModule().writeExternal(element);

        // run config
        JDOMExternalizerUtil.writeField(element, "SCRIPT_NAME", scriptName);
        JDOMExternalizerUtil.writeField(element, "PARAMETERS", scriptParameters);
    }

    public String getInterpreterOptions() {
        return interpreterOptions;
    }

    public void setInterpreterOptions(String interpreterOptions) {
        this.interpreterOptions = interpreterOptions;
    }

    public String getWorkingDirectory() {
        return workingDirectory;
    }

    public void setWorkingDirectory(String workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    public boolean isPassParentEnvs() {
        return passParentEnvs;
    }

    public void setPassParentEnvs(boolean passParentEnvs) {
        this.passParentEnvs = passParentEnvs;
    }

    public Map<String, String> getEnvs() {
        return envs;
    }

    public void setEnvs(Map<String, String> envs) {
        this.envs = envs;
    }

    public CommonBatchRunConfigurationParams getCommonParams() {
        return this;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public String getScriptParameters() {
        return scriptParameters;
    }

    public void setScriptParameters(String scriptParameters) {
        this.scriptParameters = scriptParameters;
    }
}
