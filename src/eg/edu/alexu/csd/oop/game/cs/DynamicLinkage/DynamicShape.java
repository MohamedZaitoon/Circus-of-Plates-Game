package eg.edu.alexu.csd.oop.game.cs.DynamicLinkage;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import eg.edu.alexu.csd.oop.game.cs.Factory.ConcreteShape;

public class DynamicShape extends ConcreteShape{
	private BufferedImage[] spriteImages = new BufferedImage[1];
	
	public DynamicShape(Color color) {
		setName("dynamic");
		setColor(color);
		setWidth(30);
		setHeight(30);
		setMax(1);
		spriteImages[0] = new BufferedImage(getHeight(), getWidth(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = spriteImages[0].createGraphics();
		g2.setColor(color);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		double x = getWidth()/2;
		double y = getHeight()/2;
		int x1 = (int) ((getWidth() / 2.0) - x);
		int y1 = (int) ((getHeight() / 2.0) - y);
		int x2 = (int) ((getWidth() / 2.0) + x);
		int y2 = (int) ((getHeight() / 2.0) + y);
		g2.setStroke(new BasicStroke(3));
		g2.fillRect(x1+3, y1+3, x2-6, y2-6);
		g2.setColor(Color.WHITE);
		g2.drawRect(x1+3, y1+3, x2-6, y2-6);
		g2.dispose();
		setImages(spriteImages);
		
	}
}
