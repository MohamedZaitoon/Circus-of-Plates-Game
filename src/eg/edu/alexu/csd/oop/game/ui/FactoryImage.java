package eg.edu.alexu.csd.oop.game.ui;

import java.io.File;

import javafx.scene.image.Image;

public class FactoryImage {

	public Image getImage(String name) {
		if (name == null) {
			return null;
		}
		String temp = name.toLowerCase();
		File file = new File("src/"+name);
		/*switch (temp) {
		case "easy":
			file = new File("src/res/L1.jpg");
			break;
		case "medium":
			file = new File("res/L2.PNG");
			break;
		case "hard":
			file = new File("res/L3.png");
			break;
		default:
			file = new File(temp);
		}*/
		//System.out.println(file.exists());
		Image image = new Image(file.toURI().toString());
		return image;

	}
}
