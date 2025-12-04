package uk.ac.nott.cs.comp3012.coursework.backend.codegen;

import uk.ac.nott.cs.comp3012.coursework.backend.tam.TamInstruction;

public class CodeGenContext {

    public final GlobalAllocator globals;
    public final InstructionEmitter emitter;
    public final JumpUtils jumps;
    public final ExprEmitter exprs;
    public final StmtEmitter stmts;

    public CodeGenContext() {
        this.globals = new GlobalAllocator();
        this.emitter = new InstructionEmitter();
        this.jumps = new JumpUtils(emitter);
        this.exprs = new ExprEmitter(emitter, globals);
        this.stmts = new StmtEmitter(emitter, exprs, globals, jumps);
    }

    public void dumpCode() {
        System.out.println("---- Current instruction list ----");
        for (int i = 0; i < emitter.size(); i++) {
            TamInstruction.Instruction inst = emitter.get(i);
            System.out.printf("%04d: %s %s n=%d d=%d%n",
                    i,
                    inst.op(),
                    inst.r(),
                    inst.n(),
                    inst.d());
        }
        System.out.println("---- End of instruction list ----");
    }

}
