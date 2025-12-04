package uk.ac.nott.cs.comp3012.coursework.backend.codegen;

import uk.ac.nott.cs.comp3012.coursework.ast.*;
import uk.ac.nott.cs.comp3012.coursework.backend.tam.TamOpcode;
import uk.ac.nott.cs.comp3012.coursework.backend.tam.TamRegister;
import uk.ac.nott.cs.comp3012.coursework.backend.tam.TamInstruction.Instruction;


public class StmtEmitter {

    private final InstructionEmitter emitter;
    private final ExprEmitter exprEmitter;
    private final GlobalAllocator globals;
    private final JumpUtils jumps;

    public StmtEmitter(
            InstructionEmitter emitter,
            ExprEmitter exprEmitter,
            GlobalAllocator globals,
            JumpUtils jumps) {

        this.emitter = emitter;
        this.exprEmitter = exprEmitter;
        this.globals = globals;
        this.jumps = jumps;
    }

    public void emitStmt(Stmt s) {
        switch (s) {
            case Assign a  -> emitAssign(a);
            case Read r    -> emitRead(r);
            case Write w   -> emitWrite(w);
            case If i      -> emitIf(i);
            case IfElse ie -> emitIfElse(ie);
            default -> throw new IllegalStateException("Unknown Stmt");
        }
    }

    private void emitAssign(Assign a) {
        int addr = globals.requireGlobal(a.id());
        exprEmitter.emitExpr(a.expr());
        emitter.emit(TamOpcode.STORE, TamRegister.SB, 1, addr);
    }

    private void emitRead(Read r) {
        for (Expr e : r.exprs()) {
            if (!(e instanceof Var v))
                throw new IllegalStateException("Read expects variable");

            int addr = globals.requireGlobal(v.name());
            emitter.emit(TamOpcode.CALL, TamRegister.PB, 0, 25); // GETINT
            emitter.emit(TamOpcode.LOADL, TamRegister.CB, 1, addr);
        }
    }

    private void emitWrite(Write w) {
        for (Expr e : w.exprs()) {
            exprEmitter.emitExpr(e);
            emitter.emit(TamOpcode.CALL, TamRegister.PB, 1, 26); // PUTINT
        }
    }

    private void emitIf(If i) {
        exprEmitter.emitExpr(i.condition());
        int jmp = jumps.reserveJump(TamOpcode.JUMPIF);
        emitStmt(i.thenStmt());
        jumps.patchJump(jmp, emitter.size(), TamOpcode.JUMPIF);
    }

    private void emitIfElse(IfElse ie) {
        exprEmitter.emitExpr(ie.condition());
        int jumpOverThen = reserveJumpIfFalse();
        emitThenBlock(ie);
        int jumpOverElse = reserveUnconditionalJump();
        patchJumpIfFalse(jumpOverThen);
        emitElseBlock(ie);
        patchUnconditionalJump(jumpOverElse);
    }

    private int reserveJumpIfFalse() {
        int pos = emitter.size();
        emitter.emit(TamOpcode.JUMPIF, TamRegister.CB, 0, 0);
        return pos;
    }

    private int reserveUnconditionalJump() {
        int pos = emitter.size();
        emitter.emit(TamOpcode.JUMP, TamRegister.CB, 0, 0);
        return pos;
    }

    private void emitThenBlock(IfElse ie) {
        for (Stmt s : ie.thenBlock()) {
            emitStmt(s);
        }
    }

    private void emitElseBlock(IfElse ie) {
        for (Stmt s : ie.elseBlock()) {
            emitStmt(s);
        }
    }

    private void patchJumpIfFalse(int jumpIndex) {
        jumps.patchJump(jumpIndex, emitter.size(), TamOpcode.JUMPIF);
    }

    private void patchUnconditionalJump(int jumpIndex) {
        jumps.patchJump(jumpIndex, emitter.size(), TamOpcode.JUMP);
    }


}
