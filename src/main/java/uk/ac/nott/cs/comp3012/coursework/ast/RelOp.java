package uk.ac.nott.cs.comp3012.coursework.ast;

public record RelOp(String opSymbol, Expr left, Expr right) implements Expr { }
