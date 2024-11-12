package ast.expr;

import ast.CodeVisitor;
import ast.Const;

public class CosExpr implements Expr
{
    private Expr expr;

    public CosExpr(Expr e) {
        this.expr = e;
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }

    @Override
    public Const accept(CodeVisitor v) {
        return v.visit(this);
    }
}
