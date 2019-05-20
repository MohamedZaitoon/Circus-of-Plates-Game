package eg.edu.alexu.csd.oop.game.cs.Factory;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class Text extends ConcreteShape{

	private String text;
	private BufferedImage[] spriteImages = new BufferedImage[1];
	
	public Text() {
		setName("text");
		setWidth(120);
		setHeight(60);
		setMax(1);
		update("00");
	}
	
	public void update(String text) {
		this.text = text;
		spriteImages[0] = getBufferedImg();
		Graphics2D g2 = (Graphics2D)this.spriteImages[0].createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	        RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.setStroke(new BasicStroke(4));
	    g2.setColor(Color.black);
		Font font = new Font("TimesRoman", Font.PLAIN, 23);
		g2.drawRect(4, 4, getWidth() - 10, getHeight() / 2 + 10);
	    g2.setFont(font);
	    g2.setColor(Color.RED);
	    g2.drawString(text,12, 39);
	    g2.dispose();
	    setImages(spriteImages);
	}
	public String getText() {
		return text;
	}
	
	protected BufferedImage getBufferedImg() {
		return new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
	}
}
