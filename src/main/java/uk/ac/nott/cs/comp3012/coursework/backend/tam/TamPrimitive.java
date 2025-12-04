package uk.ac.nott.cs.comp3012.coursework.backend.tam;

public enum TamPrimitive {
    ID(1),
    NOT(2),
    AND(3),
    OR(4),
    SUCC(5),
    PRED(6),
    NEG(7),
    ADD(8),
    SUB(9),
    MULT(10),
    DIV(11),
    MOD(12),
    LT(13),
    LE(14),
    GE(15),
    GT(16),
    EQ(17),
    NE(18),
    EOL(19),
    EOF(20),
    GET(21),
    PUT(22),
    GETEOL(23),
    PUTEOL(24),
    GETINT(25),
    PUTINT(26),
    NEW(27),
    DISPOSE(28);
    /** displacement from PB */
    public final int disp;

    TamPrimitive(int disp) {
        this.disp = disp;
    }
}
