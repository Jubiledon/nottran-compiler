package uk.ac.nott.cs.comp3012.coursework.ast;

import java.util.List;

public record Program(String name, List<Decl> decls, List<Stmt> statements) implements Ast { }
