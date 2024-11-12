package ast.expr;

import ast.CodeVisitor;
import ast.Const;

public class ConstExpr implements Expr
{
    private Const value;

    public ConstExpr(Const value) {
        this.value = value;
    }

    public Const getValue() {
        return value;
    }

    public void setValue(Const value) {
        this.value = value;
    }

    @Override
    public Const accept(CodeVisitor v) {
        return v.visit(this);
    }
}
