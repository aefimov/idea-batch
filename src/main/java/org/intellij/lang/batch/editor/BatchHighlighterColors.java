package org.intellij.lang.batch.editor;

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;

/**
 * Batch highlighter colors.
 *
 * @author Alexey Efimov
 */
public interface BatchHighlighterColors {
    TextAttributesKey COMMENT = TextAttributesKey.createTextAttributesKey(
            "BATCH.COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT
    );
    TextAttributesKey STRING = TextAttributesKey.createTextAttributesKey(
            "BATCH.STRING", DefaultLanguageHighlighterColors.STRING
    );
    TextAttributesKey VARIABLE = TextAttributesKey.createTextAttributesKey(
            "BATCH.VARIABLE", DefaultLanguageHighlighterColors.MARKUP_ATTRIBUTE
    );
    TextAttributesKey ENVIRONMENT_VARIABLE = TextAttributesKey.createTextAttributesKey(
            "BATCH.ENVIRONMENT_VARIABLE", DefaultLanguageHighlighterColors.CONSTANT
    );
    TextAttributesKey ENVIRONMENT_VARIABLE_DEFINITION = TextAttributesKey.createTextAttributesKey(
            "BATCH.ENVIRONMENT_VARIABLE_DEFINITION", DefaultLanguageHighlighterColors.CONSTANT
    );
    TextAttributesKey LABEL = TextAttributesKey.createTextAttributesKey(
            "BATCH.LABEL", DefaultLanguageHighlighterColors.METADATA
    );
    TextAttributesKey LABEL_REFERENCE = TextAttributesKey.createTextAttributesKey(
            "BATCH.LABEL_REFERENCE", DefaultLanguageHighlighterColors.METADATA
    );
    TextAttributesKey EXPRESSION = TextAttributesKey.createTextAttributesKey("BATCH.EXPRESSION",
            DefaultLanguageHighlighterColors.KEYWORD);

    TextAttributesKey NUMBER = TextAttributesKey.createTextAttributesKey(
            "BATCH.NUMBER", DefaultLanguageHighlighterColors.NUMBER
    );
    TextAttributesKey OPERATION_SIGN = TextAttributesKey.createTextAttributesKey(
            "BATCH.OPERATION_SIGN", DefaultLanguageHighlighterColors.OPERATION_SIGN
    );
    TextAttributesKey BRACES = TextAttributesKey.createTextAttributesKey(
            "BATCH.BRACES", DefaultLanguageHighlighterColors.BRACES
    );
    TextAttributesKey BRACKETS = TextAttributesKey.createTextAttributesKey(
            "BATCH.BRACKETS", DefaultLanguageHighlighterColors.BRACKETS
    );
    TextAttributesKey PARENTHS = TextAttributesKey.createTextAttributesKey(
            "BATCH.PARENTHS", DefaultLanguageHighlighterColors.PARENTHESES
    );
    TextAttributesKey KEYWORD = TextAttributesKey.createTextAttributesKey(
            "BATCH.KEYWORD", DefaultLanguageHighlighterColors.KEYWORD
    );

    TextAttributesKey BAD_CHARACTER = HighlighterColors.BAD_CHARACTER;
}
