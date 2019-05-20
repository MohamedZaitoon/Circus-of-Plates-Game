package eg.edu.alexu.csd.oop.game.cs.Factory;

import java.util.logging.Logger;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.cs.FlyWeight.FlyWeight1;
import eg.edu.alexu.csd.oop.game.cs.FlyWeight.FlyWeight2;
import eg.edu.alexu.csd.oop.game.cs.State.IState;

public class ShapeFactory {
	private Logger log;
	public ShapeFactory(Logger log)
	{
		this.log = log;
	}
	public GameObject getShape(String Type, IState state) {
		if (Type == null) {
			return null;
		}
		if (Type.equalsIgnoreCase("character")) {
			log.info("create character");
			return new Character();
		}
		 else if (Type.equalsIgnoreCase("shape1")) {
			FlyWeight1 w = new FlyWeight1(state);
			log.info("create shape1");
			return w.GetObject();
		} else if (Type.equalsIgnoreCase("shape2")) {
			FlyWeight2 w = new FlyWeight2(state);
			log.info("create shape2");
			return w.GetObject();
		} else if (Type.equalsIgnoreCase("background")) {
			log.info("create background");
			return new Background();
		} else if (Type.equalsIgnoreCase("word")) {
			return new Text();
		} else if (Type.equalsIgnoreCase("life+")) {
			log.info("loading image");
			return new Collective("/res/collective/life+.png");
		} else if (Type.equalsIgnoreCase("life-")) {
			log.info("loading image");
			return new Collective("/res/collective/life-.png");
		} else if (Type.equalsIgnoreCase("time+")) {
			log.info("loading image");
			return new Collective("/res/collective/time+.png");
		} else if (Type.equalsIgnoreCase("time-")) {
			log.info("loading image");
			return new Collective("/res/collective/time-.png");
		} else if (Type.equalsIgnoreCase("tnt")) {
			log.info("loading image");
			return new Collective("/res/collective/tnt.png");
		}
		return null;
	}

}