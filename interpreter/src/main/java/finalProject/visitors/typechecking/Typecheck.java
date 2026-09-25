package finalProject.visitors.typechecking;

import static finalProject.visitors.typechecking.AtomicType.*;

import finalProject.environments.EnvironmentException;
import finalProject.parser.ast.Block;
import finalProject.parser.ast.Exp;
import finalProject.parser.ast.Stmt;
import finalProject.parser.ast.StmtSeq;
import finalProject.parser.ast.Variable;
import finalProject.visitors.Visitor;

public class Typecheck implements Visitor<Type> {

	private final StaticEnv env = new StaticEnv();

	// useful to typecheck binary operations where operands must have the same type
	private void checkBinOp(Exp left, Exp right, Type type) {
		type.checkEqual(left.accept(this));
		type.checkEqual(right.accept(this));
	}

	// static semantics for programs; no value returned by the visitor

	@Override
	public Type visitLangProg(StmtSeq stmtSeq) {
		try {
			stmtSeq.accept(this);
		} catch (EnvironmentException e) { // undeclared variable
			throw new TypecheckerException(e);
		}
		return null;
	}

	// static semantics for sequences of statements
	// no value returned by the visitor

	@Override
	public Type visitEmptyStmtSeq() {
		return null;
	}

	@Override
	public Type visitNonEmptyStmtSeq(Stmt first, StmtSeq rest) {
		first.accept(this);
		rest.accept(this);
		return null;
	}
	
	// static semantics for statements; no value returned by the visitor

	@Override
	public Type visitAssertStmt(Exp exp) {
		BOOL.checkEqual(exp.accept(this));
		return null;
	}

	@Override
	public Type visitAssignStmt(Variable var, Exp exp) {
		var found = env.lookup(var);
		found.checkEqual(exp.accept(this));
		return null;
	}

	@Override
	public Type visitIfStmt(Exp exp, Block thenBlock, Block elseBlock) {
		BOOL.checkEqual(exp.accept(this));
		thenBlock.accept(this);
		if (elseBlock != null)
			elseBlock.accept(this);
		return null;
	}
	
	@Override
	public Type visitPrintStmt(Exp exp) {
		exp.accept(this);
		return null;
	}

	@Override
	public Type visitVarStmt(Variable var, Exp exp) {
		env.dec(var, exp.accept(this));
		return null;
	}

	@Override
	public Type visitBlock(StmtSeq stmtSeq) {
		env.enterLevel();
		stmtSeq.accept(this);
		env.exitLevel();
		return null;
	}

	// static semantics of expressions; a type is returned by the visitor

	@Override
	public AtomicType visitAdd(Exp left, Exp right) {
		checkBinOp(left, right, INT);
		return INT;
	}

	@Override
	public AtomicType visitAnd(Exp left, Exp right) {
		checkBinOp(left, right, BOOL);
		return BOOL;
	}

	@Override
	public AtomicType visitBoolLiteral(boolean value) {
		return BOOL;
	}

	@Override
	public AtomicType visitEq(Exp left, Exp right) {
		left.accept(this).checkEqual(right.accept(this));
		return BOOL;
	}

	@Override
	public Type visitFst(Exp exp) {
		return exp.accept(this).toPairType().fstType();
	}

	@Override
	public AtomicType visitIntLiteral(int value) {
		return INT;
	}

	@Override
	public AtomicType visitMinus(Exp exp) {
		INT.checkEqual(exp.accept(this));
		return INT;
	}
	
	@Override
	public AtomicType visitMul(Exp left, Exp right) {
		checkBinOp(left, right, INT);
		return INT;
	}

	@Override
	public AtomicType visitNot(Exp exp) {
		BOOL.checkEqual(exp.accept(this));
		return BOOL;
	}

	@Override
	public PairType visitPairLit(Exp left, Exp right) {
		return new PairType(left.accept(this), right.accept(this));
	}

	@Override
	public Type visitSnd(Exp exp) {
		return exp.accept(this).toPairType().sndType();
	}
	
	@Override
	public Type visitVariable(Variable var) {
		return env.lookup(var);
	}

	@Override
	public Type visitIsIn(Exp left, Exp right) {
		var expected = new SetType(left.accept(this));
		expected.checkEqual(right.accept(this));
		return BOOL;
	}

	@Override
	public Type visitDiff(Exp left, Exp right) {
		var type = left.accept(this).toSetType();
		type.checkEqual(right.accept(this).toSetType());
		return type;
	}

	@Override
	public Type visitUnion(Exp left, Exp right) {
		var type = left.accept(this).toSetType();
		type.checkEqual(right.accept(this).toSetType());
		return type;
	}

	@Override
	public Type visitSize(Exp exp) {
		exp.accept(this).toSetType();
		return INT;
	}

	@Override
	public Type visitSetLit(Exp exp) {
		return new SetType(exp.accept(this));
	}

	@Override
	public Type visitSetEnum(Variable var, Exp setExp, Exp elemExp) {
		env.enterLevel();		
		env.dec(var, setExp.accept(this).toSetType().elemType());
		var elemType = elemExp.accept(this);
		env.exitLevel();
		return new SetType(elemType);
	}

	@Override
	public Type visitWhileStmt(Exp exp, Block block) {
		BOOL.checkEqual(exp.accept(this));
		block.accept(this);
		return null;
	}
}
