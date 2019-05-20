package eg.edu.alexu.csd.oop.game.cs.State;

import java.awt.Color;

public interface IState {

	/**
	 * used to go to the following state.
	 * return the next state.
	 */
	public IState NextState();
	
	/**
	 * used to get the path for state background image.
	 * return the next state.
	 */
	public String getBackground();

	/**
	 * used to get the speed of the falling objects of current state.
	 * return objects speed.
	 */
	public float speed();

	/**
	 * used to get the collection of the colors of the falling objects of current state.
	 * return colors of the falling objects.
	 */
	public Color[] getAvailabeColors();
}
