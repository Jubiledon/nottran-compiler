package uk.ac.nott.cs.comp3012.coursework.backend.codegen;

import uk.ac.nott.cs.comp3012.coursework.ast.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class GlobalAllocator {

    private final Map<String, Integer> globals = new LinkedHashMap<>();
    private int nextGlobal = 0;

    /** Build global table from program declarations */
    public void buildGlobalTable(Program program) {
        for (Decl d : program.decls()) {
            for (String id : d.ids()) {
                if (!globals.containsKey(id)) {
                    globals.put(id, nextGlobal++);
                }
            }
        }
    }

    /** Lookup a variable offset, or throw if missing */
    public int requireGlobal(String name) {
        Integer addr = globals.get(name);
        if (addr == null)
            throw new IllegalStateException("Undefined variable: " + name);
        return addr;
    }

    public int totalGlobals() {
        return nextGlobal;
    }
}
