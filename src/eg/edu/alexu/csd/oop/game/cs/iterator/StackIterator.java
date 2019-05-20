package eg.edu.alexu.csd.oop.game.cs.iterator;

import java.util.ArrayList;
import java.util.Stack;

import eg.edu.alexu.csd.oop.game.GameObject;

public class StackIterator implements Container {
	
	private ArrayList<GameObject> Shapes;
	
	public StackIterator(Stack<GameObject> shapes) {
		Shapes = new ArrayList<>(shapes.size());
		for(int i = 0; i < shapes.size(); i++)
		{
			Shapes.add(i, shapes.pop());
		}
	}
	
	@Override
	public Iterator getIterator() {
		return new newIterator();
	}

	private class newIterator implements Iterator {

	      int index = 0;

	      @Override
	      public boolean hasNext() {
	      
	         if(index < Shapes.size()){
	            return true;
	         }
	         return false;
	      }

	      @Override
	      public GameObject next() {
	      
	         if(this.hasNext()){
	            return Shapes.get(index++);
	         }
	         return null;
	      }		
	   }
}
