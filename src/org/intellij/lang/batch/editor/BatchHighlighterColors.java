package org.intellij.lang.batch.editor;

import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.SyntaxHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;

import java.awt.*;

/**
 * Batch highlighter colors.
 *
 * @author Alexey Efimov
 */
public interface BatchHighlighterColors {
    TextAttributesKey COMMENT = TextAttributesKey.createTextAttributesKey(
        "BATCH.COMMENT", SyntaxHighlighterColors.JAVA_BLOCK_COMMENT.getDefaultAttributes()
    );
    TextAttributesKey STRING = TextAttributesKey.createTextAttributesKey(
        "BATCH.STRING", SyntaxHighlighterColors.STRING.getDefaultAttributes()
    );
    TextAttributesKey VARIABLE = TextAttributesKey.createTextAttributesKey(
        "BATCH.VARIABLE", new TextAttributes(new Color(0, 0, 0xff), null, null, null, Font.BOLD | Font.ITALIC)
    );
    TextAttributesKey ENVIRONMENT_VARIABLE = TextAttributesKey.createTextAttributesKey(
        "BATCH.ENVIRONMENT_VARIABLE", new TextAttributes(new Color(0x66, 0xe, 0x7a), null, null, null, Font.BOLD | Font.ITALIC)
    );
    TextAttributesKey ENVIRONMENT_VARIABLE_DEFINITION = TextAttributesKey.createTextAttributesKey(
        "BATCH.ENVIRONMENT_VARIABLE_DEFINITION", new TextAttributes(new Color(0x66, 0xe, 0x7a), null, null, null, Font.BOLD)
    );
    TextAttributesKey LABEL = TextAttributesKey.createTextAttributesKey(
        "BATCH.LABEL", new TextAttributes(new Color(0x80, 0x80, 0), null, null, null, Font.BOLD)
    );
    TextAttributesKey LABEL_REFERENCE = TextAttributesKey.createTextAttributesKey(
        "BATCH.LABEL_REFERENCE", new TextAttributes(new Color(0x80, 0x80, 0), null, null, null, Font.BOLD | Font.ITALIC)
    );
    TextAttributesKey EXPRESSION = TextAttributesKey.createTextAttributesKey(
        "BATCH.EXPRESSION", new TextAttributes(new Color(0, 0, 0x80), null, null, null, 0)
    );
    TextAttributesKey NUMBER = TextAttributesKey.createTextAttributesKey(
        "BATCH.NUMBER", new TextAttributes(new Color(0, 0, 0xff), null, null, null, 0)
    );
    TextAttributesKey OPERATION_SIGN = TextAttributesKey.createTextAttributesKey(
        "BATCH.OPERATION_SIGN", SyntaxHighlighterColors.OPERATION_SIGN.getDefaultAttributes()
    );
    TextAttributesKey BRACES = TextAttributesKey.createTextAttributesKey(
        "BATCH.BRACES", SyntaxHighlighterColors.BRACES.getDefaultAttributes()
    );
    TextAttributesKey BRACKETS = TextAttributesKey.createTextAttributesKey(
        "BATCH.BRACES", SyntaxHighlighterColors.BRACKETS.getDefaultAttributes()
    );
    TextAttributesKey PARENTHS = TextAttributesKey.createTextAttributesKey(
        "BATCH.PARENTHS", SyntaxHighlighterColors.PARENTHS.getDefaultAttributes()
    );
    TextAttributesKey KEYWORD = TextAttributesKey.createTextAttributesKey(
        "BATCH.KEYWORD", SyntaxHighlighterColors.KEYWORD.getDefaultAttributes()
    );

    TextAttributesKey BAD_CHARACTER = HighlighterColors.BAD_CHARACTER;
}
