package uk.ac.nott.cs.comp3012.coursework.backend.tam;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;

public interface TamInstruction {

    /**
     * Represent this instruction as an array of bytes in TAM bytecode format.
     *
     * @return the bytes
     */
    byte[] toByteArray();


    record Instruction(TamOpcode op, TamRegister r, int n, int d) implements TamInstruction {

        public byte[] toByteArray() {
            return new byte[]{(byte) ((op.value << 4) | r.value), (byte) n,
                    (byte) ((d & 0xff00) >>> 8), (byte) (d & 0xff),};
        }

        @Override
        public String toString() {
            return switch (op) {
                case LOADL -> String.format("LOADL %d", d);
                case CALL  -> String.format("CALL %s[%d]", r, d);
                case JUMPIF -> String.format("JUMPIF %d", d);
                case JUMP -> String.format("JUMP %d", d);
                case HALT -> "HALT";
                default -> String.format("%s %d[%s] (n=%d)", op, d, r, n);
            };
        }
    }

    final class InstructionList extends ArrayList<Instruction> implements TamInstruction {

        public InstructionList() {
            super();
        }

        public InstructionList(Collection<Instruction> elems) {
            super(elems);
        }

        @Override
        public byte[] toByteArray() {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            for (Instruction instr : this) {
                out.writeBytes(instr.toByteArray());
            }
            return out.toByteArray();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Instruction instr : this) {
                sb.append(instr.toString()).append("\n");
            }
            return sb.toString();
        }

    }

}
