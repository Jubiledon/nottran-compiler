package uk.ac.nott.cs.comp3012.coursework.ast;

import java.util.List;

public record Decl(String type, List<String> ids) implements Ast { }
