package uk.ac.nott.cs.comp3012.coursework.ast;

public record Assign(String id, Expr expr) implements Stmt { }
