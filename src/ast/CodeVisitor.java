package ast;

import ast.expr.*;
import ast.command.*;

public interface CodeVisitor
{
    public Const visit(SumExpr e);

    public Const visit(SubExpr e);

    public Const visit(MulExpr e);

    public Const visit(DivExpr e);

    public Const visit(IdExpr e);

    public Const visit(ConstExpr e);

    public Const visit(NegatedExpr e);

    public Const visit(ModExpr e);

    public Const visit(ExpExpr e);

    public Const visit(SinExpr e);

    public Const visit(CosExpr e);

    public Const visit(PiExpr e);

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

    public void visit(CommandList cl);
}
