package eg.edu.alexu.csd.oop.game.cs.State;

import java.awt.Color;

public class MediumState implements IState {

	private float speed;
	private String background;

	public MediumState(float speed) {
		this.speed = speed;
		this.background = "/res/L2.PNG";
	}

	@Override
	public IState NextState() {
		return new FinalState(speed + 1);
	}

	@Override
	public float speed() {
		return speed;
	}

	@Override
	public Color[] getAvailabeColors() {
		Color[] colors = {Color.blue, Color.green, Color.red, Color.pink, Color.orange};
		return colors;
	}

	@Override
	public String getBackground() {
		return background;
	}

}
