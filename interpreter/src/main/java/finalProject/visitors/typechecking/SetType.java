package finalProject.visitors.typechecking;

import static java.util.Objects.requireNonNull;

public record SetType(Type elemType) implements Type {

	public static final String TYPE_NAME = "SET";

	public SetType {
		requireNonNull(elemType);
	}

	@Override
	public String toString() {
		return String.format("%s set", elemType);
	}

	@Override
	public SetType toSetType() {
		return this;
	}
}