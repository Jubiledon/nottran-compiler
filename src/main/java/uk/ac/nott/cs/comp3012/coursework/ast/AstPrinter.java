package uk.ac.nott.cs.comp3012.coursework.ast;

public class AstPrinter {

    private int indent = 0;

    private void pad() {
        System.out.print(" ".repeat(indent));
    }

    public void print(Ast node) {
        if (node instanceof Program p) printProgram(p);
        else if (node instanceof Decl d) printDecl(d);
        else if (node instanceof Assign a) printAssign(a);
        else if (node instanceof Read r) printRead(r);
        else if (node instanceof Write w) printWrite(w);
        else if (node instanceof If i) printIf(i);
        else if (node instanceof IfElse ie) printIfElse(ie);
        else if (node instanceof IntLiteral i) printInt(i);
        else if (node instanceof BoolLiteral b) printBool(b);
        else if (node instanceof Var v) printVar(v);
        else if (node instanceof RelOp r) printRelOp(r);
        else {
            pad(); System.out.println("UNKNOWN NODE: " + node.getClass());
        }
    }

    private void printProgram(Program p) {
        pad(); System.out.println("Program " + p.name());
        indent += 2;

        for (Decl d : p.decls()) print(d);

        pad(); System.out.println("Statements:");
        indent += 2;
        for (Stmt s : p.statements()) print(s);
        indent -= 2;

        indent -= 2;
    }

    private void printDecl(Decl d) {
        pad(); System.out.println("Decl (" + d.type() + ") " + d.ids());
    }

    private void printAssign(Assign a) {
        pad(); System.out.println("Assign " + a.id());
        indent += 2;
        print(a.expr());
        indent -= 2;
    }

    private void printRead(Read r) {
        pad(); System.out.println("Read");
        indent += 2;
        for (Expr e : r.exprs()) print(e);
        indent -= 2;
    }

    private void printWrite(Write w) {
        pad(); System.out.println("Write");
        indent += 2;
        for (Expr e : w.exprs()) print(e);
        indent -= 2;
    }

    private void printIf(If i) {
        pad(); System.out.println("If");
        indent += 2;
        pad(); System.out.println("Condition:");
        indent += 2; print(i.condition()); indent -= 2;

        pad(); System.out.println("Then:");
        indent += 2; print(i.thenStmt()); indent -= 2;

        indent -= 2;
    }

    private void printIfElse(IfElse ie) {
        pad(); System.out.println("IfElse");
        indent += 2;

        pad(); System.out.println("Condition:");
        indent += 2; print(ie.condition()); indent -= 2;

        pad(); System.out.println("Then:");
        indent += 2; for (Stmt s : ie.thenBlock()) print(s); indent -= 2;

        pad(); System.out.println("Else:");
        indent += 2; for (Stmt s : ie.elseBlock()) print(s); indent -= 2;

        indent -= 2;
    }

    private void printInt(IntLiteral i) {
        pad(); System.out.println("Int " + i.value());
    }

    private void printBool(BoolLiteral b) {
        pad(); System.out.println("Bool " + b.value());
    }

    private void printVar(Var v) {
        pad(); System.out.println("Var " + v.name());
    }

    private void printRelOp(RelOp r) {
        pad(); System.out.println("RelOp " + r.opSymbol());
        indent += 2;
        print(r.left());
        print(r.right());
        indent -= 2;
    }
}
