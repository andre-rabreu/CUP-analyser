package ast.expr;

import ast.CodeVisitor;
import ast.Const;

public class SinExpr implements Expr
{
    private Expr expr;

    public SinExpr(Expr e) {
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
