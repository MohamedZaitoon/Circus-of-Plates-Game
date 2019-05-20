package eg.edu.alexu.csd.oop.game.cs.Snapshot;

import eg.edu.alexu.csd.oop.game.GameObject;

public class Originator {
	private GameObject object;
	
	public void SetObject(GameObject o) {
		this.object = o;
	}
	
	public GameObject GetObject() {
		return this.object;
	}

	public Memento SaveToMemento() {
		return new Memento(object);
	}
	
	public void GetObjectFromMemento(Memento memento) {
		this.object = memento.GetObject();
	}
}
