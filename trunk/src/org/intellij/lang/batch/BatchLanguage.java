package org.intellij.lang.batch;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
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
        super(ID, "application/x-batch", "application/x-bat", "text/x-script.bat");
        SyntaxHighlighterFactory.LANGUAGE_FACTORY.addExplicitExtension(this, new BatchHighlighterFactory());
    }

    private static class BatchHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory {

        @Override
        @NotNull
        protected SyntaxHighlighter createHighlighter() {
            return new BatchSyntaxHighlighter();
        }

    }
}