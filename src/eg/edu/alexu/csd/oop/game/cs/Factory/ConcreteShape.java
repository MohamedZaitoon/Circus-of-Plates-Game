package eg.edu.alexu.csd.oop.game.cs.Factory;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import eg.edu.alexu.csd.oop.game.GameObject;

public class ConcreteShape implements GameObject{

	private int x;
	private int y;
	private int weight;
	private int height;
	private boolean controlled = false;
	private boolean visible = true;
	private Color color;
	private boolean horizontalL = false;
	private boolean horizontalR = false;
	private String Path;
	private String Name;
	private int MAX_MSTATE;
	private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public void setX(int x) {
		if (horizontalL && this.x < x) {
			setHorizontalL(false);
		}
		if (horizontalR && this.x > x) {
			setHorizontalR(false);
		}
		if (horizontalL) {
			return;
		} else if (horizontalR) {
			return;
		} else {
			this.x = x;
		}
	}
	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public void setY(int y) {
		if (this.controlled) {
			return;
		}
		this.y = y;
	}

	@Override
	public int getWidth() {
		return weight;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public boolean isVisible() {
		return this.visible;
	}

	@Override
	public BufferedImage[] getSpriteImages() {
		return spriteImages;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setControll(boolean control) {
		this.controlled = control;
	}

	public static BufferedImage resize(BufferedImage img, int newW, int newH) {
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}
	
	public String getPath() {
		return this.Path;
	}
	
	public void setWidth(int weigh) {
		this.weight = weigh;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setImages(BufferedImage[] spriteImages) {
		this.spriteImages = spriteImages;
	}
	public void setMax(int max) {
		this.MAX_MSTATE = max;
	}
	public void setPath(String path) {
		this.Path = path;
		if (path.contains("blue")) {
			this.color = Color.blue;
		} else if (path.contains("green")) {
			this.color = Color.green;
		} else if (path.contains("red")) {
			this.color = Color.red;
		} else if (path.contains("pink")) {
			this.color = Color.pink;
		} else if (path.contains("orange")) {
			this.color = Color.orange;
		} else if (path.contains("yellow")) {
			this.color = Color.yellow;
		} else if (path.contains("magenta")) {
			this.color = Color.magenta;
		}
		try {
			BufferedImage[] sprite = new BufferedImage[1];
			sprite[0] = resize(ImageIO.read(this.getClass().getResourceAsStream(path)), getWidth(), getHeight());
			setImages(sprite);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Color getColor() {
		return this.color;
	}
	public void setHorizontalL(boolean h) {
		this.horizontalL = h;
	}
	
	public void setHorizontalR(boolean h) {
		this.horizontalR = h;
	}
	public void setName(String name) {
		this.Name = name;
	}
	public String getName() {
		return this.Name;
	}
}
