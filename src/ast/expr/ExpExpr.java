package ast.expr;

import ast.CodeVisitor;
import ast.Const;

public class ExpExpr implements Expr
{
    private Expr e1, e2;

    public ExpExpr(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public Expr getBase() {
        return e1;
    }

    public void setBase(Expr e1) {
        this.e1 = e1;
    }

    public Expr getExponent() {
        return e2;
    }

    public void setExponent(Expr e2) {
        this.e2 = e2;
    }

    @Override
    public Const accept(CodeVisitor v) {
        return v.visit(this);
    }
}
