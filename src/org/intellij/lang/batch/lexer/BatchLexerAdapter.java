package org.intellij.lang.batch.lexer;

import com.intellij.lexer.FlexAdapter;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

/**
 * Lexer adapter.
 *
 * @author Alexey Efimov
 */
public class BatchLexerAdapter extends FlexAdapter {
    public BatchLexerAdapter(Project project, VirtualFile file) {
        super(new _BatchLexer((java.io.Reader)null));
    }
}
