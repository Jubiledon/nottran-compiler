// Generated from C:/Users/jubil/Documents/university-of-nottingham/comp3012-compilers/Coursework starter kit/src/main/antlr/uk/ac/nott/cs/comp3012/coursework/Nottscript.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link NottscriptParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface NottscriptVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link NottscriptParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(NottscriptParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link NottscriptParser#decls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecls(NottscriptParser.DeclsContext ctx);
	/**
	 * Visit a parse tree produced by {@link NottscriptParser#decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl(NottscriptParser.DeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link NottscriptParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(NottscriptParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link NottscriptParser#idList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdList(NottscriptParser.IdListContext ctx);
	/**
	 * Visit a parse tree produced by {@link NottscriptParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(NottscriptParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link NottscriptParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(NottscriptParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link NottscriptParser#assignStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStmt(NottscriptParser.AssignStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link NottscriptParser#readStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadStmt(NottscriptParser.ReadStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link NottscriptParser#writeStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWriteStmt(NottscriptParser.WriteStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link NottscriptParser#exprList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprList(NottscriptParser.ExprListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleIf}
	 * labeled alternative in {@link NottscriptParser#ifStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleIf(NottscriptParser.SimpleIfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fullIf}
	 * labeled alternative in {@link NottscriptParser#ifStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFullIf(NottscriptParser.FullIfContext ctx);
	/**
	 * Visit a parse tree produced by {@link NottscriptParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(NottscriptParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link NottscriptParser#relExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelExpr(NottscriptParser.RelExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link NottscriptParser#relOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelOp(NottscriptParser.RelOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link NottscriptParser#addExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExpr(NottscriptParser.AddExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link NottscriptParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(NottscriptParser.PrimaryContext ctx);
}