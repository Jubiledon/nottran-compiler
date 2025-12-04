package uk.ac.nott.cs.comp3012.coursework.semantic;

/**
 * Thrown for unrecoverable semantic errors (optional).
 */
public class SemanticException extends RuntimeException {
    public SemanticException(String msg) { super(msg); }
}