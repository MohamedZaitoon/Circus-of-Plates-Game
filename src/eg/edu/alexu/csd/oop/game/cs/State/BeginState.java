package eg.edu.alexu.csd.oop.game.cs.State;

import java.awt.Color;

public class BeginState implements IState {

	private float speed;
	private String background;

	public BeginState(float speed) {
		this.speed = speed;
		this.background = "/res/L1.jpg";
	}
	@Override
	public IState NextState() {
		return new MediumState(speed + 0);
	}

	@Override
	public float speed() {
		return speed;
	}

	@Override
	public Color[] getAvailabeColors() {
		Color[] colors = {Color.blue, Color.green, Color.red};
		return colors;
	}
	@Override
	public String getBackground() {
		return background;
	}

}
