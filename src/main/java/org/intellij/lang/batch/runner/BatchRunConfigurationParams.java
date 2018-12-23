package org.intellij.lang.batch.runner;

/**
 * @author wibotwi
 */
public interface BatchRunConfigurationParams {
    CommonBatchRunConfigurationParams getCommonParams();

    String getScriptName();

    void setScriptName(String scriptName);

    String getScriptParameters();

    void setScriptParameters(String scriptParameters);

}
