package eg.edu.alexu.csd.oop.game.cs.Factory;

public class Collective extends ConcreteShape{

	public Collective(String path) {
		setName("collective");
		setWidth(30);
		setHeight(30);
		setMax(1);
		setPath(path);
		
	}
}
