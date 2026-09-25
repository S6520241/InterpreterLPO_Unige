package finalProject.parser.ast;

import static java.util.Objects.requireNonNull;
import finalProject.visitors.Visitor;

public class WhileStmt implements Stmt {
	private final Exp exp; // non-optional field
	private final Block block; // non-optional field

    public WhileStmt(Exp exp, Block block) {
        this.exp = requireNonNull(exp);
        this.block =  requireNonNull(block);
    }

    @Override
    public String toString() {
		return String.format("%s(%s,%s)", getClass().getSimpleName(), exp, block);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visitWhileStmt(exp, block);
    }
}
