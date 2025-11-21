package uk.ac.nott.cs.comp3012.coursework.backend;

import uk.ac.nott.cs.comp3012.coursework.ast.Ast;

public class NottranBackend implements Backend{
    @Override
    public byte[] runBackend(Ast program) {
        return new byte[0];
    }
}
