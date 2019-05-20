package eg.edu.alexu.csd.oop.game.cs.State;

import java.awt.Color;

public class FinalState implements IState {

	private float speed;
	private String background;
	
	public FinalState(float speed) {
		this.speed = speed;
		this.background = "/res/L3.png";
	}
	@Override
	public IState NextState() {
		this.speed = (float) (speed + 0.25);
		return this;
	}

	@Override
	public float speed() {
		return speed;
	}

	@Override
	public Color[] getAvailabeColors() {
		Color[] colors = {Color.blue, Color.green, Color.red, Color.pink, Color.orange, Color.yellow, Color.magenta};
		return colors;
	}
	@Override
	public String getBackground() {
		return background;
	}

}
