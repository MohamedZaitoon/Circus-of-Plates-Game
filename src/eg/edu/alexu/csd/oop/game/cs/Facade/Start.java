package eg.edu.alexu.csd.oop.game.cs.Facade;

import java.awt.Color;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Logger;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.game.cs.Factory.*;
import eg.edu.alexu.csd.oop.game.cs.Observer.*;
import eg.edu.alexu.csd.oop.game.cs.Singleton.*;
import eg.edu.alexu.csd.oop.game.cs.Snapshot.*;
import eg.edu.alexu.csd.oop.game.cs.Startegy.*;
import eg.edu.alexu.csd.oop.game.cs.State.*;
import eg.edu.alexu.csd.oop.game.cs.iterator.*;
import eg.edu.alexu.csd.oop.game.ui.ConnectionDB;

public class Start implements World {
	private ShapeFactory factory;
	private Sound sound;
	private Originator originator;
	private CareTaker caretaker;
	private Originator originator2;
	private CareTaker caretaker2;
	private Context context;
	private IState state;
	private StackIterator shapes;
	private Iterator iterator;
	private Iterator iterator2;
	private Order order;

	private static int MAX_TIME = 2 * 60 * 1000; // 2 minute
	private List<String> shape = new ArrayList<>();
	private int score = 0;
	private int life = 5;
	private int speed = 0;
	private boolean Written = false;
	private long endTime, startTime = System.currentTimeMillis();
	private String PlayerName;
	private final int width;
	private final int height;
	private final List<GameObject> constant = new LinkedList<GameObject>();
	private final List<GameObject> moving = new LinkedList<GameObject>();
	private final List<GameObject> control = new LinkedList<GameObject>();
	private Stack<GameObject> Left = new Stack<>(), Right = new Stack<>();
	private GameObject TimeLable, ScoreLable, Life;
	private String mode;
	private List<String> collective = new ArrayList<>();
	private List<GameObject> NewMoving = new ArrayList<>();
	private boolean isFinished = false;
	private int Hl = 0, Hr = 0;
	private LoggerClass logger = new LoggerClass();
	private Logger log = logger.getLog();

	public Start(int Width, int Height, String Mode, Class<ConcreteShape> dynamic, IState state, String Name) {
		factory = new ShapeFactory(logger.getLog());
		sound = Sound.getInstance();
		originator = new Originator();
		caretaker = new CareTaker();
		originator2 = new Originator();
		caretaker2 = new CareTaker();
		order = new Order();
		if (Mode == null) {
			this.mode = "time";
		} else {
			this.mode = Mode;
		}
		new Life(order, logger.getLog());
		new Time(order, logger.getLog());
		new TNT(order);
		order.setType(this.mode);
		collective = order.getCollective();
		this.width = Width;
		this.height = Height;
		Hl = 220;
		Hr = 220;
		this.PlayerName = Name;
		shape.add("shape1");
		shape.add("shape2");
		if (dynamic != null) {
			shape.add("dynamic");
		}
		this.state = state;
		ConcreteShape temp = (ConcreteShape) factory.getShape("character", state);
		temp.setX(Width / 3);
		temp.setY(Height - 250);
		temp.setControll(true);
		control.add(temp);
		for (int i = 0; i < 30; i++) {
			String fac = shape.get((int) (Math.random() * (shape.size())));
			if (fac.equals("dynamic")) {
				try {
					temp = dynamic.getDeclaredConstructor(Color.class).newInstance(
							state.getAvailabeColors()[(int) (Math.random() * state.getAvailabeColors().length)]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				temp = (ConcreteShape) factory.getShape(fac, state);
			}
			temp = (ConcreteShape) setRandomXY(temp);
			moving.add(temp);
		}
		temp = (ConcreteShape) factory.getShape("background", state);
		temp.setHeight(Height);
		temp.setWidth(Width + 15);
		temp.setPath(state.getBackground());
		constant.add(temp);
		TimeLable = createText(TimeLable, 30, 30, 0);
		ScoreLable = createText(ScoreLable, 30, 90, 1);
		if (this.mode.equals("life")) {
			Life = createText(Life, 30, 150, 2);
		}
		for (int i = 0; i < 7; i++) {
			String fac = collective.get((int) (Math.random() * (collective.size())));
			temp = (ConcreteShape) factory.getShape(fac, state);
			temp = (ConcreteShape) setRandomXY(temp);
			moving.add(temp);
		}
	}
	
	@Override
	public boolean refresh() {
		boolean timeout = System.currentTimeMillis() - startTime < MAX_TIME;
		boolean scoreout = score < 20;
		boolean lifeout = life > 0;
		
		if ((mode == "time" && timeout) || (mode == "score" && scoreout) || (mode == "life" && lifeout)) {
			/*if(!isFinished && !(mode == "time" && timeout) || (mode == "score" && scoreout) || (mode == "life" && lifeout)) {
				Statement st = new ConnectionDB().connect();
				try {
					//System.out.println(score);
				st.execute("update profile set score = "+score+" where name = '"+PlayerName+"'");
				} catch (SQLException e) {
					//e.printStackTrace();
					System.out.println("Faild Save Score");
				}
			}*/
			isFinished = true;
			Wall();
			for (int i = 0; i < NewMoving.size(); i++) {
				ConcreteShape m = (ConcreteShape) NewMoving.get(i);
				m.setX(m.getX() + 1);
				if (m.getY() == 0) {
					NewMoving.remove(m);
					i--;
				}
			}
			for (int i = moving.size() - 1; i >= 0; i--) {
				ConcreteShape shape = (ConcreteShape) moving.get(i);
				shape.setY((int) (shape.getY() + state.speed()));
				ConcreteShape temp = getUpper(Left);
				ConcreteShape temp2 = getUpper(Right);
				i = DoIntersection(new Left(), temp, shape, i, 'L', Hl, Left, originator, caretaker);
				i = DoIntersection(new Right(), temp2, shape, i, 'R', Hr, Right, originator2, caretaker2);
				if (shape.getY() == getHeight()) {
					shape = (ConcreteShape) setRandomXY(shape);
				}
			}
			log.info("check if 3 shapes of the same color being together");
			Hl = Point(caretaker, Left, Hl);
			Hr = Point(caretaker2, Right, Hr);
			if (timeout) {
				setTime();
			}
		}/* else {
			if (!(this.Written)) {
				System.out.println("written");
				Written = true;
				new write(PlayerName, score);
			}
		}*/
		return (mode == "time" && timeout) || (mode == "score" && scoreout) || (mode == "life" && lifeout);
	}

	@Override
	public int getSpeed() {
		return this.speed;
	}

	@Override
	public int getControlSpeed() {
		if (speed == 0) {
			return 10;
		}
		return speed;
	}

	@Override
	public List<GameObject> getConstantObjects() {
		return constant;
	}

	@Override
	public List<GameObject> getMovableObjects() {
		return moving;
	}

	@Override
	public List<GameObject> getControlableObjects() {
		return control;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public String getStatus() {
		switch (this.mode) {
		case "life":
			return "You Will Lose in This Mode if You Lose All Lifes";
		case "score":
			return "The Game Will end if your score 20";
		case "time":
			return "You Will Lose in This Mode if Time reaches 0:0";

		default:
			break;
		}
		return mode;
	}

	private boolean check(CareTaker care) {
		if (care.size() < 3) {
			return false;
		} else {
			if (((ConcreteShape) ((care.GetObject(care.size() - 1)).GetObject()))
					.getColor() == ((ConcreteShape) ((care.GetObject(care.size() - 2)).GetObject())).getColor()) {
				if (((ConcreteShape) ((care.GetObject(care.size() - 2)).GetObject()))
						.getColor() == ((ConcreteShape) ((care.GetObject(care.size() - 3)).GetObject())).getColor()) {
					log.info("increase score");
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkwall(Iterator o, char d) {
		if (!o.hasNext()) {
			return false;
		}
		while (o.hasNext()) {
			ConcreteShape t = (ConcreteShape) o.next();
			if (d == 'L') {
				if (t.getX() <= 0) {
					return true;
				}
			} else if (d == 'R') {
				if ((t.getX() + t.getWidth()) >= getWidth()) {
					return true;
				}
			}
		}
		return false;
	}

	private String checkShape(ConcreteShape obj) {
		try {
			if (obj.getPath().contains("shape1")
					&& (obj.getPath().substring(obj.getPath().length() - 6, obj.getPath().length())).contains("1")) {
				return obj.getPath().substring(0, obj.getPath().length() - "1.png".length()) + ".png";
			} else if (obj.getPath().contains("shape1")
					&& !(obj.getPath().substring(obj.getPath().length() - 6, obj.getPath().length())).contains("1")) {
				return obj.getPath().substring(0, obj.getPath().length() - ".png".length()) + "1.png";
			}
		} catch (Exception e) {
			// in case of dynamic linkage
			return null;
		}
		return null;
	}

	private void checkCollective(ConcreteShape o, char d) {
		if (o.getPath().contains("life+")) {
			life++;
			((Text) Life).update("Life: " + String.valueOf(life));
		} else if (o.getPath().contains("life-")) {
			life--;
			((Text) Life).update("Life: " + String.valueOf(life));
		} else if (o.getPath().contains("time+")) {
			startTime = startTime + 10 * 1000;
		} else if (o.getPath().contains("time-")) {
			startTime = startTime - 10 * 1000;
		} else if (o.getPath().contains("tnt")) {
			ConcreteShape temp;
			switch (d) {
			case 'L':
				if (Left.size() == 0) {
					if (constant.contains(Life)) {
						life--;
						((Text) Life).update("Life: " + String.valueOf(life));
					}
					break;
				}
				temp = (ConcreteShape) Left.pop();
				control.remove(temp);
				if (checkShape(temp) != null) {
					temp = (ConcreteShape) factory.getShape("shape1", state);
				} else {
					temp.setControll(false);
				}
				temp.setX((int) (Math.random() * getWidth()));
				temp.setY(-1 * (int) (Math.random() * getHeight()));
				moving.add(temp);
				caretaker.RemoveLast();
				if (Left.size() == 0) {
					Hl = 220;
				} else {
					Hl = getHeight()
							- ((ConcreteShape) ((caretaker.GetObject(caretaker.size() - 1)).GetObject())).getY();
				}
				break;
			case 'R':
				if (Right.size() == 0) {
					if (constant.contains(Life)) {
						life--;
						((Text) Life).update("Life: " + String.valueOf(life));
					}
					break;
				}
				temp = (ConcreteShape) Right.pop();
				control.remove(temp);
				if (checkShape(temp) != null) {
					temp = (ConcreteShape) factory.getShape("shape1", state);
				} else {
					temp.setControll(false);
				}
				temp.setX((int) (Math.random() * getWidth()));
				temp.setY(-1 * (int) (Math.random() * getHeight()));
				moving.add(temp);
				caretaker2.RemoveLast();
				if (Right.size() == 0) {
					Hr = 220;
				} else {
					Hr = getHeight()
							- ((ConcreteShape) ((caretaker2.GetObject(caretaker2.size() - 1)).GetObject())).getY();
				}
				break;
			default:
				break;
			}
			sound.playSound("/res/bomb.wav");
		}
	}

	private Text createText(GameObject o, int x, int y, int i) {
		o = (ConcreteShape) factory.getShape("word", state);
		o.setX(x);
		o.setY(y);
		if (i == 0) {
			((Text) o).update("Time: 0:0");
		} else if (i == 1) {
			((Text) o).update("Score: 0");
		} else {
			((Text) o).update("Life: 5");
		}
		constant.add(o);
		return (Text) o;
	}

	private ConcreteShape setRandomXY(ConcreteShape o) {
		o.setX((int) (Math.random() * getWidth()));
		o.setY(-1 * (int) (Math.random() * getHeight()));
		return o;
	}

	@SuppressWarnings("unchecked")
	private void Wall() {
		Stack<GameObject> lr = (Stack<GameObject>) Left.clone();
		lr.add(control.get(0));
		shapes = new StackIterator(lr);
		iterator = shapes.getIterator();
		lr.clear();
		lr = (Stack<GameObject>) Right.clone();
		lr.add(control.get(0));
		shapes = new StackIterator(lr);
		iterator2 = shapes.getIterator();
		if (checkwall(iterator, 'L')) {
			for (GameObject t : control) {
				ConcreteShape mv = (ConcreteShape) t;
				mv.setHorizontalL(true);
			}
		}
		if (checkwall(iterator2, 'R')) {
			for (GameObject t : control) {
				ConcreteShape mv = (ConcreteShape) t;
				mv.setHorizontalR(true);
			}
		}
	}

	private ConcreteShape getUpper(Stack<GameObject> o) {
		if (o.isEmpty()) {
			return (ConcreteShape) control.get(0);
		} else {
			return (ConcreteShape) o.peek();
		}
	}

	private int DoIntersection(Intersect intersect, ConcreteShape temp, ConcreteShape shape, int i, char d, int H,
			Stack<GameObject> o, Originator originator, CareTaker caretaker) {
		context = new Context(intersect);
		if (context.excute(temp, shape, control.get(0), getHeight() - H)) {
			log.info("intersect happened");
			if (shape.getName().equals("collective")) {
				checkCollective(shape, d);
				if (d == 'L') {
					H = Hl;
				} else if (d == 'R') {
					H = Hr;
				}
				shape = (ConcreteShape) setRandomXY(shape);
			} else if ((shape.getX() > (temp.getX() + (temp.getWidth() / 2))
					|| (shape.getX() + shape.getWidth()) < (temp.getX() + (temp.getWidth() / 2)))
					&& !(temp == control.get(0))) {
				if (constant.contains(Life)) {
					life--;
					((Text) Life).update("Life: " + String.valueOf(life));
				}
				NewMoving.add(shape);
			} else {
				if (checkShape(shape) != null) {
					moving.remove(shape);
					shape = new Shape1Static(checkShape(shape), shape.getX(), shape.getY() + 30);
					control.add(shape);
					i--;
				} else {
					shape.setControll(true);
					control.add(shape);
					moving.remove(i);
					i--;
				}
				H = H + shape.getHeight();
				o.push(shape);
				originator.SetObject(shape);
				caretaker.add(originator.SaveToMemento());
			}
		}
		if (d == 'L') {
			Hl = H;
			this.originator = originator;
			this.caretaker = caretaker;
		} else if (d == 'R') {
			Hr = H;
			this.originator2 = originator;
			this.caretaker2 = caretaker;
		}
		return i;
	}

	private int Point(CareTaker caretaker, Stack<GameObject> o, int H) {
		if (check(caretaker)) {
			for (int j = 0; j < 3; j++) {
				ConcreteShape reuse = (ConcreteShape) o.pop();
				control.remove(reuse);
				if (checkShape(reuse) != null) {
					reuse = (ConcreteShape) factory.getShape("shape1", state);
				} else {
					reuse.setControll(false);
				}
				reuse = (ConcreteShape) setRandomXY(reuse);
				moving.add(reuse);
			}
			score++;
			((Text) (ScoreLable)).update("Score: " + String.valueOf(score));
			caretaker.Remove();
			if (o.size() == 0) {
				H = 220;
			} else {
				H = getHeight() - ((ConcreteShape) ((caretaker.GetObject(caretaker.size() - 1)).GetObject())).getY();
			}
		}
		return H;
	}

	private void setTime() {
		if (!mode.equals("time")) {
			endTime = System.currentTimeMillis();
			long ti = (endTime - startTime) / 1000;
			long min = ti / 60;
			ti = ti - min * 60;
			((Text) this.TimeLable).update("Time: " + min + ":" + ti);
		} else {
			endTime = System.currentTimeMillis();
			long ti = (MAX_TIME - (endTime - startTime)) / 1000;
			long min = ti / 60;
			ti = ti - min * 60;
			((Text) this.TimeLable).update("Time: " + min + ":" + ti);
		}
	}
	public boolean isFinish() {
		return isFinished;
	}
	public int getScore() {
		return this.score;
	}
}
