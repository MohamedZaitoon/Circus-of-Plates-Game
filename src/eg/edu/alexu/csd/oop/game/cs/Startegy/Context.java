package eg.edu.alexu.csd.oop.game.cs.Startegy;

import eg.edu.alexu.csd.oop.game.GameObject;

public class Context {
	private Intersect intersect;
	public Context(Intersect intersect) {
		this.intersect = intersect;
	}
	public boolean excute(GameObject o1, GameObject o2, GameObject controller, int height) {
		return intersect.doOperation(o1, o2, controller, height);
	}
}
