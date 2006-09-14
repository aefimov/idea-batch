package org.intellij.lang.batch;

import com.intellij.psi.tree.IElementType;
import org.intellij.lang.batch.fileTypes.BatchFileTypeManager;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;

public class BatchElementType extends IElementType {
    private final IElementType parsedType;

    public BatchElementType(@NotNull @NonNls String debugName, IElementType parsedType) {
        super(debugName, BatchFileTypeManager.getInstance().getFileType().getLanguage());
        this.parsedType = parsedType;
    }

    public BatchElementType(@NotNull @NonNls String debugName) {
        this(debugName, null);
    }

    public IElementType getParsedType() {
        return parsedType != null ? parsedType : this;
    }

    @SuppressWarnings({"HardCodedStringLiteral"})
    public String toString() {
        return MessageFormat.format("Batch:{0}", super.toString());
    }
}
