package ast;

import ast.expr.*;
import ast.command.*;

public interface CodeVisitor
{
    public Const visit(SumExpr sumExpr);

    public Const visit(SubExpr subExpr);

    public Const visit(MultExpr multExpr);

    public Const visit(DivExpr divExpr);

    public Const visit(IdExpr idExpr);

    public Const visit(ConstExpr constExpr);

    public Const visit(NegatedExpr negatedExpr);

    public Const visit(ModExpr modExpr);

    public Const visit(ExpExpr expExpr);

    public Const visit(SinExpr sinExpr);

    public Const visit(CosExpr cosExpr);

    public Const visit(PiExpr piExpr);

    public Boolean visit(GTExpr gtExpr);

    public Boolean visit(LTExpr ltExpr);

    public Boolean visit(GTEExpr gteExpr);

    public Boolean visit(LTEExpr lteExpr);

    public Boolean visit(EQExpr eqExpr);

    public Boolean visit(NEQExpr neqExpr);

    public void visit(PrintCommand printCommand);

    public void visit(AssignmentCommand assignmentCommand);

    public void visit(IfCommand ifCommand);

    public void visit(WhileCommand whileCommand);

    public void visit(CommandList commandList);

    public void visit(CommandBlock commandBlock);
}
