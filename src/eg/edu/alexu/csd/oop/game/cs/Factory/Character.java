package eg.edu.alexu.csd.oop.game.cs.Factory;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Character extends ConcreteShape {

	public Character() {
		try {
			setName("character");
			setHeight(250);
			setWidth(300);
			setMax(4);
			BufferedImage[] sprite = new BufferedImage[4];
			sprite[0] = resize(ImageIO.read(this.getClass().getResourceAsStream("/res/character/0.png")), getWidth(),
					getHeight());
			sprite[1] = resize(ImageIO.read(this.getClass().getResourceAsStream("/res/character/1.png")), getWidth(),
					getHeight());
			sprite[2] = resize(ImageIO.read(this.getClass().getResourceAsStream("/res/character/2.png")), getWidth(),
					getHeight());
			sprite[3] = resize(ImageIO.read(this.getClass().getResourceAsStream("/res/character/3.png")), getWidth(),
					getHeight());
			setImages(sprite);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
