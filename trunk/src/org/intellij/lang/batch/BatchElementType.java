package org.intellij.lang.batch;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;

public class BatchElementType extends IElementType {
    public BatchElementType(@NotNull @NonNls String debugName) {
        super(debugName, BatchLanguage.INSTANCE);
    }

    @Override
    @SuppressWarnings({"HardCodedStringLiteral"})
    public String toString() {
        return MessageFormat.format("Batch:{0}", super.toString());
    }
}
