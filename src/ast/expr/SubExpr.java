package ast.expr;

import ast.CodeVisitor;
import ast.Const;

public class SubExpr implements Expr
{
    private Expr e1, e2;

    public SubExpr(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public Expr getMinuend() {
        return e1;
    }

    public void setMinuend(Expr e1) {
        this.e1 = e1;
    }

    public Expr getSubtrahend() {
        return e2;
    }

    public void setSubtrahend(Expr e2) {
        this.e2 = e2;
    }

    @Override
    public Const accept(CodeVisitor v) {
        return v.visit(this);
    }
}
