package eg.edu.alexu.csd.oop.game.cs.Snapshot;

import java.util.LinkedList;

public class CareTaker {
	private LinkedList<Memento> list = new LinkedList<>();
	
	public void add(Memento memento) {
		list.add(memento);
	}
	
	public Memento GetObject(int index) {
		return list.get(index);
	}
	
	public void Remove() {
		for (int i = 0; i < 3; i++) {
			list.remove(list.size() - 1);
		}
	}
	
	public int size() {
		return list.size();
	}
	
	public void RemoveLast() {
		list.remove(list.size() - 1);
	}

}
