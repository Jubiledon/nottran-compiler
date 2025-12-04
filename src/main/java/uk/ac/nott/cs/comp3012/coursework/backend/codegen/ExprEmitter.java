package uk.ac.nott.cs.comp3012.coursework.backend.codegen;

import uk.ac.nott.cs.comp3012.coursework.ast.*;
import uk.ac.nott.cs.comp3012.coursework.backend.tam.TamOpcode;
import uk.ac.nott.cs.comp3012.coursework.backend.tam.TamRegister;

public class ExprEmitter {

    private final InstructionEmitter emitter;
    private final GlobalAllocator globals;

    private final int PRIM_LT;
    private final int PRIM_LE;
    private final int PRIM_GE;
    private final int PRIM_GT;
    private final int PRIM_EQ;
    private final int PRIM_NEQ;

    public ExprEmitter(InstructionEmitter emitter,
                       GlobalAllocator globals,
                       int primLT, int primLE, int primGE, int primGT, int primEQ, int primNEQ) {
        this.emitter = emitter;
        this.globals = globals;
        this.PRIM_LT = primLT;
        this.PRIM_LE = primLE;
        this.PRIM_GE = primGE;
        this.PRIM_GT = primGT;
        this.PRIM_EQ = primEQ;
        this.PRIM_NEQ = primNEQ;
    }

    public void emitExpr(Expr e) {
        switch (e) {
            case IntLiteral il  -> emitLoadLiteral(il.value());
            case BoolLiteral bl -> emitLoadLiteral(bl.value() ? 1 : 0);
            case Var v          -> emitVar(v);
            case RelOp rop      -> emitRelOp(rop);
            default -> throw new IllegalStateException("Unknown Expr");
        }
    }

    private void emitVar(Var v) {
        int addr = globals.requireGlobal(v.name());
        emitter.emit(TamOpcode.LOAD, TamRegister.SB, 1, addr);
    }

    private void emitRelOp(RelOp rop) {
        emitExpr(rop.left());
        emitExpr(rop.right());
        emitter.emit(TamOpcode.CALL, TamRegister.PB, 2,
                primForRelOp(rop.op()));
    }

    private int primForRelOp(String op) {
        return switch (op.strip().toLowerCase()) {
            case ".lt.", "<"  -> PRIM_LT;
            case ".le.", "<=" -> PRIM_LE;
            case ".ge.", ">=" -> PRIM_GE;
            case ".gt.", ">"  -> PRIM_GT;
            case ".eq.", "==" -> PRIM_EQ;
            case ".neq.", "/=", "!=" -> PRIM_NEQ;
            default -> throw new IllegalArgumentException("Unknown relational operator: " + op);
        };
    }

    private void emitLoadLiteral(int value) {
        emitter.emit(TamOpcode.LOADL, TamRegister.CB, 0, value);
    }
}
