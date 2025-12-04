package uk.ac.nott.cs.comp3012.coursework.ast;

import java.util.List;

public record Read(List<Expr> exprs) implements Stmt { }
