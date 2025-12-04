package uk.ac.nott.cs.comp3012.coursework.backend.codegen;

import uk.ac.nott.cs.comp3012.coursework.backend.tam.TamOpcode;
import uk.ac.nott.cs.comp3012.coursework.backend.tam.TamRegister;
import uk.ac.nott.cs.comp3012.coursework.backend.tam.TamInstruction.Instruction;

public class JumpUtils {

    private final InstructionEmitter emitter;

    public JumpUtils(InstructionEmitter emitter) {
        this.emitter = emitter;
    }

    /**
     * Creates a placeholder forward jump instruction using the given opcode.
     * It will later be patched to jump to the current end of code.
     */
    public int createForwardJump(TamOpcode opcode) {
        int index = emitter.size();
        emitter.emit(opcode, TamRegister.CB, 0, 0);
        return index;
    }

    /**
     * Patches a previously created forward jump so that it jumps
     * to the current end of the instruction stream.
     */
    public void patchForwardJump(int jumpIndex) {
        Instruction old = emitter.get(jumpIndex);
        int targetIndex = emitter.size();
        int displacement = targetIndex - (jumpIndex + 1);
        System.out.printf(
                "Patching %s at idx=%d -> targetIdx=%d displacement=%d (from=%d to=%d)%n",
                old.op(),            // opcode from the original jump
                jumpIndex,           // index of the jump instruction
                targetIndex,         // where it will jump to
                displacement,        // computed displacement
                jumpIndex + 1,       // first instruction after the jump
                targetIndex          // same as target
        );        emitter.set(jumpIndex,
        new Instruction(old.op(), old.r(), old.n(), targetIndex));
    }
}
