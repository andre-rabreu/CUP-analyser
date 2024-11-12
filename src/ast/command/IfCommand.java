package ast.command;

import ast.CodeVisitor;
import ast.expr.BoolExpr;

public class IfCommand implements Command
{
    private BoolExpr boolExpr;
    private Command trueCommand;
    private Command falseCommand;

    public IfCommand(BoolExpr boolExpr, Command command) {
        this.boolExpr = boolExpr;
        this.trueCommand = command;
        this.falseCommand = null;
    }

    public IfCommand(BoolExpr boolExpr, Command trueCommand, Command falseCommand) {
        this.boolExpr = boolExpr;
        this.trueCommand = trueCommand;
        this.falseCommand = falseCommand;
    }

    public BoolExpr getBoolExpr() {
        return boolExpr;
    }

    public void setBoolExpr(BoolExpr boolExpr) {
        this.boolExpr = boolExpr;
    }

    public Command getTrueCommand() {
        return trueCommand;
    }

    public void setTrueCommand(Command trueCommand) {
        this.trueCommand = trueCommand;
    }

    public Command getFalseCommand() {
        return falseCommand;
    }

    public void setFalseCommand(Command falseCommand) {
        this.falseCommand = falseCommand;
    }

    @Override
    public void accept(CodeVisitor v) {
        v.visit(this);
    }
}
