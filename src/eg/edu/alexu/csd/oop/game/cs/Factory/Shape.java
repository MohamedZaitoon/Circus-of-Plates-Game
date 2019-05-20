package eg.edu.alexu.csd.oop.game.cs.Factory;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Shape extends ConcreteShape {

	public Shape(String path) {
		setName("shape");
		if(path.contains("shape1")) {
			setHeight(60);
			setWidth(33);
		} else {
			setHeight(30);
			setWidth(30);
		}
		setMax(1);
		try {
			BufferedImage[] sprite = new BufferedImage[1];
			sprite[0] = resize(ImageIO.read(this.getClass().getResourceAsStream(path)), getWidth(), getHeight());
			setPath(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
