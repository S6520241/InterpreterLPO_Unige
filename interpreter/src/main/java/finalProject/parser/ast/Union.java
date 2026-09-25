package finalProject.parser.ast;

import finalProject.visitors.Visitor;

public class Union extends BinaryOp {
	public Union(Exp left, Exp right) {
		super(left, right);
	}
	
	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitUnion(left, right);
	}
}
