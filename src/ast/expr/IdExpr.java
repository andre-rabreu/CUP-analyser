package ast.expr;

import ast.CodeVisitor;
import ast.Const;

public class IdExpr implements Expr
{
    private String name;
    private Const value;

    public IdExpr(String name) {
        this(name, null);
    }

    public IdExpr(String name, Const value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
