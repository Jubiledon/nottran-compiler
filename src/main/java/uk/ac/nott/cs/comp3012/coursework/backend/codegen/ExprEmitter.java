package uk.ac.nott.cs.comp3012.coursework.backend.codegen;

import uk.ac.nott.cs.comp3012.coursework.ast.*;
import uk.ac.nott.cs.comp3012.coursework.backend.tam.TamOpcode;
import uk.ac.nott.cs.comp3012.coursework.backend.tam.TamPrimitive;
import uk.ac.nott.cs.comp3012.coursework.backend.tam.TamRegister;

public class ExprEmitter {

    private final InstructionEmitter emitter;
    private final GlobalAllocator globals;

    public ExprEmitter(InstructionEmitter emitter, GlobalAllocator globals) {
        this.emitter = emitter;
        this.globals = globals;
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

    private void emitRelOp(RelOp relOp) {
        emitExpr(relOp.left());
        emitExpr(relOp.right());
        TamPrimitive prim = relationalOperatorToPrimitive(relOp.opSymbol());
        emitter.emit(TamOpcode.CALL, TamRegister.PB, 0, prim.disp);
    }

    private TamPrimitive relationalOperatorToPrimitive(String operator) {
        return switch (operator.strip().toLowerCase()) {
            case ".lt.", "<"         -> TamPrimitive.LT;
            case ".le.", "<="        -> TamPrimitive.LE;
            case ".ge.", ">="        -> TamPrimitive.GE;
            case ".gt.", ">"         -> TamPrimitive.GT;
            case ".eq.", "=="        -> TamPrimitive.EQ;
            case ".neq.", "/=", "!=" -> TamPrimitive.NE;
            default -> throw new IllegalArgumentException("Unknown relational operator: " + operator);
        };
    }

    private void emitLoadLiteral(int value) {
        emitter.emit(TamOpcode.LOADL, TamRegister.CB, 0, value);
    }
}
