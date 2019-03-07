package org.intellij.lang.batch.fileTypes;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.intellij.lang.batch.BatchTokenTypes;
import org.intellij.lang.batch.editor.BatchHighlighterColors;
import org.intellij.lang.batch.lexer.BatchHighlighterLexer;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Batch syntax highlighter
 *
 * @author Alexey Efimov
 */
public final class BatchSyntaxHighlighter extends SyntaxHighlighterBase {

    private final BatchHighlighterLexer lexer;
    private final Map<IElementType, TextAttributesKey> colors = new HashMap<IElementType, TextAttributesKey>();
    private final Map<IElementType, TextAttributesKey> backgrounds = new HashMap<IElementType, TextAttributesKey>();

    public BatchSyntaxHighlighter() {
        lexer = new BatchHighlighterLexer();

        fillMap(colors, BatchTokenTypes.BRACES, BatchHighlighterColors.BRACES);
        fillMap(colors, BatchTokenTypes.BRACKETS, BatchHighlighterColors.BRACKETS);
        fillMap(colors, BatchTokenTypes.PARENTHESES, BatchHighlighterColors.PARENTHS);

        fillMap(colors, BatchTokenTypes.OPERATORS, BatchHighlighterColors.OPERATION_SIGN);

        colors.put(BatchTokenTypes.DIGIT, BatchHighlighterColors.NUMBER);
        colors.put(BatchTokenTypes.VARIABLE, BatchHighlighterColors.VARIABLE);
        colors.put(BatchTokenTypes.ENVIRONMENT_VARIABLE, BatchHighlighterColors.ENVIRONMENT_VARIABLE);
        colors.put(BatchTokenTypes.ENVIRONMENT_VARIABLE_DEFINITION, BatchHighlighterColors.ENVIRONMENT_VARIABLE_DEFINITION);
        colors.put(BatchTokenTypes.LABEL, BatchHighlighterColors.LABEL);
        colors.put(BatchTokenTypes.LABEL_REFERENCE, BatchHighlighterColors.LABEL_REFERENCE);
        colors.put(BatchTokenTypes.LABEL_MARKER, BatchHighlighterColors.EXPRESSION);
        colors.put(BatchTokenTypes.ECHO_OFF_MARKER, BatchHighlighterColors.EXPRESSION);
        colors.put(BatchTokenTypes.EXPRESSION, BatchHighlighterColors.EXPRESSION);
        colors.put(BatchTokenTypes.BAD_CHARACTER, BatchHighlighterColors.BAD_CHARACTER);
        colors.put(BatchTokenTypes.COMMENT, BatchHighlighterColors.COMMENT);
        colors.put(BatchTokenTypes.STRING_LITERAL, BatchHighlighterColors.STRING);

        fillMap(colors, BatchTokenTypes.KEYWORDS, BatchHighlighterColors.KEYWORD);
    }

    @NotNull
    public Lexer getHighlightingLexer() {
        return lexer;
    }

    @NotNull
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        return pack(getAttributeKeys(tokenType, backgrounds), getAttributeKeys(tokenType, colors));
    }

    private TextAttributesKey getAttributeKeys(IElementType tokenType, Map<IElementType, TextAttributesKey> map) {
        return map.get(tokenType);
    }
}
