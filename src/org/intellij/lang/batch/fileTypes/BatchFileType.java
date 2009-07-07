package org.intellij.lang.batch.fileTypes;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.LanguageFileType;
import org.intellij.lang.batch.BatchLanguage;
import org.intellij.lang.batch.util.BatchBundle;
import org.intellij.lang.batch.util.BatchIcons;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Batch file type.
 *
 * @author Alexey Efimov
 */
public final class BatchFileType extends LanguageFileType {
    public static final BatchFileType BATCH_FILE_TYPE = new BatchFileType();
    public static final Language BASH_LANGUAGE = BATCH_FILE_TYPE.getLanguage();

    @NonNls
    public static final String[] DEFAULT_ASSOCIATED_EXTENSIONS = new String[]{"bat", "cmd"};

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
        return BatchIcons.BATCH_FILE_ICON;
    }

    @NotNull
    @NonNls
    public String getName() {
        return "Batch";
    }
}
