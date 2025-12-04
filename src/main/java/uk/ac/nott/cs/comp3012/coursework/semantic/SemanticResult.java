package uk.ac.nott.cs.comp3012.coursework.semantic;

import java.util.ArrayList;
import java.util.List;

public class SemanticResult {
    private final List<String> errors = new ArrayList<>();

    public void addError(String err) { errors.add(err); }
    public boolean hasErrors() { return !errors.isEmpty(); }
    public List<String> getErrors() { return errors; }

    public void printErrors() {
        for (String e : errors) System.err.println("Semantic error: " + e);
    }
}