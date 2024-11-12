package ast.command;

import ast.CodeVisitor;
import ast.expr.Expr;

public class PrintCommand implements Command
{
    private Expr expr;

    public PrintCommand(Expr expr) {
        this.expr = expr;
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }

    @Override
    public void accept(CodeVisitor v) {
        v.visit(this);
    }
}
