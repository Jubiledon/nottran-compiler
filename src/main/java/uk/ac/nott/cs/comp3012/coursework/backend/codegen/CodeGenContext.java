package uk.ac.nott.cs.comp3012.coursework.backend.codegen;

public class CodeGenContext {

    public final GlobalAllocator globals;
    public final InstructionEmitter emitter;
    public final JumpUtils jumps;
    public final ExprEmitter exprs;
    public final StmtEmitter stmts;

    public CodeGenContext(
            int primLT, int primLE, int primGE,
            int primGT, int primEQ, int primNEQ) {

        this.globals = new GlobalAllocator();
        this.emitter = new InstructionEmitter();
        this.jumps = new JumpUtils(emitter);

        this.exprs = new ExprEmitter(
                emitter, globals,
                primLT, primLE, primGE, primGT, primEQ, primNEQ
        );

        this.stmts = new StmtEmitter(
                emitter, exprs, globals, jumps
        );
    }
}
