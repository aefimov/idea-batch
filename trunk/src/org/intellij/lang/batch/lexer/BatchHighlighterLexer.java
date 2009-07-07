package org.intellij.lang.batch.lexer;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.LayeredLexer;
import org.intellij.lang.batch.BatchTokenTypes;

/**
 * Lexer adapter.
 *
 * @author Alexey Efimov
 */
public class BatchHighlighterLexer extends LayeredLexer {
    public BatchHighlighterLexer() {
        super(new FlexAdapter(new _BatchLexer((java.io.Reader) null)));

        _ExpressionLexer stringLexer = new _ExpressionLexer((java.io.Reader) null);
        stringLexer.setDefaultToken(BatchTokenTypes.STRING_LITERAL);
        registerLayer(
                new FlexAdapter(stringLexer),
                BatchTokenTypes.STRING_LITERAL);
        _ExpressionLexer extressionLexer = new _ExpressionLexer((java.io.Reader) null);
        extressionLexer.setDefaultToken(BatchTokenTypes.EXPRESSION);
        registerLayer(
                new FlexAdapter(extressionLexer),
                BatchTokenTypes.EXPRESSION);
        _ExpressionLexer labelLexer = new _ExpressionLexer((java.io.Reader) null);
        labelLexer.setDefaultToken(BatchTokenTypes.LABEL_REFERENCE);
        registerLayer(
                new FlexAdapter(labelLexer),
                BatchTokenTypes.LABEL_REFERENCE);
    }
}
