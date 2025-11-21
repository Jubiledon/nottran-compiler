package uk.ac.nott.cs.comp3012.coursework.backend;
import uk.ac.nott.cs.comp3012.coursework.ast.Ast;

/**
 * A compiler backend converts IR of a program into bytes of machine code.
 */
@FunctionalInterface
public interface Backend {

    /**
     * Generate machine code from an IR.
     *
     * @param program IR to transform
     * @return the bytes
     */
    byte[] runBackend(Ast program);
}