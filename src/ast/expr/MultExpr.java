package ast.expr;

import ast.CodeVisitor;
import ast.Const;

public class MultExpr implements Expr
{
    private Expr e1, e2;

    public MultExpr(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public Expr getMultiplicand() {
        return e1;
    }

    public void setMultiplicand(Expr e1) {
        this.e1 = e1;
    }

    public Expr getMultiplier() {
        return e2;
    }

    public void setMultiplier(Expr e2) {
        this.e2 = e2;
    }

    @Override
    public Const accept(CodeVisitor v) {
        return v.visit(this);
    }
}
