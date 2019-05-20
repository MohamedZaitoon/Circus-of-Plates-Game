package eg.edu.alexu.csd.oop.game.cs.Observer;

import java.util.List;

public abstract class observer {
	protected Order order;
	public abstract List<String> update();
}
