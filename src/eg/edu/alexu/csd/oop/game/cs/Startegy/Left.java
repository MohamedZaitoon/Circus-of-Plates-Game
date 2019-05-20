package eg.edu.alexu.csd.oop.game.cs.Startegy;

import java.util.logging.Logger;

import eg.edu.alexu.csd.oop.game.GameObject;

public class Left implements Intersect{

	@Override
	public boolean doOperation(GameObject o1, GameObject o2, GameObject controller, int hight) {
		// hight = getHeight() - Hl;
		boolean t1 = o2.getY() + o2.getHeight() == hight;
		boolean t2, t3;
		if (o1.equals(controller)) {
			t2 = o2.getX() >= (controller.getX()) && o2.getX() < (controller.getX() + 45);
			t3 = (o2.getX() + o2.getWidth()) > (controller.getX())
					&& (o2.getX() + o2.getWidth()) <= (controller.getX() + 45);
		} else {
			t2 = o2.getX() >= (o1.getX()) && o2.getX() < (o1.getX() + o1.getWidth());
			t3 = (o2.getX() + o2.getWidth()) > (o1.getX())
					&& (o2.getX() + o2.getWidth()) <= (o1.getX() + o1.getWidth());
		}
		return (t1 && (t2 || t3));
	}

}
