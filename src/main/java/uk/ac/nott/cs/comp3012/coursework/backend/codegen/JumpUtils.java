package uk.ac.nott.cs.comp3012.coursework.backend.codegen;

import uk.ac.nott.cs.comp3012.coursework.backend.tam.TamOpcode;
import uk.ac.nott.cs.comp3012.coursework.backend.tam.TamRegister;
import uk.ac.nott.cs.comp3012.coursework.backend.tam.TamInstruction.Instruction;

public class JumpUtils {

    private final InstructionEmitter emitter;

    public JumpUtils(InstructionEmitter emitter) {
        this.emitter = emitter;
    }

    public int reserveJump(TamOpcode op) {
        int index = emitter.size();
        emitter.emit(op, TamRegister.CB, 0, 0);
        return index;
    }

    public void patchJump(int jumpIndex, int targetIndex, TamOpcode op) {
        Instruction old = emitter.get(jumpIndex);
        int displacement = computeInstructionWordsToSkip(jumpIndex + 1, targetIndex);
        // debug: show what we're about to patch
        System.out.printf("Patching %s at idx=%d -> targetIdx=%d displacement=%d (from=%d to=%d)%n",
                op, jumpIndex, targetIndex, displacement, jumpIndex + 1, targetIndex);
        emitter.set(jumpIndex, new Instruction(op, old.r(), old.n(), displacement));
    }

    public int computeInstructionWordsToSkip(int from, int to) {
        return to - from;
    }

    public int computeInstructionByteOffset(int index) {
        return index;
    }
}
