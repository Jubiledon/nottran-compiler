// Generated from C:/Users/jubil/Documents/university-of-nottingham/comp3012-compilers/Coursework starter kit/src/main/antlr/uk/ac/nott/cs/comp3012/coursework/Nottscript.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link NottscriptParser}.
 */
public interface NottscriptListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link NottscriptParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(NottscriptParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link NottscriptParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(NottscriptParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link NottscriptParser#decls}.
	 * @param ctx the parse tree
	 */
	void enterDecls(NottscriptParser.DeclsContext ctx);
	/**
	 * Exit a parse tree produced by {@link NottscriptParser#decls}.
	 * @param ctx the parse tree
	 */
	void exitDecls(NottscriptParser.DeclsContext ctx);
	/**
	 * Enter a parse tree produced by {@link NottscriptParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(NottscriptParser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link NottscriptParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(NottscriptParser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link NottscriptParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(NottscriptParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link NottscriptParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(NottscriptParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link NottscriptParser#idList}.
	 * @param ctx the parse tree
	 */
	void enterIdList(NottscriptParser.IdListContext ctx);
	/**
	 * Exit a parse tree produced by {@link NottscriptParser#idList}.
	 * @param ctx the parse tree
	 */
	void exitIdList(NottscriptParser.IdListContext ctx);
	/**
	 * Enter a parse tree produced by {@link NottscriptParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(NottscriptParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link NottscriptParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(NottscriptParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link NottscriptParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(NottscriptParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link NottscriptParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(NottscriptParser.StmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link NottscriptParser#assignStmt}.
	 * @param ctx the parse tree
	 */
	void enterAssignStmt(NottscriptParser.AssignStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link NottscriptParser#assignStmt}.
	 * @param ctx the parse tree
	 */
	void exitAssignStmt(NottscriptParser.AssignStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link NottscriptParser#readStmt}.
	 * @param ctx the parse tree
	 */
	void enterReadStmt(NottscriptParser.ReadStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link NottscriptParser#readStmt}.
	 * @param ctx the parse tree
	 */
	void exitReadStmt(NottscriptParser.ReadStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link NottscriptParser#writeStmt}.
	 * @param ctx the parse tree
	 */
	void enterWriteStmt(NottscriptParser.WriteStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link NottscriptParser#writeStmt}.
	 * @param ctx the parse tree
	 */
	void exitWriteStmt(NottscriptParser.WriteStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link NottscriptParser#exprList}.
	 * @param ctx the parse tree
	 */
	void enterExprList(NottscriptParser.ExprListContext ctx);
	/**
	 * Exit a parse tree produced by {@link NottscriptParser#exprList}.
	 * @param ctx the parse tree
	 */
	void exitExprList(NottscriptParser.ExprListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simpleIf}
	 * labeled alternative in {@link NottscriptParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void enterSimpleIf(NottscriptParser.SimpleIfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simpleIf}
	 * labeled alternative in {@link NottscriptParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void exitSimpleIf(NottscriptParser.SimpleIfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fullIf}
	 * labeled alternative in {@link NottscriptParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void enterFullIf(NottscriptParser.FullIfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fullIf}
	 * labeled alternative in {@link NottscriptParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void exitFullIf(NottscriptParser.FullIfContext ctx);
	/**
	 * Enter a parse tree produced by {@link NottscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(NottscriptParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link NottscriptParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(NottscriptParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link NottscriptParser#relExpr}.
	 * @param ctx the parse tree
	 */
	void enterRelExpr(NottscriptParser.RelExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link NottscriptParser#relExpr}.
	 * @param ctx the parse tree
	 */
	void exitRelExpr(NottscriptParser.RelExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link NottscriptParser#relOp}.
	 * @param ctx the parse tree
	 */
	void enterRelOp(NottscriptParser.RelOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link NottscriptParser#relOp}.
	 * @param ctx the parse tree
	 */
	void exitRelOp(NottscriptParser.RelOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link NottscriptParser#addExpr}.
	 * @param ctx the parse tree
	 */
	void enterAddExpr(NottscriptParser.AddExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link NottscriptParser#addExpr}.
	 * @param ctx the parse tree
	 */
	void exitAddExpr(NottscriptParser.AddExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link NottscriptParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(NottscriptParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link NottscriptParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(NottscriptParser.PrimaryContext ctx);
}