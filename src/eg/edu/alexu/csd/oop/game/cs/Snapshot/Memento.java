package eg.edu.alexu.csd.oop.game.cs.Snapshot;

import eg.edu.alexu.csd.oop.game.GameObject;

public class Memento {
	private GameObject object;
	
	public Memento(GameObject object) {
		this.object = object;
	}
	
	public GameObject GetObject() {
		return this.object;
	}

}
