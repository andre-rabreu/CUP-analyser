package ast.expr;

import ast.CodeVisitor;
import ast.Const;

public class ModExpr implements Expr
{
    public Expr e1, e2;

    public ModExpr(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public Const accept(CodeVisitor v) {
        return v.visit(this);
    }
}
