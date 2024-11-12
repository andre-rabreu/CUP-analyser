package ast.command;

import ast.CodeVisitor;
import ast.expr.BoolExpr;

public class WhileCommand implements Command
{
    private BoolExpr boolExpr;
    private Command command;

    public WhileCommand(BoolExpr e, Command c) {
        this.boolExpr = e;
        this.command = c;
    }

    public BoolExpr getBoolExpr() {
        return boolExpr;
    }

    public void setBoolExpr(BoolExpr boolExpr) {
        this.boolExpr = boolExpr;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    @Override
    public void accept(CodeVisitor v) {
        v.visit(this);
    }
}
