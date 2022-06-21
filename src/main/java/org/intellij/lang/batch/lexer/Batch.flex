package org.intellij.lang.batch.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import org.intellij.lang.batch.BatchTokenTypes;

%%

%class _BatchLexer
%implements FlexLexer
%final
%ignorecase
%unicode
%function advance
%type IElementType
%eof{ return;
%eof}

WhiteSpace = [ \t]
LineTerminator = \r|\n|\r\n
StringLiteral = \" ( \\\" | [^\"\n\r] )* \"
Exp = [^ \t\f\n\r\:\;\,\|\&\<\>]+
CommandTerminator = "|""|"? | "&""&"? | "<""<"? | ">"">"?
EscapeCharacter = "^".

%state NEXT_SYM, CALL, ECHO, ECHO_SYM, FOR, FOR_IN, GOTO, IF, IF_EXIST, IF_DIGIT, IF_VARIABLE, IF_EQUAL, REM, SET, SET_VALUE, COMMAND, LABEL

%%
<YYINITIAL> {
    {WhiteSpace}+       { yybegin(YYINITIAL); return BatchTokenTypes.WHITE_SPACE; }
    {LineTerminator}+   { yybegin(YYINITIAL); return BatchTokenTypes.WHITE_SPACE; }
    "@"{Exp}            { yybegin(YYINITIAL); yypushback(yylength() - 1); return BatchTokenTypes.ECHO_OFF_MARKER; }
    {Exp}               { yybegin(COMMAND); yypushback(yylength()); }
    {EscapeCharacter}   { yybegin(YYINITIAL); return BatchTokenTypes.EXPRESSION;}
    {StringLiteral}     { yybegin(YYINITIAL); return BatchTokenTypes.STRING_LITERAL;}
    "::"                { yybegin(REM); yypushback(yylength()); }
    ":"                 { yybegin(LABEL); return BatchTokenTypes.LABEL_MARKER; }
    "&"                 { yybegin(YYINITIAL); return BatchTokenTypes.APPEND_OPERATOR; }
    "|"                 { yybegin(YYINITIAL); return BatchTokenTypes.PIPE_OPERATOR; }
    "&&"                { yybegin(YYINITIAL); return BatchTokenTypes.AND_OPERATOR; }
    "||"                { yybegin(YYINITIAL); return BatchTokenTypes.OR_OPERATOR; }
    "<""<"?             { yybegin(YYINITIAL); return BatchTokenTypes.GET_OPERATOR; }
    ">"">"?             { yybegin(YYINITIAL); return BatchTokenTypes.PUT_OPERATOR; }
    .                   { yybegin(NEXT_SYM); yypushback(yylength()); }
}

<LABEL> {
    {WhiteSpace}+       { yybegin(LABEL); return BatchTokenTypes.WHITE_SPACE; }
    {Exp}               { yybegin(REM);  return BatchTokenTypes.LABEL; }
    .                   { yybegin(REM); yypushback(yylength()); }
}

<NEXT_SYM> {
    {LineTerminator}+   { yybegin(YYINITIAL); return BatchTokenTypes.WHITE_SPACE; }
    {WhiteSpace}+       { yybegin(NEXT_SYM); return BatchTokenTypes.WHITE_SPACE; }
    {Exp}               { yybegin(COMMAND); yypushback(yylength()); }
    {EscapeCharacter}   { yybegin(NEXT_SYM); return BatchTokenTypes.EXPRESSION;}
    {StringLiteral}     { yybegin(NEXT_SYM); return BatchTokenTypes.STRING_LITERAL;}
    {CommandTerminator} { yybegin(YYINITIAL); yypushback(yylength()); }
    .                   { yybegin(NEXT_SYM); return BatchTokenTypes.EXPRESSION; }
}

<COMMAND> {
    {LineTerminator}+   { yybegin(YYINITIAL); return BatchTokenTypes.WHITE_SPACE; }
    "call"              { yybegin(CALL); return BatchTokenTypes.CALL_KEYWORD; }
    "goto"              { yybegin(GOTO); return BatchTokenTypes.GOTO_KEYWORD; }
    "echo"              { yybegin(ECHO); return BatchTokenTypes.ECHO_KEYWORD; }
    "for"               { yybegin(FOR); return BatchTokenTypes.FOR_KEYWORD; }
    "if"                { yybegin(IF); return BatchTokenTypes.IF_KEYWORD; }
    "else"              { yybegin(COMMAND); return BatchTokenTypes.ELSE_KEYWORD; }
    "rem"               { yybegin(REM); yypushback(yylength()); }
    "set"               { yybegin(SET); return BatchTokenTypes.SET_KEYWORD; }

    {WhiteSpace}+       { yybegin(COMMAND); return BatchTokenTypes.WHITE_SPACE; }
    {StringLiteral}     { yybegin(COMMAND); return BatchTokenTypes.STRING_LITERAL;}
    {EscapeCharacter}   { yybegin(COMMAND); return BatchTokenTypes.EXPRESSION;}
    {CommandTerminator} { yybegin(YYINITIAL); yypushback(yylength()); }
    {Exp}               { yybegin(COMMAND); return BatchTokenTypes.EXPRESSION; }
    .                   { yybegin(COMMAND); return BatchTokenTypes.EXPRESSION; }
}

<CALL> {
    {LineTerminator}+   { yybegin(YYINITIAL); return BatchTokenTypes.WHITE_SPACE; }
    {WhiteSpace}+       { yybegin(CALL); return BatchTokenTypes.WHITE_SPACE; }
    {StringLiteral}     { yybegin(CALL); return BatchTokenTypes.STRING_LITERAL;}
    {EscapeCharacter}   { yybegin(CALL); return BatchTokenTypes.EXPRESSION;}
    {CommandTerminator} { yybegin(YYINITIAL); yypushback(yylength()); }
    ":"{Exp}            { yybegin(CALL); return BatchTokenTypes.LABEL_REFERENCE; }
    {Exp}               { yybegin(CALL); return BatchTokenTypes.EXPRESSION; }
    .                   { yybegin(CALL); return BatchTokenTypes.BAD_CHARACTER; }
}

<GOTO> {
    {LineTerminator}+   { yybegin(YYINITIAL); return BatchTokenTypes.WHITE_SPACE; }
    {WhiteSpace}+       { yybegin(GOTO); return BatchTokenTypes.WHITE_SPACE; }
    "/?"                { yybegin(GOTO); return BatchTokenTypes.EXPRESSION; }
    ":"{Exp}            { yybegin(GOTO); return BatchTokenTypes.LABEL_REFERENCE;}
    {Exp}               { yybegin(GOTO); return BatchTokenTypes.LABEL_REFERENCE;}
    {CommandTerminator} { yybegin(YYINITIAL); yypushback(yylength()); }
    .                   { yybegin(GOTO); return BatchTokenTypes.BAD_CHARACTER; }
}

<ECHO> {
    {LineTerminator}+   { yybegin(YYINITIAL); return BatchTokenTypes.WHITE_SPACE; }
    {WhiteSpace}+       { yybegin(ECHO); return BatchTokenTypes.WHITE_SPACE; }
    {CommandTerminator} { yybegin(YYINITIAL); yypushback(yylength()); }

    "on"                { yybegin(YYINITIAL); return BatchTokenTypes.ON_KEYWORD; }
    "off"               { yybegin(YYINITIAL); return BatchTokenTypes.OFF_KEYWORD; }

    {StringLiteral}     { yybegin(ECHO_SYM); return BatchTokenTypes.STRING_LITERAL;}
    {EscapeCharacter}   { yybegin(ECHO_SYM); return BatchTokenTypes.STRING_LITERAL;}
    {Exp}               { yybegin(ECHO_SYM); return BatchTokenTypes.STRING_LITERAL;}
    .                   { yybegin(ECHO_SYM); return BatchTokenTypes.STRING_LITERAL; }
}

<ECHO_SYM> {
    {LineTerminator}+   { yybegin(YYINITIAL); return BatchTokenTypes.WHITE_SPACE; }
    {WhiteSpace}+       { yybegin(ECHO_SYM); return BatchTokenTypes.WHITE_SPACE; }
    {CommandTerminator} { yybegin(YYINITIAL); yypushback(yylength()); }
    {StringLiteral}     { yybegin(ECHO_SYM); return BatchTokenTypes.STRING_LITERAL;}
    {EscapeCharacter}   { yybegin(ECHO_SYM); return BatchTokenTypes.STRING_LITERAL;}
    {Exp}               { yybegin(ECHO_SYM); return BatchTokenTypes.STRING_LITERAL;}
    .                   { yybegin(ECHO_SYM); return BatchTokenTypes.STRING_LITERAL; }
}

<FOR> {
    {LineTerminator}+   { yybegin(YYINITIAL); return BatchTokenTypes.WHITE_SPACE; }
    {WhiteSpace}+       { yybegin(FOR); return BatchTokenTypes.WHITE_SPACE; }
    "in"                { yybegin(FOR_IN); return BatchTokenTypes.IN_KEYWORD; }
    "do"                { yybegin(YYINITIAL); return BatchTokenTypes.DO_KEYWORD; }
    .                   { yybegin(FOR); return BatchTokenTypes.EXPRESSION; }
}

<FOR_IN> {
    {LineTerminator}+   { yybegin(YYINITIAL); return BatchTokenTypes.WHITE_SPACE; }
    {WhiteSpace}+       { yybegin(FOR_IN); return BatchTokenTypes.WHITE_SPACE; }
    "("                 { yybegin(FOR_IN); return BatchTokenTypes.LEFT_PARENTHESIS; }
    ")"                 { yybegin(FOR); return BatchTokenTypes.RIGHT_PARENTHESIS; }
    .                   { yybegin(FOR_IN); return BatchTokenTypes.EXPRESSION; }
}

<IF> {
    {LineTerminator}+   { yybegin(YYINITIAL); return BatchTokenTypes.WHITE_SPACE; }
    {WhiteSpace}+       { yybegin(IF); return BatchTokenTypes.WHITE_SPACE; }
    "not"               { yybegin(IF); return BatchTokenTypes.NOT_KEYWORD; }
    "exist"             { yybegin(IF_EXIST); return BatchTokenTypes.EXIST_KEYWORD; }
    "errorlevel"        { yybegin(IF_DIGIT); return BatchTokenTypes.ERRORLEVEL_KEYWORD; }
    "cmdextversion"     { yybegin(IF_DIGIT); return BatchTokenTypes.CMDEXTVERSION_KEYWORD; }
    "defined"           { yybegin(IF_VARIABLE); return BatchTokenTypes.DEFINED_KEYWORD; }
    {StringLiteral}     { yybegin(IF); return BatchTokenTypes.STRING_LITERAL;}
    "=="                { yybegin(IF_EQUAL); return BatchTokenTypes.EQUAL_OPERATOR; }
    .                   { yybegin(IF); return BatchTokenTypes.EXPRESSION; }
}

<IF_EQUAL> {
    {LineTerminator}+   { yybegin(YYINITIAL); return BatchTokenTypes.WHITE_SPACE; }
    {WhiteSpace}+       { yybegin(IF_EQUAL); return BatchTokenTypes.WHITE_SPACE; }
    {StringLiteral}     { yybegin(YYINITIAL); return BatchTokenTypes.STRING_LITERAL;}
    {Exp}               { yybegin(YYINITIAL); yypushback(yylength()); }
    .                   { yybegin(IF_EQUAL); return BatchTokenTypes.BAD_CHARACTER; }
}

<IF_EXIST> {
    {LineTerminator}+   { yybegin(YYINITIAL); return BatchTokenTypes.WHITE_SPACE; }
    {WhiteSpace}+       { yybegin(IF_EXIST); return BatchTokenTypes.WHITE_SPACE; }
    {StringLiteral}     { yybegin(IF_EXIST); return BatchTokenTypes.STRING_LITERAL;}
    {EscapeCharacter}   { yybegin(IF_EXIST); return BatchTokenTypes.EXPRESSION;}
    {Exp}               { yybegin(YYINITIAL); yypushback(yylength()); }
    .                   { yybegin(IF_EXIST); return BatchTokenTypes.BAD_CHARACTER; }
}

<IF_DIGIT> {
    {LineTerminator}+   { yybegin(YYINITIAL); return BatchTokenTypes.WHITE_SPACE; }
    {WhiteSpace}+       { yybegin(IF_DIGIT); return BatchTokenTypes.WHITE_SPACE; }
    [:digit:]+          { yybegin(YYINITIAL); return BatchTokenTypes.DIGIT;}
    .                   { yybegin(IF_DIGIT); return BatchTokenTypes.BAD_CHARACTER; }
}

<IF_VARIABLE> {
    {LineTerminator}+   { yybegin(YYINITIAL); return BatchTokenTypes.WHITE_SPACE; }
    {WhiteSpace}+       { yybegin(IF_VARIABLE); return BatchTokenTypes.WHITE_SPACE; }
    .                   { yybegin(IF_VARIABLE); return BatchTokenTypes.BAD_CHARACTER; }
}

<REM> {
    {LineTerminator}+   { yybegin(YYINITIAL); return BatchTokenTypes.WHITE_SPACE; }
    .                   { yybegin(REM); return BatchTokenTypes.COMMENT; }
}

<SET> {
    {LineTerminator}+   { yybegin(YYINITIAL); return BatchTokenTypes.WHITE_SPACE; }
    {WhiteSpace}+       { yybegin(SET); return BatchTokenTypes.WHITE_SPACE; }
    "="[ \t]*{Exp}      { yybegin(SET_VALUE); yypushback(yylength() - 1); return BatchTokenTypes.EQUAL_OPERATOR; }
    {Exp}[ \t]*"="      { yybegin(SET); yypushback(1); return BatchTokenTypes.ENVIRONMENT_VARIABLE_DEFINITION;}
    "="                 { yybegin(SET_VALUE); return BatchTokenTypes.EQUAL_OPERATOR; }
    .                   { yybegin(SET); return BatchTokenTypes.EXPRESSION; }
}

<SET_VALUE> {
    {LineTerminator}+   { yybegin(YYINITIAL); return BatchTokenTypes.WHITE_SPACE; }
    {WhiteSpace}+       { yybegin(SET_VALUE); return BatchTokenTypes.WHITE_SPACE; }
    {StringLiteral}     { yybegin(SET_VALUE); return BatchTokenTypes.STRING_LITERAL;}
    .                   { yybegin(SET_VALUE); return BatchTokenTypes.EXPRESSION; }
}
