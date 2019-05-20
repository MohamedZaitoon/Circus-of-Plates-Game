package eg.edu.alexu.csd.oop.game.cs.Observer;

import java.util.ArrayList;
import java.util.List;

public class Order {
	private List<observer> obs = new ArrayList<>();
	private List<String> col = new ArrayList<>();
	private String Type;
	
	public List<String> getCollective() {
	      return col;
	   }

	public void attach(observer observer) {
		obs.add(observer);
	}
	
	public void setType(String Type) {
	      this.Type = Type;
	      notifyAllObsevers();
	}
	
	public String getType() {
		return this.Type;	
	}
	
	private void notifyAllObsevers() {
		List<String> result = new ArrayList<>();
		for (observer observer : obs) {
			result = observer.update();
			for(String m : result) {
				col.add(m);
			}
		}
	}
}
