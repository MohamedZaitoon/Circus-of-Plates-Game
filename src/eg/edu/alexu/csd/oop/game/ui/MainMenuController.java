package eg.edu.alexu.csd.oop.game.ui;

import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.game.cs.DynamicLinkage.Dynamic;
import eg.edu.alexu.csd.oop.game.cs.Facade.Start;
import eg.edu.alexu.csd.oop.game.cs.Factory.ConcreteShape;
import eg.edu.alexu.csd.oop.game.cs.State.IState;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;

public class MainMenuController implements Initializable {

	private World world;
	private FactoryState fs;
	private FactoryImage fi;
	private IState state;
	private Statement statement;
	private Profile user;
	String modeName;
	Class<ConcreteShape> t;
	private AudioClip audioClip;
	@FXML
	private AnchorPane workspace;

	@FXML
	private Button start;

	@FXML
	private ImageView mapImage;

	@FXML
	private Label mapName;

	@FXML
	private Label difficulity;

	@FXML
	private Canvas userImage;

	@FXML
	private Label currentScore;

	@FXML
	private Label name;

	@FXML
	private AnchorPane levels;

	@FXML
	private ImageView mapImage1;

	@FXML
	private Label mapName1;

	@FXML
	private ImageView mapImage2;

	@FXML
	private Label mapName2;

	@FXML
	private ImageView mapImage3;

	@FXML
	private Label mapName3;

	@FXML
	private AnchorPane profilePage;

	@FXML
	private ListView<String> users;

	@FXML
	private TextField newUserName;

	@FXML
	private AnchorPane scoreSheet;
	/*
	 * @FXML private AnchorPane currentMap;
	 */
	private AnchorPane currentScreen;

	@FXML
	private ListView<String> scores;
	@FXML
	private StackPane stackHard;
	@FXML
	private StackPane stackMedium;

	private ObservableList<String> usersList;

	private void setUsers() {
		usersList = FXCollections.observableArrayList();
		try {
			ResultSet r = statement.executeQuery("select name from profile");
			while (r.next()) {
				usersList.add(r.getString(1));
			}
			users.setItems(usersList);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void createUser(ActionEvent event) {
		String newName = newUserName.getText();
		if (!newName.trim().isEmpty()) {
			newUserName.setText("");
			addProfile(newName);
			setUsers();
		}
	}

	@FXML
	void deleteUser(ActionEvent event) {
		String name = users.getSelectionModel().getSelectedItem();
		if (name != null && !name.equals("guest")) {
			try {
				statement.execute("delete from profile where name = '" + name + "'");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			setUsers();
		}
	}

	@FXML
	void hideScreen(MouseEvent event) {
		hideScreen();
	}

	void hideScreen() {
		translateX(.25, currentScreen, 2000);
		newUserName.setText("");
	}

	@FXML
	void selectMap(MouseEvent event) {
		currentScreen = levels;
		translateX(.5, levels, -2000);
		fade(1, levels, 0, 1);
	}

	@FXML
	void selectUser(ActionEvent event) {
		String name = users.getSelectionModel().getSelectedItem();
		if (name != null) {
			setProfile(name);
			hideScreen();
		}
	}

	@FXML
	void showProfile(ActionEvent event) {
		currentScreen = profilePage;
		translateX(.25, profilePage, -2000);
	}

	@FXML
	void showScore(ActionEvent event) {
		currentScreen = scoreSheet;
		translateX(.25, scoreSheet, -2000);
		ObservableList<String> scoreList = FXCollections.observableArrayList();
		try {
			ResultSet r = statement.executeQuery("select * from profile");
			while (r.next()) {
				scoreList.add(r.getString(1) + "\t\t\t" + r.getString(2));
			}
			scores.setItems(scoreList);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void plugin(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(new ExtensionFilter("Jar File", "*.jar"));
		File file = fc.showOpenDialog(null);

		if (file != null) {
			Dynamic y = new Dynamic();
			if (file.getName().endsWith(".jar")) {
				t = (Class<ConcreteShape>) y.getShape(file.getAbsolutePath());
			}
		}
	}

	@FXML
	void startGame(ActionEvent event) {
		/*
		 * try { //Thread.sleep(1000);
		 * System.out.println(Thread.currentThread().getName()); } catch
		 * (InterruptedException e) { e.printStackTrace(); }
		 */

		/*
		 * Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); int
		 * screenHeight = screenSize.height - 70; int screenWidth = screenSize.width;
		 */
		audioClip.stop();
		world = new Start(1800, 900, modeName, t, this.state, name.getText());
		GameEngine.start("PlatesUBG", world);

	}

	@FXML
	void toggelSound(ActionEvent event) {
		if (audioClip.getVolume() > 0 && audioClip.isPlaying()) {
			audioClip.stop();
		} else {
			audioClip.play();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		translateX(.25, levels, 2000);
		fade(2, levels, 1, 0);
		translateX(.25, scoreSheet, 2000);
		translateX(.25, profilePage, 2000);
		translateX(.25, modes, 2000);
		init();

		translateX(.25, creditPage, 2000);

		translateY(.25, creditListLeft, 400);
		translateY(.25, creditListRight, 700);
		translateX(.25, creditListRight, -70);
		translateX(.25, creditListLeft, 85);
		
		statement = new ConnectionDB().connect();
		fs = new FactoryState();
		fi = new FactoryImage();
		user = new Profile();
		modeName = "time";
		state = ((FactoryState) fs).getState("easy");

		initiateLevels();
		initiateModeImg();
		setMap();
		createTable();
		setProfile("guest");
		setUsers();
		// sound
		final URL resource = getClass().getResource("/res/menuMusic.mp3");
		audioClip = new AudioClip(resource.toExternalForm());
		audioClip.play();

	}

	/*
	 * class SC extends Thread{ private World world; public void run() {
	 * while(!((Start)world).isFinish()) { System.out.println("test"); } int score =
	 * ((Start)world).getScore(); saveScore(score); } public void setWorld(World w)
	 * { this.world = w; }
	 * 
	 * }
	 */
	/*
	 * private void saveScore(int score) { try {
	 * statement.execute("update profile set score = "+score+" where name = '"+name.
	 * getText()+"'"); } catch (SQLException e) { e.printStackTrace();
	 * System.out.println("Faild Save Score"); } }
	 */
	private void setMap() {
		this.mapImage.setImage(fi.getImage(state.getBackground()));
		this.difficulity.setText(fs.getDifficulity(this.state));
		this.mapName.setText(this.mapName.getText());
	}

	/* ****************************** Difficulty ********************************/
	@FXML
	void selectEasy(MouseEvent event) {
		selectDiff("easy", this.mapName1);
	}

	@FXML
	void selectMedium(MouseEvent event) {
		selectDiff("medium", this.mapName2);
	}

	@FXML
	void selectHard(MouseEvent event) {
		selectDiff("hard", this.mapName3);
	}

	private void selectDiff(String difficulty, Label l) {
		this.state = fs.getState(difficulty);
		this.mapName.setText(l.getText());
		setMap();
	}

	private void initiateLevels() {
		this.mapImage1.setImage(fi.getImage(fs.getState("easy").getBackground()));
		this.mapImage2.setImage(fi.getImage(fs.getState("medium").getBackground()));
		this.mapImage3.setImage(fi.getImage(fs.getState("hard").getBackground()));
		this.mapName.setText(this.mapName1.getText());
	}

	/* **************************************************************************/
	private void translateX(double duration, Node node, double x) {
		TranslateTransition translate = new TranslateTransition(Duration.seconds(duration), node);
		translate.setByX(x);
		translate.play();
	}

	private void translateY(double duration, Node node, double y) {
		TranslateTransition translate = new TranslateTransition(Duration.seconds(duration), node);
		translate.setByY(y);
		translate.play();
	}

	private void fade(double duration, Node node, double from, double to) {
		FadeTransition fade = new FadeTransition(Duration.seconds(duration), node);
		fade.setFromValue(from);
		fade.setToValue(to);
		fade.play();
	}

	/* ********************************Database******************************* */
	private void createTable() {
		String q = "create table profile (name varchar, score int)";
		try {
			if (statement.execute(q)) {
				String initialq = "insert into profile values ('guest',0)";
				statement.execute(initialq);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println("Failed create Table");
		}
	}

	private void setProfile(String name) {
		ResultSet r;
		if (!addProfile(name)) {
			String q = "select * from profile where name = '" + name + "'";
			try {
				r = statement.executeQuery(q);
				if (!r.next())
					throw new SQLException();
				user.setName(r.getString(1));
				user.setScore(Integer.valueOf(r.getString(2)));
				this.name.setText(user.getName());
				this.currentScore.setText(user.getScore() + "");
				GraphicsContext gc = userImage.getGraphicsContext2D();
				gc.clearRect(0, 0, this.userImage.getWidth(), this.userImage.getHeight());
				gc.setFill(new Color(0, 0, 0, .5));
				gc.fillRect(0, 0, this.userImage.getWidth(), this.userImage.getHeight());

				gc.setStroke(Color.WHITE);
				gc.setFont(new Font(50));
				gc.strokeText((user.getName().trim().charAt(0) + "").toUpperCase(), 10, 50);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	private boolean addProfile(String name) {
		try {
			ResultSet r = statement.executeQuery("select name from profile where name ='" + name + "'");
			if (r != null)
				return false;
			return statement.execute("insert into profile values ('" + name + "',0)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	/* ******************************** Modes *********************************/
	@FXML
	private AnchorPane modes;
	@FXML
	private ImageView timeModeImage;
	@FXML
	private ImageView scoreModeImage;
	@FXML
	private ImageView lifeModeImage;
	@FXML
	private ImageView currentModeImg;
	@FXML
	private Label modeN;

	@FXML
	public void showMode(ActionEvent event) {
		currentScreen = modes;
		translateX(.25, modes, -2000);
	}

	@FXML
	public void selectTimeMode(MouseEvent event) {
		modeName = "time";
		this.currentModeImg.setImage(fi.getImage("res/collective/time+.png"));
		modeN.setText("Time");
	}

	@FXML
	public void selectScoreMode(MouseEvent event) {
		modeName = "score";
		this.currentModeImg.setImage(fi.getImage("res/collective/score.png"));
		modeN.setText("Score");
	}

	@FXML
	public void selectLifeMode(MouseEvent event) {
		modeName = "life";
		this.currentModeImg.setImage(fi.getImage("res/collective/life+.png"));
		modeN.setText("Life");
	}

	private void initiateModeImg() {
		this.lifeModeImage.setImage(fi.getImage("res/collective/life+.png"));
		this.scoreModeImage.setImage(fi.getImage("res/collective/score.png"));
		this.timeModeImage.setImage(fi.getImage("res/collective/time+.png"));
		this.currentModeImg.setImage(fi.getImage("res/collective/time+.png"));
		modeN.setText("Time");
	}

	/* ******************* credits*********************** */
	@FXML
	private AnchorPane creditPage;

	@FXML
	private VBox creditListLeft;

	@FXML
	private VBox creditListRight;

	private AudioClip creditMusic;

	void creditStart() {

		int animationTime = 18;

		KeyValue initKeyValue = new KeyValue(creditListLeft.translateYProperty(), 400);
		KeyFrame initFrame = new KeyFrame(Duration.ZERO, initKeyValue);
		KeyValue endKeyValue = new KeyValue(creditListLeft.translateYProperty(), -400);
		KeyFrame endFrame = new KeyFrame(Duration.seconds(animationTime), endKeyValue);
		Timeline timelineLeft = new Timeline(initFrame, endFrame);

		initKeyValue = new KeyValue(creditListRight.translateYProperty(), 700);
		initFrame = new KeyFrame(Duration.ZERO, initKeyValue);
		endKeyValue = new KeyValue(creditListRight.translateYProperty(), -425);
		endFrame = new KeyFrame(Duration.seconds(animationTime + 9.5), endKeyValue);
		Timeline timelineRight = new Timeline(initFrame, endFrame);

		timelineLeft.play();
		timelineRight.play();

		timelineRight.setOnFinished(e -> creditMusic.stop());
		timelineRight.setOnFinished(e -> translateX(0.25, creditPage, 2000));
		timelineRight.setOnFinished(e -> audioClip.play());
	}

	public void init() {
		// Create an AudioClip, which loads the audio data synchronously
		final URL resource = getClass().getResource("/res/creditMusic.mp3");
		creditMusic = new AudioClip(resource.toExternalForm());
	}

	@FXML
	void showCredit(ActionEvent event) {
		audioClip.stop();
		creditMusic.play();
		translateX(.25, creditPage, -2000);
		creditStart();
		translateY(.0025, creditListRight, 400);
		translateY(.0025, creditListLeft, 700);

	}

}