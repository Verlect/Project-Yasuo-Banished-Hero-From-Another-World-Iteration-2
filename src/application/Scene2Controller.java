/*
 * Project Name: Project Yasuo Banished Hero From Another Hero
 * Author: Bismarck Leung, David Tran
 */
package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.*;


public class Scene2Controller {

	private Main app;
	private BattleController lastScene;    //change "Scene1MapController" to the class of last scene.
	private Scene scene;
	private Pane root;
	private Stage stage;
	private FXMLLoader loader = new FXMLLoader();
	private MediaPlayer musicData = new MediaPlayer(new Media(Paths.get("src/sounds/scene2.mp3").toUri().toString()));
	
	private int textCounter;
	private int eventCounter;
	
	
	@FXML
	private Label textBox;
	private ArrayList<String> dialogue = new ArrayList<String>();
	private ArrayList<Rectangle> obstacles = new ArrayList<Rectangle>();
	private ArrayList<Items> items = new ArrayList<Items>();
	private ArrayList<Rectangle> warpPoints = new ArrayList<Rectangle>();
	
	/**
	 * Takes an instance of Main and have app reference it.
	 * Takes an instance of BattleController and have lastScene reference it.
	 * @param app
	 * @param lastScene
	 */
	public void linkToApplication(Main app, BattleController lastScene) { //change "Scene1MapController" to the class of last scene.
		this.app = app;
		this.lastScene = lastScene;
		musicData.setCycleCount(MediaPlayer.INDEFINITE);
		musicData.play();
	}
	
	
	/**
	 * Sets initial text for dialogue box and then adds subsequent text to dialogue instance variable.
	 */
	public void addDialogue() {
		textBox.setText("Unknown Summoner: Blasphemy, how can the mandate of celestia be bound by such weakness? I’ll handle this myself…. ");
		dialogue.add("[Unknown Summoner casts Chaos Dark Flames and kills the Wicked Horror]");
		dialogue.add("Unknown Summoner: One has no use for nugatory subortanants among one’s ranks. Guards, cast this one off the Cliffs of Sacred Tears");
	}
	
	/**
	 * Takes all Rectangle in obstacles instance variable and adds their nodes into root.
	 */
	public void loadObstacles() {
		for (int i=0; i<obstacles.size();i++) {
			root.getChildren().add(obstacles.get(i));
		}
		
	}
	
	/**
	 * Creates Rectangle objects and appends them to obstacles array list. 
	 */
	public void createObstacles() {
		
		obstacles.add(new Rectangle(6,7,1311,93));
		obstacles.add(new Rectangle(334,136,283,153));
		obstacles.add(new Rectangle(14,165,177,252));
		obstacles.add(new Rectangle(14,612,364,77));
		obstacles.add(new Rectangle(14,418,108,194));
		obstacles.add(new Rectangle(1078,536,228,153));
		obstacles.add(new Rectangle(1241,18,65,518));
		obstacles.add(new Rectangle(923,18,155,163));
		obstacles.add(new Rectangle(1013,576,65,113));
		obstacles.add(new Rectangle(378,627,636,62));
		
		
		for (int i=0; i<obstacles.size();i++) {
			obstacles.get(i).setFill(Color.TRANSPARENT);
		}
	}
	
	/**
	 * @return obstacles
	 */
	public ArrayList<Rectangle> getObstacleArrayList() {
		return obstacles;
	}
	
	/**
	 * Creates Items objects and appends to items arraylist.
	 * @throws FileNotFoundException
	 */
	public void createItems() throws FileNotFoundException {
		ImageView MagicalWoodenSwordLocation = new ImageView();
		MagicalWoodenSwordLocation.setPreserveRatio(true);
		MagicalWoodenSwordLocation.setFitWidth(53);
		MagicalWoodenSwordLocation.setFitHeight(59);
		MagicalWoodenSwordLocation.setLayoutX(731);
		MagicalWoodenSwordLocation.setLayoutY(201);
		Image image = new Image(new FileInputStream("src/application/images/woodenMSword.png"));
		MagicalWoodenSwordLocation.setImage(image);
		Items MagicalWoodenSword = new Weapons(20,"Magical Wooden Sword","Deals 20 damage.",MagicalWoodenSwordLocation);
		items.add(MagicalWoodenSword);
	}
	
	/**
	 * For all the objects in items, their ImageView gets added as a node to root.
	 */
	public void loadItems() {
		for (int i=0; i<items.size();i++) {
			root.getChildren().add(items.get(i).getMapItem());
		}
	}
	
	/**
	 * @return items
	 */
	public ArrayList<Items> getItemsArrayList() {
		return items;
	}
	
	/**
	 * Creates Rectangle objects and appends them to warpPoints array list.
	 */
	public void createWarpPoints() {
		Rectangle warp1 = new Rectangle(210,100,72,93);
		warp1.setFill(Color.TRANSPARENT);
		warpPoints.add(warp1);
	}
	
	
	/**
	 * Takes all Rectangle in warpPoints instance variable and adds their nodes into root.
	 */
	public void loadWarpPoints() {
		for (int i=0; i<warpPoints.size();i++) {
			root.getChildren().add(warpPoints.get(i));
		}
	}
	
	/**
	 * @return warpPoints
	 */
	public ArrayList<Rectangle> getWarpPointArrayList() {
		return warpPoints;
	}
	
	/**
	 * 
	 * @return root
	 */
	public Pane getRoot() {
		return root;
	}
	
	/**
	 * 
	 * @return stage
	 */
	public Stage getStage() {
		return stage;
	}
	
	/**
	 * Takes in a KeyEvent, and updates the label with given dialogue.
	 * When there is no more dialogue, next scene is called.
	 * @param event
	 */
	public void next(KeyEvent event) {
		if (textCounter<dialogue.size()) {
			textBox.setText(dialogue.get(textCounter));
			textCounter++;
		}
		else if (eventCounter==0){
			lastScene.getScene().getStylesheets().add(getClass().getResource("Scene2.css").toExternalForm());
			textBox.setText("[You get overwealmed, and dragged away by the Summoner's Honour Gaurds...]");
			eventCounter++;
		}
		else if(eventCounter==1){
			lastScene.getScene().getStylesheets().add(getClass().getResource("FallingAbyss.css").toExternalForm());
			textBox.setText(app.user.getName()+": . . .");
			eventCounter++;
		}
		else {
			try {
				root = (Pane)loader.load(new FileInputStream("src/fxml/Scene2Map.FXML"));
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				Scene2MapController map1 = loader.getController();
				map1.linkToApplication(this.app, this, musicData);
				createObstacles();
				loadObstacles();
				createItems();
				loadItems();
				createWarpPoints();
				loadWarpPoints();
				
				
				Scene scene = new Scene(root,1320,703);
				scene.getStylesheets().add(getClass().getResource("Scene2Map.css").toExternalForm());
				stage.setScene(scene);
				stage.show();
				scene.getRoot().requestFocus();
			} catch(FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
	
}
