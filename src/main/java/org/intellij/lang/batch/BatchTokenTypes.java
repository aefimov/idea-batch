package org.intellij.lang.batch;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

public interface BatchTokenTypes {
    IElementType BAD_CHARACTER = TokenType.BAD_CHARACTER;
    IElementType WHITE_SPACE = TokenType.WHITE_SPACE;

    IElementType STRING_LITERAL = new BatchElementType("STRING_LITERAL");
    IElementType IDENTIFIER = new BatchElementType("IDENTIFIER");
    IElementType VARIABLE = new BatchElementType("VARIABLE");
    IElementType ENVIRONMENT_VARIABLE = new BatchElementType("ENVIRONMENT_VARIABLE");
    IElementType ENVIRONMENT_VARIABLE_DEFINITION = new BatchElementType("ENVIRONMENT_VARIABLE_DEFINITION");
    IElementType EXPRESSION = new BatchElementType("EXPRESSION");
    IElementType DIGIT = new BatchElementType("DIGIT");
    IElementType LABEL = new BatchElementType("LABEL");
    IElementType LABEL_REFERENCE = new BatchElementType("LABEL_REFERENCE");

    IElementType COMMENT = new BatchElementType("COMMENT");

    IElementType ECHO_OFF_MARKER = new BatchElementType("ECHO_OFF_MARKER");
    IElementType LABEL_MARKER = new BatchElementType("LABEL_MARKER");

    IElementType EQUAL_OPERATOR = new BatchElementType("EQUAL_OPERATOR");
    IElementType OR_OPERATOR = new BatchElementType("OR_OPERATOR");
    IElementType AND_OPERATOR = new BatchElementType("AND_OPERATOR");
    IElementType APPEND_OPERATOR = new BatchElementType("APPEND_OPERATOR");
    IElementType PUT_OPERATOR = new BatchElementType("PUT_OPERATOR");
    IElementType GET_OPERATOR = new BatchElementType("GET_OPERATOR");
    IElementType PIPE_OPERATOR = new BatchElementType("PIPE_OPERATOR");

    IElementType ECHO_KEYWORD = new BatchElementType("ECHO_KEYWORD");
    IElementType GOTO_KEYWORD = new BatchElementType("GOTO_KEYWORD");
    IElementType CALL_KEYWORD = new BatchElementType("CALL_KEYWORD");
    IElementType IF_KEYWORD = new BatchElementType("IF_KEYWORD");
    IElementType EXIST_KEYWORD = new BatchElementType("EXIST_KEYWORD");
    IElementType NOT_KEYWORD = new BatchElementType("NOT_KEYWORD");
    IElementType DEFINED_KEYWORD = new BatchElementType("DEFINED_KEYWORD");
    IElementType FOR_KEYWORD = new BatchElementType("FOR_KEYWORD");
    IElementType IN_KEYWORD = new BatchElementType("FOR_KEYWORD");
    IElementType DO_KEYWORD = new BatchElementType("DO_KEYWORD");
    IElementType ERRORLEVEL_KEYWORD = new BatchElementType("ERRORLEVEL_KEYWORD");
    IElementType ON_KEYWORD = new BatchElementType("ON_KEYWORD");
    IElementType OFF_KEYWORD = new BatchElementType("OFF_KEYWORD");
    IElementType ELSE_KEYWORD = new BatchElementType("ELSE_KEYWORD");
    IElementType SET_KEYWORD = new BatchElementType("SET_KEYWORD");
    IElementType CMDEXTVERSION_KEYWORD = new BatchElementType("CMDEXTVERSION_KEYWORD");

    IElementType LEFT_BRACE = new BatchElementType("LEFT_BRACE");
    IElementType RIGHT_BRACE = new BatchElementType("RIGHT_BRACE");
    IElementType RIGHT_BRACKET = new BatchElementType("RIGHT_BRACKET");
    IElementType LEFT_BRACKET = new BatchElementType("LEFT_BRACKET");
    IElementType RIGHT_PARENTHESIS = new BatchElementType("RIGHT_PARENTHESIS");
    IElementType LEFT_PARENTHESIS = new BatchElementType("LEFT_PARENTHESIS");

    TokenSet KEYWORDS = TokenSet.create(
            ECHO_KEYWORD,
            GOTO_KEYWORD,
            CALL_KEYWORD,
            IF_KEYWORD,
            EXIST_KEYWORD,
            NOT_KEYWORD,
            DEFINED_KEYWORD,
            FOR_KEYWORD,
            IN_KEYWORD,
            DO_KEYWORD,
            ERRORLEVEL_KEYWORD,
            ON_KEYWORD,
            OFF_KEYWORD,
            ELSE_KEYWORD,
            SET_KEYWORD,
            CMDEXTVERSION_KEYWORD
    );

    TokenSet OPERATORS = TokenSet.create(
            EQUAL_OPERATOR,
            OR_OPERATOR,
            AND_OPERATOR,
            PIPE_OPERATOR,
            APPEND_OPERATOR,
            PUT_OPERATOR,
            GET_OPERATOR
    );

    TokenSet BRACES = TokenSet.create(
            LEFT_BRACE,
            RIGHT_BRACE
    );

    TokenSet BRACKETS = TokenSet.create(
            LEFT_BRACKET,
            RIGHT_BRACKET
    );

    TokenSet PARENTHESES = TokenSet.create(
            LEFT_PARENTHESIS,
            RIGHT_PARENTHESIS
    );
}
