package eg.edu.alexu.csd.oop.game.cs.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Life extends observer{
	private Logger log;
	public Life(Order order, Logger log) {
		this.order = order;
		this.order.attach(this);
		this.log = log;
	}
	
	@Override
	public List<String> update() {
		List<String> d = new ArrayList<>();
		if (order.getType().equals("life")) {
			d.add("life+");
			d.add("life-");
			log.info("lives updated");
		}
		return d;
	}
}
