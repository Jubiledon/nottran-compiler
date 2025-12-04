package uk.ac.nott.cs.comp3012.coursework.ast;

import java.util.List;

public record Write(List<Expr> exprs) implements Stmt { }
