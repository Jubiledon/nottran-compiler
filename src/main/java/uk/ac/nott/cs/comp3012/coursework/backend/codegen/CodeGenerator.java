package uk.ac.nott.cs.comp3012.coursework.backend.codegen;

import uk.ac.nott.cs.comp3012.coursework.ast.Program;
import uk.ac.nott.cs.comp3012.coursework.backend.tam.TamInstruction.InstructionList;

public class CodeGenerator {

    private static final int PRIM_LT  = 13;
    private static final int PRIM_LE  = 14;
    private static final int PRIM_GE  = 15;
    private static final int PRIM_GT  = 16;
    private static final int PRIM_EQ  = 17;
    private static final int PRIM_NEQ = 18;

    /**
     * Generates TAM assembly for a complete Nottran program.
     * Builds the global variable table, allocates global storage,
     * emits code for all statements, and appends a final HALT.
     *
     * @param program the parsed Nottran AST
     * @return the generated TAM instruction list
     */
    public InstructionList generateProgram(Program program) {
        CodeGenContext ctx = new CodeGenContext(
                PRIM_LT, PRIM_LE, PRIM_GE, PRIM_GT, PRIM_EQ, PRIM_NEQ
        );
        ctx.globals.buildGlobalTable(program);
        ctx.emitter.emitPushGlobals(ctx.globals.totalGlobals());
        program.statements().forEach(ctx.stmts::emitStmt);
        ctx.emitter.emitHalt();
        ctx.emitter.dumpFinalCode();
        return ctx.emitter.getCode();
    }
}
