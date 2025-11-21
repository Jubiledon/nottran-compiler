package uk.ac.nott.cs.comp3012.coursework.frontend;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import uk.ac.nott.cs.comp3012.coursework.NottscriptLexer;
import uk.ac.nott.cs.comp3012.coursework.NottscriptParser;
import uk.ac.nott.cs.comp3012.coursework.ast.Ast;

public class NottranFrontend implements Frontend{
    @Override
    public Ast runFrontend(String programText) {
        CharStream input = CharStreams.fromString(programText);
        NottscriptLexer lexer = new NottscriptLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        NottscriptParser parser = new NottscriptParser(tokens);

        ParseTree tree = parser.program();

        // TODO: Write your AST builder visitor
        AstBuilder builder = new AstBuilder();
        return builder.visit(tree);
    }
}
