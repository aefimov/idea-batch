package org.intellij.lang.batch.lexer;

import com.intellij.lexer.LayeredLexer;
import com.intellij.lexer.Lexer;
import com.intellij.lexer.FlexAdapter;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.tree.IElementType;
import org.intellij.lang.batch.BatchTokenTypes;

/**
 * Lexer adapter.
 *
 * @author Alexey Efimov
 */
public class BatchHighlighterLexer extends LayeredLexer {
    public BatchHighlighterLexer(Project project, VirtualFile file) {
        super(new BatchLexerAdapter(project, file));

        _ExpressionLexer stringLexer = new _ExpressionLexer((java.io.Reader) null);
        stringLexer.setDefaultToken(BatchTokenTypes.STRING_LITERAL);
        registerLayer(
                new FlexAdapter(stringLexer), 
                new IElementType[] {
                        BatchTokenTypes.STRING_LITERAL,
                }
        );
        _ExpressionLexer extressionLexer = new _ExpressionLexer((java.io.Reader) null);
        extressionLexer.setDefaultToken(BatchTokenTypes.EXPRESSION);
        registerLayer(
                new FlexAdapter(extressionLexer), 
                new IElementType[] {
                        BatchTokenTypes.EXPRESSION,
                }
        );
        _ExpressionLexer labelLexer = new _ExpressionLexer((java.io.Reader) null);
        labelLexer.setDefaultToken(BatchTokenTypes.LABEL_REFERENCE);
        registerLayer(
                new FlexAdapter(labelLexer), 
                new IElementType[] {
                        BatchTokenTypes.LABEL_REFERENCE,
                }
        );
    }
}
