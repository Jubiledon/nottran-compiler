package uk.ac.nott.cs.comp3012.coursework.frontend;
import uk.ac.nott.cs.comp3012.coursework.ast.Ast;

/**
 * A compiler frontend converts the source program to an IR.
 */
@FunctionalInterface
public interface Frontend {

    /**
     * Generate IR for a source program.
     *
     * @param programText program to process
     * @return the IR
     */
    Ast runFrontend(String programText);
}