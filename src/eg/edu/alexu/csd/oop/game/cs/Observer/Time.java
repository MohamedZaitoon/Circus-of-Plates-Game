package eg.edu.alexu.csd.oop.game.cs.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Time extends observer{
	
	private Logger log;
	public Time(Order order, Logger log) {
		this.order = order;
		this.order.attach(this);
		this.log = log;
	}
	
	@Override
	public List<String> update() {
		List<String> d = new ArrayList<>();
		if (order.getType().equals("time")) {
			d.add("time+");
			d.add("time-");
			log.info("time updated");
		}
		return d;
	}

}
