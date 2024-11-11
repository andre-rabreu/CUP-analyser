package ast;

import java.util.HashMap;

import ast.command.*;
import ast.expr.*;

public class Interpreter implements CodeVisitor
{
    // symbolTable é a tabela de símbolos
    private static HashMap<String, IdExpr> symbolTable = new HashMap<>();

    @Override
    public Const visit(SumExpr e) {
        Double c1 = (Double)e.e1.accept(this).getValue();
        Double c2 = (Double)e.e2.accept(this).getValue();
        return new Const(c1 + c2, Type.NUMBER);
    }

    @Override
    public Const visit(SubExpr e) {
        Double c1 = (Double)e.e1.accept(this).getValue();
        Double c2 = (Double)e.e2.accept(this).getValue();
        return new Const(c1 - c2, Type.NUMBER);
    }

    @Override
    public Const visit(MulExpr e) {
        Double c1 = (Double)e.e1.accept(this).getValue();
        Double c2 = (Double)e.e2.accept(this).getValue();
        return new Const(c1 * c2, Type.NUMBER);
    }

    @Override
    public Const visit(DivExpr e) {
        Double c1 = (Double)e.e1.accept(this).getValue();
        Double c2 = (Double)e.e2.accept(this).getValue();
        return new Const(c1 / c2, Type.NUMBER);
    }

    @Override
    public Const visit(IdExpr e) {
        IdExpr idExpr = Interpreter.symbolTable.get(e.name);
        if(idExpr == null) {
                System.err.println("Erro: variável \"" + e.name +
                        "\" não inicializada!");
        }
        return idExpr.value;
    }

    @Override
    public Const visit(ConstExpr e) {
        return e.value;
    }

    @Override
    public Const visit(NegatedExpr e) {
        Double value = (Double)e.expr.accept(this).getValue();
        return new Const(-value, Type.NUMBER);
    }

    @Override
    public Const visit(ModExpr e) {
        Double c1 = (Double)e.e1.accept(this).getValue();
        Double c2 = (Double)e.e2.accept(this).getValue();
        return new Const(c1 % c2, Type.NUMBER);
    }

    @Override
    public Const visit(ExpExpr e) {
        Double c1 = (Double)e.e1.accept(this).getValue();
        Double c2 = (Double)e.e2.accept(this).getValue();
        return new Const(Math.pow(c1, c2), Type.NUMBER);
    }

    @Override
    public Const visit(SinExpr e) {
        Double value = (Double)e.expr.accept(this).getValue();
        return new Const(Math.sin(value), Type.NUMBER);
    }

    @Override
    public Const visit(CosExpr e) {
        Double value = (Double)e.expr.accept(this).getValue();
        return new Const(Math.cos(value), Type.NUMBER);
    }

    @Override
    public Const visit(PiExpr e) {
        return new Const(e.value, Type.NUMBER);
    }

    @Override
    public Boolean visit(GTExpr e) {
        Double v1 = (Double)e.e1.accept(this).getValue();
        Double v2 = (Double)e.e2.accept(this).getValue();
        return v1 > v2;
    }

    @Override
    public Boolean visit(LTExpr e) {
        Double v1 = (Double)e.e1.accept(this).getValue();
        Double v2 = (Double)e.e2.accept(this).getValue();
        return v1 < v2;
    }

    @Override
    public Boolean visit(GTEExpr e) {
        Double v1 = (Double)e.e1.accept(this).getValue();
        Double v2 = (Double)e.e2.accept(this).getValue();
        return v1 >= v2;
    }

    @Override
    public Boolean visit(LTEExpr e) {
        Double v1 = (Double)e.e1.accept(this).getValue();
        Double v2 = (Double)e.e2.accept(this).getValue();
        return v1 <= v2;
    }

    @Override
    public Boolean visit(EQExpr e) {
        Double v1 = (Double)e.e1.accept(this).getValue();
        Double v2 = (Double)e.e2.accept(this).getValue();
        return v1.equals(v2);
    }

    @Override
    public Boolean visit(NEQExpr e) {
        Double v1 = (Double)e.e1.accept(this).getValue();
        Double v2 = (Double)e.e2.accept(this).getValue();
        return !v1.equals(v2);
    }

    @Override
    public void visit(PrintCommand c) {
        System.out.println(">> " + c.expr.accept(this).getValue());
    }

    @Override
    public void visit(AssignmentCommand c) {
        Const value = c.expr.accept(this);
        IdExpr idExpr = new IdExpr(c.id, value);
        Interpreter.symbolTable.put(c.id, idExpr);
    }

    @Override
    public void visit(IfCommand command) {
        if(command.boolExpr.accept(this)) {
            command.trueCommand.accept(this);
        } else {
            if(command.falseCommand != null) command.falseCommand.accept(this);
        }
    }

    @Override
    public void visit(WhileCommand w) {
        Boolean b = w.boolExpr.accept(this);
        while(b) {
            w.command.accept(this);
            b = w.boolExpr.accept(this);
        }
    }
}
