package uk.ac.nott.cs.comp3012.coursework.semantic;

import uk.ac.nott.cs.comp3012.coursework.ast.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Semantic analyser for Class III features.
 *
 * Rules enforced:
 * - Variables must be declared before use.
 * - Declaration duplicates are reported.
 * - Assignments must match types.
 * - Read target must be variable and declared.
 * - Write operands must be INT or BOOL.
 * - Relational operators require INT operands and produce BOOL.
 * - If condition must be BOOL.
 */
public class SemanticAnalyser {

    // symbol table: var name -> Type
    private final Map<String, Type> symtab = new HashMap<>();
    private final SemanticResult result = new SemanticResult();

    public SemanticResult analyse(Program program) {
        // clear state
        symtab.clear();

        // process declarations
        for (Decl d : program.decls()) {
            declare(d);
        }

        // process statements
        for (Stmt s : program.statements()) {
            checkStmt(s);
        }

        return result;
    }

    private void declare(Decl d) {
        String rawType = d.type().toLowerCase();
        Type t;
        if (rawType.equals("integer")) t = Type.INT;
        else if (rawType.equals("logical")) t = Type.BOOL;
        else {
            result.addError("Unknown type in declaration: " + d.type());
            t = Type.UNKNOWN;
        }

        for (String id : d.ids()) {
            if (symtab.containsKey(id)) {
                result.addError("Duplicate declaration of variable '" + id + "'");
            } else {
                symtab.put(id, t);
            }
        }
    }

    private void checkStmt(Stmt s) {
        if (s instanceof Assign a) {
            checkAssign(a);
        } else if (s instanceof Read r) {
            checkRead(r);
        } else if (s instanceof Write w) {
            checkWrite(w);
        } else if (s instanceof If i) {
            checkIf(i);
        } else if (s instanceof IfElse ie) {
            checkIfElse(ie);
        } else {
            result.addError("Unknown statement type: " + s.getClass().getSimpleName());
        }
    }

    private void checkAssign(Assign a) {
        String name = a.id();
        if (!symtab.containsKey(name)) {
            result.addError("Assignment to undeclared variable '" + name + "'");
            // still check RHS for further errors
            checkExpr(a.expr());
            return;
        }
        Type declared = symtab.get(name);
        Type rhs = checkExpr(a.expr());
        if (declared != Type.UNKNOWN && rhs != Type.UNKNOWN && declared != rhs) {
            result.addError(String.format("Type mismatch in assignment to '%s': declared %s but assigned %s",
                    name, declared, rhs));
        }
    }

    private void checkRead(Read r) {
        for (Expr e : r.exprs()) {
            if (e instanceof Var v) {
                String name = v.name();
                if (!symtab.containsKey(name)) {
                    result.addError("Read into undeclared variable '" + name + "'");
                }
                // OK: reading into var of any declared type is allowed for Class III (int/logical)
            } else {
                result.addError("Read expects variable names, got expression: " + e.getClass().getSimpleName());
            }
        }
    }

    private void checkWrite(Write w) {
        for (Expr e : w.exprs()) {
            Type t = checkExpr(e);
            if (t == Type.UNKNOWN) continue;
            if (t != Type.INT && t != Type.BOOL) {
                result.addError("Write expects integer or logical expression, got: " + t);
            }
        }
    }

    private void checkIf(If i) {
        Type cond = checkExpr(i.condition());
        if (cond != Type.BOOL && cond != Type.UNKNOWN) {
            result.addError("If condition must be logical (.true./.false. or relational expression). Got: " + cond);
        }
        // thenStmt can be any single statement; check it
        checkStmt(i.thenStmt());
    }

    private void checkIfElse(IfElse ie) {
        Type cond = checkExpr(ie.condition());
        if (cond != Type.BOOL && cond != Type.UNKNOWN) {
            result.addError("If condition must be logical (.true./.false. or relational expression). Got: " + cond);
        }
        for (Stmt s : ie.thenBlock()) checkStmt(s);
        for (Stmt s : ie.elseBlock()) checkStmt(s);
    }

    /**
     * Check an expression and return its type (INT, BOOL or UNKNOWN).
     */
    private Type checkExpr(Expr e) {
        if (e instanceof IntLiteral) {
            return Type.INT;
        } else if (e instanceof BoolLiteral) {
            return Type.BOOL;
        } else if (e instanceof Var v) {
            String name = v.name();
            if (!symtab.containsKey(name)) {
                result.addError("Use of undeclared variable '" + name + "'");
                return Type.UNKNOWN;
            }
            return symtab.get(name);
        } else if (e instanceof RelOp rop) {
            // relational operators expect integer operands and produce BOOL
            Type left = checkExpr(rop.left());
            Type right = checkExpr(rop.right());
            if (left != Type.INT || right != Type.INT) {
                if (left != Type.UNKNOWN && right != Type.UNKNOWN)
                    result.addError("Relational operator '" + rop.op() + "' requires INTEGER operands (got " + left + " and " + right + ")");
                return Type.UNKNOWN;
            }
            return Type.BOOL;
        } else {
            result.addError("Unknown expression node: " + e.getClass().getSimpleName());
            return Type.UNKNOWN;
        }
    }
}
