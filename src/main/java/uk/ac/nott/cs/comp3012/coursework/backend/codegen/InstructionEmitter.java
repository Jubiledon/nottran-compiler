package uk.ac.nott.cs.comp3012.coursework.backend.codegen;

import uk.ac.nott.cs.comp3012.coursework.backend.tam.TamInstruction.Instruction;
import uk.ac.nott.cs.comp3012.coursework.backend.tam.TamInstruction.InstructionList;
import uk.ac.nott.cs.comp3012.coursework.backend.tam.TamOpcode;
import uk.ac.nott.cs.comp3012.coursework.backend.tam.TamRegister;

public class InstructionEmitter {

    private final InstructionList code = new InstructionList();

    public void emit(TamOpcode op, TamRegister r, int n, int d) {
        int idx = code.size();
        System.out.printf("%04d: %s %s n=%d d=%d%n", idx, op, r, n, d);
        code.add(new Instruction(op, r, n, d));
    }

    public void emitHalt() {
        emit(TamOpcode.HALT, TamRegister.CB, 0, 0);
    }

    public void emitPushGlobals(int count) {
        if (count > 0) {
            emit(TamOpcode.PUSH, TamRegister.CB, 0, count);
        }
    }

    public InstructionList getCode() {
        return code;
    }

    public Instruction get(int index) {
        return code.get(index);
    }

    public void set(int index, Instruction instr) {
        code.set(index, instr);
    }

    public int size() {
        return code.size();
    }

}
