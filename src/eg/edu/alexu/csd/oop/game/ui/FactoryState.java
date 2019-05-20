package eg.edu.alexu.csd.oop.game.ui;

import eg.edu.alexu.csd.oop.game.cs.State.BeginState;
import eg.edu.alexu.csd.oop.game.cs.State.FinalState;
import eg.edu.alexu.csd.oop.game.cs.State.IState;
import eg.edu.alexu.csd.oop.game.cs.State.MediumState;

public class FactoryState {

	public IState getState(String name) {
		if (name == null) {
			return null;
		}
		float speed = 1;
		String temp = name.toLowerCase();
		switch (temp) {
		case "easy":
			return new BeginState(speed);
		case "medium":
			return new MediumState(speed);
		case "hard":
			return new FinalState(speed);
		default:
			return null;
		}

	}
	public String getDifficulity(IState state) {
		if(state instanceof BeginState) {
			return "Easy";
		}else if(state instanceof MediumState) {
			return "Meduim";
		}else if(state instanceof FinalState) {
			return "Hard";
		}
		return "Easy";
	}

}
