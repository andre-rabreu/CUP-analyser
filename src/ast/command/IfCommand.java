package ast.command;

import ast.CodeVisitor;
import ast.expr.BoolExpr;

public class IfCommand implements Command
{
    public BoolExpr boolExpr;
    public Command trueCommand;
    public Command falseCommand;

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

    @Override
    public void accept(CodeVisitor v) {
        v.visit(this);
    }
}
