package eg.edu.alexu.csd.oop.game.cs.FlyWeight;

import java.awt.Color;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.cs.State.IState;

public class FlyWeight2 {
	private Color[] color;
	private FactoryShape2 shape;
	
	public FlyWeight2(IState s) {
		color = s.getAvailabeColors();
		shape = new FactoryShape2();
	}
	private int random() {
		return (int)(Math.random()*(color.length));
	}
	
	public GameObject GetObject() {
		return shape.GetShape(color[random()]);
	}
}
