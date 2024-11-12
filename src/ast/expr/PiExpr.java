package ast.expr;

import ast.CodeVisitor;
import ast.Const;

public class PiExpr implements Expr
{
    private Double value;

    public PiExpr(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    @Override
    public Const accept(CodeVisitor v) {
        return v.visit(this);
    }
}
