package org.intellij.lang.batch.fileTypes;

import com.intellij.openapi.application.ApplicationManager;

/**
 * Batch file type manager.
 *
 * @author Alexey Efimov
 */
public abstract class BatchFileTypeManager {
    public static BatchFileTypeManager getInstance() {
        return ApplicationManager.getApplication().getComponent(BatchFileTypeManager.class);
    }

    public abstract BatchFileType getFileType();
}
