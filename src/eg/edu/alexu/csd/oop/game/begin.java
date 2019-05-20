package eg.edu.alexu.csd.oop.game;

import java.awt.Color;

import eg.edu.alexu.csd.oop.game.cs.DynamicLinkage.Dynamic;
import eg.edu.alexu.csd.oop.game.cs.Factory.ConcreteShape;
import eg.edu.alexu.csd.oop.game.cs.State.BeginState;
import eg.edu.alexu.csd.oop.game.cs.State.IState;


public class begin {

	public static void main(String[] args) {
		Dynamic y = new Dynamic();
		@SuppressWarnings("unchecked")
		Class<ConcreteShape> t = (Class<ConcreteShape>) y.getShape("C:\\Users\\Ahmed\\git\\game\\GameOOP\\jar\\ForDynamicLinkage.jar");
		IState s = new BeginState(1);
		GameEngine.start("", new eg.edu.alexu.csd.oop.game.cs.Facade.Start(1800, 900, "score", t, s, "Mazen"), Color.GRAY);

	}

}
