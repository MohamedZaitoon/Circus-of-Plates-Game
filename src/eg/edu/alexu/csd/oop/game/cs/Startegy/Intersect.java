package eg.edu.alexu.csd.oop.game.cs.Startegy;

import eg.edu.alexu.csd.oop.game.GameObject;

public interface Intersect {
	public boolean doOperation(GameObject o1, GameObject o2, GameObject controller, int height);
}
