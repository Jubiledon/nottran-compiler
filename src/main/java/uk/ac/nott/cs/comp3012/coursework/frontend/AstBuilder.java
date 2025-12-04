package uk.ac.nott.cs.comp3012.coursework.frontend;

import org.antlr.v4.runtime.tree.ParseTree;
import uk.ac.nott.cs.comp3012.coursework.NottscriptBaseVisitor;
import uk.ac.nott.cs.comp3012.coursework.NottscriptParser;
import uk.ac.nott.cs.comp3012.coursework.ast.*;

import java.util.ArrayList;
import java.util.List;

public class AstBuilder extends NottscriptBaseVisitor<Ast> {
    // --------------------------------------------------------
    // PROGRAM
    // --------------------------------------------------------
    @Override
    public Program visitProgram(NottscriptParser.ProgramContext ctx) {

        String name = ctx.ID(0).getText();   // program name

        List<Decl> decls = new ArrayList<>();
        for (var d : ctx.decls().decl()) {
            decls.add((Decl) visit(d));
        }

        List<Stmt> statements = new ArrayList<>();
        for (var s : ctx.block().stmt()) {
            statements.add((Stmt) visit(s));
        }

        return new Program(name, decls, statements);
    }

    // --------------------------------------------------------
    // DECLARATIONS
    // --------------------------------------------------------
    @Override
    public Decl visitDecl(NottscriptParser.DeclContext ctx) {

        String type = ctx.type().getText();

        List<String> ids = new ArrayList<>();
        for (var idToken : ctx.idList().ID()) {
            ids.add(idToken.getText());
        }

        return new Decl(type, ids);
    }

    // --------------------------------------------------------
    // STATEMENTS
    // --------------------------------------------------------

    @Override
    public Stmt visitAssignStmt(NottscriptParser.AssignStmtContext ctx) {
        String id = ctx.ID().getText();
        Expr expr = (Expr) visit(ctx.expr());
        return new Assign(id, expr);
    }

    @Override
    public Stmt visitReadStmt(NottscriptParser.ReadStmtContext ctx) {
        List<Expr> exprs = new ArrayList<>();
        for (var e : ctx.exprList().expr()) {
            exprs.add((Expr) visit(e));
        }
        return new Read(exprs);
    }

    @Override
    public Stmt visitWriteStmt(NottscriptParser.WriteStmtContext ctx) {
        List<Expr> exprs = new ArrayList<>();
        for (var e : ctx.exprList().expr()) {
            exprs.add((Expr) visit(e));
        }
        return new Write(exprs);
    }

    // IF statements
    @Override
    public Stmt visitSimpleIf(NottscriptParser.SimpleIfContext ctx) {
        Expr condition = (Expr) visit(ctx.expr());
        Stmt thenStmt = (Stmt) visit(ctx.stmt());
        return new If(condition, thenStmt);
    }

    @Override
    public Stmt visitFullIf(NottscriptParser.FullIfContext ctx) {

        Expr condition = (Expr) visit(ctx.expr());

        List<Stmt> thenBlock = new ArrayList<>();
        for (var s : ctx.block(0).stmt()) {
            thenBlock.add((Stmt) visit(s));
        }

        List<Stmt> elseBlock = new ArrayList<>();
        for (var s : ctx.block(1).stmt()) {
            elseBlock.add((Stmt) visit(s));
        }

        return new IfElse(condition, thenBlock, elseBlock);
    }

    // --------------------------------------------------------
    // EXPRESSIONS
    // --------------------------------------------------------

    @Override
    public Expr visitExpr(NottscriptParser.ExprContext ctx) {
        return (Expr) visit(ctx.relExpr());
    }

    @Override
    public Expr visitRelExpr(NottscriptParser.RelExprContext ctx) {
        Expr left = (Expr) visit(ctx.addExpr(0));

        if (ctx.relOp() != null) {
            String op = ctx.relOp().getText().toLowerCase();
            Expr right = (Expr) visit(ctx.addExpr(1));
            return new RelOp(op, left, right);
        }

        return left;
    }

    @Override
    public Expr visitAddExpr(NottscriptParser.AddExprContext ctx) {
        return (Expr) visit(ctx.primary());
    }

    @Override
    public Expr visitPrimary(NottscriptParser.PrimaryContext ctx) {

        if (ctx.INTLIT() != null)
            return new IntLiteral(Integer.parseInt(ctx.INTLIT().getText()));

        if (ctx.TRUE() != null)
            return new BoolLiteral(true);

        if (ctx.FALSE() != null)
            return new BoolLiteral(false);

        if (ctx.ID() != null)
            return new Var(ctx.ID().getText());

        // parenthesised expression
        return (Expr) visit(ctx.expr());
    }
}
