package org.intellij.lang.batch;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.intellij.lang.batch.fileTypes.BatchSyntaxHighlighter;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * Batch language.
 *
 * @author Alexey Efimov
 */
public class BatchLanguage extends Language {
    @NonNls
    private static final String ID = "Batch";

    public BatchLanguage() {
        super(ID);
    }

    @NotNull
    public SyntaxHighlighter getSyntaxHighlighter(Project project, VirtualFile file) {
        return new BatchSyntaxHighlighter(project, file);
    }
}
