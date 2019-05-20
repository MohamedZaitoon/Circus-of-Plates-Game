package eg.edu.alexu.csd.oop.game.cs.Observer;

import java.util.ArrayList;
import java.util.List;

public class TNT extends observer{

	public TNT(Order order) {
		this.order = order;
		this.order.attach(this);
	}
	
	@Override
	public List<String> update() {
		List<String> d = new ArrayList<>();
		d.add("tnt");
		return d;
	}
}
