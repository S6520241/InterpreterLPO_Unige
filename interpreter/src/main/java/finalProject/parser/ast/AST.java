package finalProject.parser.ast;

import finalProject.visitors.Visitor;

public interface AST {
	<T> T accept(Visitor<T> visitor);
}
