package ast;

import ast.expr.*;
import ast.command.*;

public interface CodeVisitor {
    public Double visit(SumExpr e);

    public Double visit(SubExpr e);

    public Double visit(MulExpr e);

    public Double visit(DivExpr e);

    public Double visit(IdExpr e);

    public Double visit(DoubleConstExpr e);

    public Double visit(NegatedExpr e);

    public Double visit(ModExpr e);

    public Double visit(ExpExpr e);

    public Double visit(SinExpr e);

    public Double visit(CosExpr e);

    public Double visit(PiExpr e);

    public Boolean visit(GTExpr e);

    public Boolean visit(LTExpr e);

    public Boolean visit(GTEExpr e);

    public Boolean visit(LTEExpr e);

    public Boolean visit(EQExpr e);

    public Boolean visit(NEQExpr e);

    public void visit(PrintCommand c);

    public void visit(AssignmentCommand c);

    public void visit(IfCommand ifc);

    public void visit(WhileCommand w);
}
