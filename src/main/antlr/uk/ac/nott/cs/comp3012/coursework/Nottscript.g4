grammar Nottscript;

// =============================================================================
// GRAMMARS
// =============================================================================

program
    : PROGRAM ID decls block END PROGRAM ID
    ;

decls
    : ( decl )*
    ;

decl
    : type '::' idList
    ;

type
    : baseType dims?     // optional array dimensions
    ;

baseType
    : INTEGER
    | REAL
    | CHARACTER
    | LOGICAL
    ;

// dims: a parenthesised list of integers or expressions
dims
    : LPAREN dimensionList RPAREN
    ;

// list of dimensions (expressions allowed)
dimensionList
    : expr (COMMA expr)*
    ;

idList
    : ID (COMMA ID)*
    ;

block
    : stmt*
    ;

stmt
    : assignStmt
    | readStmt
    | writeStmt
    | ifStmt
    ;

assignStmt
    : ID ASSIGN expr
    | designator ASSIGN expr
    ;

readStmt
    : READ exprList
    ;

writeStmt
    : WRITE exprList
    ;

exprList
    : expr (',' expr)*
    ;

// ----------------------
// IF statements
// ----------------------

ifStmt
    : IF LPAREN expr RPAREN stmt                        # simpleIf
    | IF LPAREN expr RPAREN THEN block ELSE block ENDIF # fullIf
    ;

// ----------------------
// Expressions
// ----------------------

expr
    : relExpr
    ;

relExpr
    : addExpr (relOp addExpr)?
    ;

relOp
    : LT | LE | GT | GE | EQ | NEQ
    ;

addExpr
    : primary
    ;

primary
    : INTLIT
    | TRUE
    | FALSE
    | ID
    | LPAREN expr RPAREN
    ;

// ----------------------
// Class II-2
// ----------------------

designator
    : simpleDesignator ( PERCENT ID ( LPAREN exprList? RPAREN )? )*
    ;

// a simpleDesignator is either a bare name or an array indexing
simpleDesignator
    : ID ( '(' exprList? ')' )?
    ;

// =============================================================================
// SCANNER RULES
// =============================================================================

// -----------
// Class III
// -----------

PROGRAM : 'program';
END     : 'end';
INTEGER : 'integer';
LOGICAL : 'logical';
READ    : 'read';
WRITE   : 'write';
IF      : 'if';
THEN    : 'then';
ELSE    : 'else';
ENDIF   : 'end if';

TRUE    : '.true.';
FALSE   : '.false.';

// Relational Operators
LT      : '<' | '.lt.';
LE      : '<=' | '.le.';
GT      : '>' | '.gt.';
GE      : '>=' | '.ge.';
EQ      : '==' | '.eq.';
NEQ     : '/=' | '.neq.';

ASSIGN  : '=';

LPAREN  : '(';
RPAREN  : ')';
COMMA   : ',';

INTLIT  : [0-9]+;
ID      : [A-Za-z_][A-Za-z_0-9]*;

WS      : [ \t\r\n]+ -> skip;
PERCENT : '%' ;
REAL      : 'real';
CHARACTER : 'character';