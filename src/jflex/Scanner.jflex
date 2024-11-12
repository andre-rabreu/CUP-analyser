// File for the scanner to be used by the parser
package parser;
// // Importing classes from CUP - class Symbol
import java_cup.runtime.*;

%%

%class Scanner
%cup
%unicode
%line
%column

%{
    // type is the token class yyline and yycolumn are reserved variables of JFlex to store
    // the line and column of a token found on input (needs to use %line and %column)
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

ws = [\ \t\f\r\n]
number = \d+(\.\d+)?(["E""e"]["+""-"]?\d+)?
identifier = [A-Za-z][A-Za-z0-9]*
string = (\")([^\"])*(\")

%%
";"         { return symbol(sym.SEMI); }
"+"         { return symbol(sym.PLUS); }
"-"         { return symbol(sym.MINUS); }
"**"        { return symbol(sym.EXP); }
"*"         { return symbol(sym.TIMES); }
"/"         { return symbol(sym.DIVIDE); }
"%"         { return symbol(sym.MOD); }
"("         { return symbol(sym.LPAREN); }
")"         { return symbol(sym.RPAREN); }
"{"         { return symbol(sym.LBRACE); }
"}"         { return symbol(sym.RBRACE); }
"="         { return symbol(sym.ASSIGN); }
">"         { return symbol(sym.GT); }
">="        { return symbol(sym.GTE); }
"<="        { return symbol(sym.LTE); }
"<"         { return symbol(sym.LT); }
"=="        { return symbol(sym.EQ); }
"!="        { return symbol(sym.NEQ); }
"if"        { return symbol(sym.IF); }
"else"      { return symbol(sym.ELSE); }
"while"     { return symbol(sym.WHILE); }
"sin"       { return symbol(sym.SIN); }
"cos"       { return symbol(sym.COS); }
"print"     { return symbol(sym.PRINT); }
"PI"        { return symbol(sym.PI, Math.PI); }
{number}    { return symbol(sym.NUMBER, Double.valueOf(yytext())); }
{string}    { return symbol(sym.STRING, yytext().substring(1, yytext().length()-1)); }
{identifier} { return symbol(sym.ID, yytext()); }
{ws}        {/* Ignore */}
.           { throw new Error("Invalid symbol detected: \"" + yycharat(0) +
              "\" ( line: " +  yyline + ", column: " + yycolumn + ")"); }
