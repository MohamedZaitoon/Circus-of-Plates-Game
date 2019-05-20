package eg.edu.alexu.csd.oop.game.cs.FlyWeight;

import java.awt.Color;
import java.util.HashMap;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.cs.Factory.Shape;

public class FactoryShape2 {
	
	private static final HashMap<Color, GameObject> shapeMap = new HashMap<Color, GameObject>();

	   public GameObject GetShape(Color color) {
		   GameObject Shape = (GameObject) shapeMap.get(color);
		   if(Shape == null) {
			   if (color == Color.blue) {
				   Shape = new Shape("/res/shape2/blue.png");
			   } else if (color == Color.green) {
				   Shape = new Shape("/res/shape2/green.png");
			   } else if (color == Color.red) {
				   Shape = new Shape("/res/shape2/red.png");
			   } else if (color == Color.pink) {
				   Shape = new Shape("/res/shape2/pink.png");
			   } else if (color == Color.orange) {
				   Shape = new Shape("/res/shape2/orange.png");
			   } else if (color == Color.yellow) {
				   Shape = new Shape("/res/shape2/yellow.png");
			   } else if (color == Color.magenta) {
				   Shape = new Shape("/res/shape2/magenta.png");
			   }
			   shapeMap.put(color, Shape);
		   }
		   return new Shape(((Shape) Shape).getPath());
	   }
}
