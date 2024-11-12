package ast.expr;

import ast.CodeVisitor;
import ast.Const;

public class NegatedExpr implements Expr
{
    private Expr expr;

    public NegatedExpr(Expr e) {
        this.expr = e;
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr e) {
        this.expr = e;
    }

    @Override
    public Const accept(CodeVisitor v) {
        return v.visit(this);
    }
}
