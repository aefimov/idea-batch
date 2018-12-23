package org.intellij.lang.batch.fileTypes;

import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import org.jetbrains.annotations.NotNull;

/**
 * @author wibotwi
 */
public class BatchFileTypeLoader extends FileTypeFactory {

    @Override
    public void createFileTypes(@NotNull FileTypeConsumer consumer) {
        consumer.consume(BatchFileType.BATCH_FILE_TYPE, "bat");
        consumer.consume(BatchFileType.BATCH_FILE_TYPE, "cmd");
    }
}
