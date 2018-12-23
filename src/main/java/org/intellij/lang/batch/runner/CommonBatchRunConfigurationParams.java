package org.intellij.lang.batch.runner;

import java.util.Map;

/**
 * @author wibotwi
 */
public interface CommonBatchRunConfigurationParams {

    String getInterpreterOptions();

    void setInterpreterOptions(String options);

    String getWorkingDirectory();

    void setWorkingDirectory(String workingDirectory);

    boolean isPassParentEnvs();

    void setPassParentEnvs(boolean passParentEnvs);

    Map<String, String> getEnvs();

    void setEnvs(Map<String, String> envs);

}
