package eg.edu.alexu.csd.oop.game.cs.Startegy;

import java.util.logging.Logger;

import eg.edu.alexu.csd.oop.game.GameObject;

public class Right implements Intersect{

	@Override
	public boolean doOperation(GameObject o1, GameObject o2, GameObject controller, int height) {
		// height = getHeight() - Hr;
		boolean t1 = o2.getY() + o2.getHeight() == height;
		boolean t2, t3;
		if (o1.equals(controller)) {
			t2 = o2.getX() >= (controller.getX() + controller.getWidth() - 45) && o2.getX() < (controller.getX() + controller.getWidth());
			t3 = (o2.getX() + o2.getWidth()) > (controller.getX() + controller.getWidth() - 45)
					&& (o2.getX() + o2.getWidth()) <= (controller.getX() + controller.getWidth());
		} else {
			t2 = o2.getX() >= (o1.getX()) && o2.getX() < (o1.getX() + o1.getWidth());
			t3 = (o2.getX() + o2.getWidth()) > (o1.getX())
					&& (o2.getX() + o2.getWidth()) <= (o1.getX() + o1.getWidth());
		}
		return (t1 && (t2 || t3));
	}

}
