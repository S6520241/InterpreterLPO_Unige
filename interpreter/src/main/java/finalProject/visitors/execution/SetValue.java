package finalProject.visitors.execution;

import java.util.HashSet;

public class SetValue extends HashSet<Value> implements Value {
    public SetValue() {
		super();
	}
	
	public SetValue(SetValue set) {
		super(set);
	}
	
	@Override
	public SetValue toSet() {
		return this;
	}

	@Override
	public String toString() {
		return String.format("setOfSize(%d)", size());
	}
}
