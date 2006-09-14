package org.intellij.lang.batch.fileTypes;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.intellij.lang.batch.BatchLanguage;
import org.intellij.lang.batch.util.BatchBundle;

import javax.swing.*;

/**
 * Batch file type.
 *
 * @author Alexey Efimov
 */
public final class BatchFileType extends LanguageFileType {
    @NonNls
    public static final String[] DEFAULT_ASSOCIATED_EXTENSIONS = new String[] {"bat", "cmd"};

    public BatchFileType() {
        super(new BatchLanguage());
    }

    @NotNull
    @NonNls
    public String getDefaultExtension() {
        return DEFAULT_ASSOCIATED_EXTENSIONS[0];
    }

    @NotNull
    public String getDescription() {
        return BatchBundle.message("batch.filetype.description");
    }

    @Nullable
    public Icon getIcon() {
        return IconLoader.getIcon("/fileTypes/batch.png");
    }

    @NotNull
    @NonNls
    public String getName() {
        return "Batch";
    }
}
