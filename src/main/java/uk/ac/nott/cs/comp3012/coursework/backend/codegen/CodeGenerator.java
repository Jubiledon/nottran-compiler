package uk.ac.nott.cs.comp3012.coursework.backend.codegen;

import uk.ac.nott.cs.comp3012.coursework.ast.Program;
import uk.ac.nott.cs.comp3012.coursework.backend.tam.TamInstruction.InstructionList;

public class CodeGenerator {

    /**
     * Generates TAM assembly for a complete Nottran program.
     * Builds the global variable table, allocates global storage,
     * emits code for all statements, and appends a final HALT.
     *
     * @param program the parsed Nottran AST
     * @return the generated TAM instruction list
     */
    public InstructionList generateProgram(Program program) {
        CodeGenContext ctx = new CodeGenContext();
        ctx.globals.buildGlobalTable(program);
        ctx.emitter.emitPushGlobals(ctx.globals.totalGlobals());
        program.statements().forEach(ctx.stmts::emitStmt);
        ctx.emitter.emitHalt();
        ctx.dumpCode();
        return ctx.emitter.getCode();
    }
}
