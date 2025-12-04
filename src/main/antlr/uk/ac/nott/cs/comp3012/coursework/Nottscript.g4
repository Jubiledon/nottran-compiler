grammar Nottscript;

// =============================================================================
// GRAMMARS
// =============================================================================

// Program definition
program : PROGRAM ID decls block END PROGRAM ID ;

// Declarations
decls : decl* ;

decl  : type '::' idList ;

type  : INTEGER | LOGICAL ;

idList : ID (',' ID)* ;

// Block structure
block : stmt* ;

// Statements
stmt : assignStmt | readStmt | writeStmt | ifStmt ;

// Assignment statement
assignStmt : ID ASSIGN expr ;

// Read statement
readStmt : READ exprList ;

// Write statement
writeStmt : WRITE exprList ;

// Expression list for Read and Write
exprList : expr (',' expr)* ;

// =============================================================================
// IF Statements
// =============================================================================

// Simple If statement
ifStmt : IF LPAREN expr RPAREN stmt # simpleIf

        // Full If statement with THEN and ELSE
      | IF LPAREN expr RPAREN THEN block ELSE block ENDIF # fullIf ;

// =============================================================================
// Expressions
// =============================================================================

// Relational expressions
expr : relExpr ;

relExpr : addExpr (relOp addExpr)? ;

relOp : LT | LE | GT | GE | EQ | NEQ ;

addExpr : primary ;

primary : INTLIT | TRUE | FALSE | ID | LPAREN expr RPAREN ;

// =============================================================================
// SCANNER RULES
// =============================================================================

// Reserved keywords
PROGRAM  : 'program' ;
END      : 'end' ;
INTEGER  : 'integer' ;
LOGICAL  : 'logical' ;
READ     : 'read' ;
WRITE    : 'write' ;
IF       : 'if' ;
THEN     : 'then' ;
ELSE     : 'else' ;
ENDIF    : 'end if' ;
TRUE     : '.true.' ;
FALSE    : '.false.' ;

// Relational operators
LT  : '<' | '.lt.' ;
LE  : '<=' | '.le.' ;
GT  : '>' | '.gt.' ;
GE  : '>=' | '.ge.' ;
EQ  : '==' | '.eq.' ;
NEQ : '/=' | '.neq.' ;

// Other symbols
ASSIGN : '=' ;
LPAREN : '(' ;
RPAREN : ')' ;
COMMA  : ',' ;

// Literals
INTLIT : [0-9]+ ;
ID     : [A-Za-z_][A-Za-z_0-9]* ;

// Whitespace (skip it)
WS : [ \t\r\n]+ -> skip ;
