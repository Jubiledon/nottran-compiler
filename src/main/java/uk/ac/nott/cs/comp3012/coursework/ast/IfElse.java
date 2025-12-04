package uk.ac.nott.cs.comp3012.coursework.ast;

import java.util.List;

public record IfElse(Expr condition, List<Stmt> thenBlock, List<Stmt> elseBlock) implements Stmt { }
