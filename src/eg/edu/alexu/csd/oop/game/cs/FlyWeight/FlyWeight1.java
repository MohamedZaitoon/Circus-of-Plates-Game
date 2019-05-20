package eg.edu.alexu.csd.oop.game.cs.FlyWeight;

import java.awt.Color;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.cs.State.IState;

public class FlyWeight1 {
	private Color[] color;
	private FactoryShape1 shape;
	
	public FlyWeight1(IState s) {
		color = s.getAvailabeColors();
		shape = new FactoryShape1();
	}
	
	private int random() {
		return (int) (Math.random()*(color.length));
	}
	
	public GameObject GetObject() {
		return shape.GetShape(color[random()]);
	}
}
