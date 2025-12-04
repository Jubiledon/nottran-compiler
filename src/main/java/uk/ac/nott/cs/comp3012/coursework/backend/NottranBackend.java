package uk.ac.nott.cs.comp3012.coursework.backend;

import uk.ac.nott.cs.comp3012.coursework.ast.Ast;
import uk.ac.nott.cs.comp3012.coursework.ast.Program;
import uk.ac.nott.cs.comp3012.coursework.backend.codegen.CodeGenerator;
import uk.ac.nott.cs.comp3012.coursework.backend.tam.TamInstruction;

public class NottranBackend implements Backend{
    @Override
    public byte[] runBackend(Ast ast) {
        if (!(ast instanceof Program prog)) {
            throw new IllegalArgumentException("Backend expected Program AST node");
        }

        CodeGenerator gen = new CodeGenerator();
        TamInstruction.InstructionList instrs = gen.generateProgram(prog);

        System.out.println("TAM Assembly:");
        System.out.println(instrs);

        return instrs.toByteArray();
    }
}
