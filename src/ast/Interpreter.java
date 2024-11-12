package ast;

import ast.command.*;
import ast.expr.*;

public class Interpreter implements CodeVisitor
{
    private static SymbolTable symbolTable = new SymbolTable();

    @Override
    public Const visit(SumExpr sumExpr) {
        Const augend = sumExpr.getAugend().accept(this);
        Const addend = sumExpr.getAddend().accept(this);

        Type augendType = augend.getType();
        Type addendType = addend.getType();

        if(augendType == Type.NUMBER && addendType == Type.NUMBER) {
            return new Const((Double)augend.getValue() + (Double)addend.getValue(), Type.NUMBER);
        }
        else if(augendType == Type.STRING || addend.getType() == Type.STRING) {
            return new Const(augend.getValue().toString() + addend.getValue().toString(), Type.STRING);
        }
        else {
            System.err.println("Error: incompatible types for '+' operator!");
            return new Const(null, null);
            // return null;
        }
    }

    @Override
    public Const visit(SubExpr subExpr) {
        Const minuend = subExpr.getMinuend().accept(this);
        Const subtrahend = subExpr.getSubtrahend().accept(this);

        if(minuend.getType() == Type.NUMBER && subtrahend.getType() == Type.NUMBER) {
            return new Const((Double)minuend.getValue() - (Double)subtrahend.getValue(), Type.NUMBER);
        }
        else {
            System.err.println("Error: incompatible types for '-' operator!");
            return new Const(null, null);
            // return null;
        }
    }

    @Override
    public Const visit(MultExpr multExpr) {
        Const multiplicand = multExpr.getMultiplicand().accept(this);
        Const multiplier = multExpr.getMultiplier().accept(this);

        if(multiplicand.getType() == Type.NUMBER && multiplier.getType() == Type.NUMBER) {
            return new Const((Double)multiplicand.getValue() * (Double)multiplier.getValue(), Type.NUMBER);
        }
        else {
            System.err.println("Error: incompatible types for '*' operator!");
            return new Const(null, null);
            // return null;
        }
    }

    @Override
    public Const visit(DivExpr divExpr) {
        Const dividend = divExpr.getDividend().accept(this);
        Const divisor = divExpr.getDivisor().accept(this);

        if(dividend.getType() == Type.NUMBER && divisor.getType() == Type.NUMBER) {
            Double divisorValue = (Double)divisor.getValue();
            if(divisorValue == 0) {
                System.err.println("Error: division by zero!");
                return new Const(null, null);
                // return null;
            }
            return new Const((Double)dividend.getValue() / divisorValue, Type.NUMBER);
        }
        else {
            System.err.println("Error: incompatible types for '/' operator!");
            return new Const(null, null);
            // return null;
        }
    }

    @Override
    public Const visit(IdExpr idExpr) {
        String idExprName = idExpr.getName();
        IdExpr _idExpr = Interpreter.symbolTable.get(idExprName);

        if(_idExpr == null) {
            System.err.println("Error: variable \"" + idExprName + "\" not initialized!");
        }
        return _idExpr.getValue();
    }

    @Override
    public Const visit(ConstExpr constExpr) {
        return constExpr.getValue();
    }

    @Override
    public Const visit(NegatedExpr negatedExpr) {
        Const value = negatedExpr.getExpr().accept(this);

        if(value.getType() == Type.NUMBER) {
            return new Const(-(Double)value.getValue(), Type.NUMBER);
        }
        else {
            System.err.println("Error: incompatible type for negation!");
            return new Const(null, null);
            // return null;
        }
    }

    @Override
    public Const visit(ModExpr modExpr) {
        Const dividend = modExpr.getDividend().accept(this);
        Const divisor = modExpr.getDivisor().accept(this);

        if(dividend.getType() == Type.NUMBER && divisor.getType() == Type.NUMBER) {
            Double divisorValue = (Double)divisor.getValue();
            if(divisorValue == 0) {
                System.err.println("Error: division by zero!");
                return new Const(null, null);
                // return null;
            }
            return new Const((Double)dividend.getValue() % divisorValue, Type.NUMBER);
        }
        else {
            System.err.println("Error: incompatible types for '%' operator!");
            return new Const(null, null);
            // return null;
        }
    }

    @Override
    public Const visit(ExpExpr expExpr) {
        Const base = expExpr.getBase().accept(this);
        Const exponent = expExpr.getExponent().accept(this);

        if(base.getType() == Type.NUMBER && exponent.getType() == Type.NUMBER) {
            return new Const(Math.pow((Double)base.getValue(), (Double)exponent.getValue()), Type.NUMBER);
        }
        else {
            System.err.println("Error: incompatible types for '**' operator!");
            return new Const(null, null);
            // return null;
        }
    }

    @Override
    public Const visit(SinExpr sinExpr) {
        Const expr = sinExpr.getExpr().accept(this);

        if(expr.getType() == Type.NUMBER) {
            return new Const(Math.sin((Double)expr.getValue()), Type.NUMBER);
        }
        else {
            System.err.println("Error: incompatible type for sin!");
            return new Const(null, null);
            // return null;
        }
    }

    @Override
    public Const visit(CosExpr cosExpr) {
        Const expr = cosExpr.getExpr().accept(this);

        if(expr.getType() == Type.NUMBER) {
            return new Const(Math.cos((Double)expr.getValue()), Type.NUMBER);
        }
        else {
            System.err.println("Error: incompatible type for cos!");
            return new Const(null, null);
            // return null;
        }
    }

    @Override
    public Const visit(PiExpr e) {
        return new Const(e.getValue(), Type.NUMBER);
    }

    @Override
    public Boolean visit(GTExpr gtExpr) {
        Const left = gtExpr.getLeft().accept(this);
        Const right = gtExpr.getRight().accept(this);

        if(left.getType() == Type.NUMBER && right.getType() == Type.NUMBER) {
            return (Double)left.getValue() > (Double)right.getValue();
        }
        else {
            System.err.println("Error: incompatible types for '>' comparator!");
            return false;
        }
    }

    @Override
    public Boolean visit(LTExpr ltExpr) {
        Const left = ltExpr.getLeft().accept(this);
        Const right = ltExpr.getRight().accept(this);

        if(left.getType() == Type.NUMBER && right.getType() == Type.NUMBER) {
            return (Double)left.getValue() < (Double)right.getValue();
        }
        else {
            System.err.println("Error: incompatible types for '<' comparator!");
            return false;
        }
    }

    @Override
    public Boolean visit(GTEExpr gteExpr) {
        Const left = gteExpr.getLeft().accept(this);
        Const right = gteExpr.getRight().accept(this);

        if(left.getType() == Type.NUMBER && right.getType() == Type.NUMBER) {
            return (Double)left.getValue() >= (Double)right.getValue();
        }
        else {
            System.err.println("Error: incompatible types for '>=' comparator!");
            return false;
        }
    }

    @Override
    public Boolean visit(LTEExpr lteExpr) {
        Const left = lteExpr.getLeft().accept(this);
        Const right = lteExpr.getRight().accept(this);

        if(left.getType() == Type.NUMBER && right.getType() == Type.NUMBER) {
            return (Double)left.getValue() <= (Double)right.getValue();
        }
        else {
            System.err.println("Error: incompatible types for '<=' comparator!");
            return false;
        }
    }

    @Override
    public Boolean visit(EQExpr eqExpr) {
        Const left = eqExpr.getLeft().accept(this);
        Const right = eqExpr.getRight().accept(this);

        if(left.getType() == Type.NUMBER && right.getType() == Type.NUMBER) {
            return ((Double)left.getValue()).equals((Double)right.getValue());
        }
        else {
            System.err.println("Error: incompatible types for '==' comparator!");
            return false;
        }
    }

    @Override
    public Boolean visit(NEQExpr neqExpr) {
        Const left = neqExpr.getLeft().accept(this);
        Const right = neqExpr.getRight().accept(this);

        if(left.getType() == Type.NUMBER && right.getType() == Type.NUMBER) {
            return !((Double)left.getValue()).equals((Double)right.getValue());
        }
        else {
            System.err.println("Error: incompatible types for '!=' comparator!");
            return false;
        }
    }

    @Override
    public void visit(PrintCommand printCommand) {
        System.out.println(">> " + printCommand.getExpr().accept(this).getValue());
    }

    @Override
    public void visit(AssignmentCommand assignmentCommand) {
        Const value = assignmentCommand.getExpr().accept(this);
        IdExpr idExpr = new IdExpr(assignmentCommand.getId(), value);
        Interpreter.symbolTable.put(assignmentCommand.getId(), idExpr);
    }

    @Override
    public void visit(IfCommand ifCommand) {
        if(ifCommand.getBoolExpr().accept(this)) {
            ifCommand.getTrueCommand().accept(this);
        } else {
            Command falseCommand = ifCommand.getFalseCommand();
            if(falseCommand != null) falseCommand.accept(this);
        }
    }

    @Override
    public void visit(WhileCommand whileCommand) {
        Boolean condition = whileCommand.getBoolExpr().accept(this);
        while(condition) {
            whileCommand.getCommand().accept(this);
            condition = whileCommand.getBoolExpr().accept(this);
        }
    }

    @Override
    public void visit(CommandList commandList) {
        CommandList _commandList = commandList;
        do {
            _commandList.getCommand().accept(this);
            _commandList = _commandList.getCommandList();
        } while(_commandList != null);
    }

    @Override
    public void visit(CommandBlock commandBlock) {
        symbolTable.push();
        commandBlock.getCommandList().accept(this);
        symbolTable.pop();
    }
}
