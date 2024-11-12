package ast.expr;

import ast.CodeVisitor;
import ast.Const;

public class ModExpr implements Expr
{
    private Expr e1, e2;

    public ModExpr(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public Expr getDividend() {
        return e1;
    }

    public void setDividend(Expr e1) {
        this.e1 = e1;
    }

    public Expr getDivisor() {
        return e2;
    }

    public void setDivisor(Expr e2) {
        this.e2 = e2;
    }

    @Override
    public Const accept(CodeVisitor v) {
        return v.visit(this);
    }
}
