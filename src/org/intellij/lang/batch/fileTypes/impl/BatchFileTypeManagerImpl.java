package org.intellij.lang.batch.fileTypes.impl;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.fileTypes.FileTypeManager;
import org.intellij.lang.batch.fileTypes.BatchFileTypeManager;
import org.intellij.lang.batch.fileTypes.BatchFileType;
import org.jetbrains.annotations.NonNls;

final class BatchFileTypeManagerImpl extends BatchFileTypeManager implements ApplicationComponent {
    private final BatchFileType fileType = new BatchFileType();

    public void disposeComponent() {
    }

    @NonNls
    public String getComponentName() {
        return "BatchFileTypeManager";
    }

    public void initComponent() {
        FileTypeManager.getInstance().registerFileType(fileType, BatchFileType.DEFAULT_ASSOCIATED_EXTENSIONS);
    }

    public BatchFileType getFileType() {
        return fileType;
    }

}
