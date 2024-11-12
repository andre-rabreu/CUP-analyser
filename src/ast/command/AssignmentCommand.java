package ast.command;

import ast.CodeVisitor;
import ast.expr.Expr;

public class AssignmentCommand implements Command
{
    private String id;
    private Expr expr;
    
    public AssignmentCommand(String id, Expr expr) {
        this.id = id;
        this.expr = expr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
