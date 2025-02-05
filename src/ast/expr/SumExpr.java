package ast.expr;

import ast.CodeVisitor;
import ast.Const;

public class SumExpr implements Expr
{
    private Expr e1, e2;

    public SumExpr(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public Expr getAugend() {
        return e1;
    }

    public void setAugend(Expr e1) {
        this.e1 = e1;
    }

    public Expr getAddend() {
        return e2;
    }

    public void setAddend(Expr e2) {
        this.e2 = e2;
    }

    @Override
    public Const accept(CodeVisitor v) {
        return v.visit(this);
    }
}
