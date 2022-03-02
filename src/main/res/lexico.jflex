package edu.mooncoder.model.analizer.lexic;

import java_cup.runtime.Symbol;
import edu.mooncoder.model.analizer.syntax.Tokens;

%%

%class Lexer
%unicode
%cupsym Tokens
%cup
%public

%{
  private int pos = 2;
  
  private Symbol symbol(int type) {
    if (type == Tokens.BREAKLINE) {
      pos = 2;
    }
    return new Symbol(type, yyline + 1, yycolumn + 1);
  }

  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline + 1, yycolumn + 1, value);
  }
%}

Caballo = [0-9] [0-9]?
Monto = [0-9]+ "." [0-9] [0-9]
Nombre = [^ ,\n\r\t\f.0-9] [^,\n\r\t\f.]+
Breakline = \n | \r | \r\n

%state LITERAL

%%

<YYINITIAL> {

  {Nombre}          { return symbol(Tokens.APOSTADOR, yytext()); }

  {Monto}           { return symbol(Tokens.MONTO, Double.parseDouble(yytext())); }

  {Caballo}         { return symbol(Tokens.CABALLO, Integer.parseInt(yytext()) - 1); }

  ","               { return symbol(Tokens.COMA); }

  {Breakline}       { return symbol(Tokens.BREAKLINE); }

  [ \t\f]           { /* *Lo ignora* */ }

}

[^] { System.out.println(String.format("Caracter desconocido '%s'.", yytext())); }

