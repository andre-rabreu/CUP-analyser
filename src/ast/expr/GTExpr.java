package ast.expr;

import ast.CodeVisitor;

public class GTExpr implements BoolExpr
{
    private Expr e1, e2;

    public GTExpr(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public Expr getLeft() {
        return e1;
    }

    public void setLeft(Expr e1) {
        this.e1 = e1;
    }

    public Expr getRight() {
        return e2;
    }

    public void setRight(Expr e2) {
        this.e2 = e2;
    }

    @Override
    public Boolean accept(CodeVisitor v) {
        return v.visit(this);
    }
}
