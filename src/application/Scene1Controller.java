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

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.*;

public class Scene1Controller extends Application{
	private Main app;
	private int currentText = 0;
	private int isDeath = 0;
	private Stage stage;
	private Pane root;
	private Scene scene;
	private FXMLLoader loader = new FXMLLoader();
	private ArrayList<Rectangle> obstacles = new ArrayList<Rectangle>();//added from David's Implementation	
	private ArrayList<Items> items = new ArrayList<Items>();
	private ArrayList<Rectangle> warpPoints = new ArrayList<Rectangle>();
	private MediaPlayer musicData = new MediaPlayer(new Media(Paths.get("src/sounds/teleport.mp3").toUri().toString()));
	
	@FXML
	private Label dialogueText;
	
	@FXML
	private Button nextButton;
	
	ArrayList<String> dialogue = new ArrayList<String>();
 	

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
	}
	
	
	/**
	 * Takes an instance of Main and have app reference it.
	 * @param app
	 */
	public void linkToApplication(Main app) {
		this.app = app;
		musicData.setCycleCount(MediaPlayer.INDEFINITE);
		musicData.play();
	}
	
	/**
	 * Sets initial text for dialogue box and then adds subsequent text to dialogue instance variable.
	 */
	public void setDialogue() {
		dialogueText.setText("\nPRESENT\n" + app.user.getName()+ ": Where am I-");
		dialogue.add("Unknown Summoner: Go now, smite thy hevonous wretch!");
		dialogue.add(app.user.getName() + ": I-");
		dialogue.add("Unknown Summoner: Go now, grab that sword and fight!");
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
	    
	    obstacles.add(new Rectangle(409,221,140,482));
	    obstacles.add(new Rectangle(752,221,140,482));
	    obstacles.add(new Rectangle(251,24,803,199));
	    obstacles.add(new Rectangle(405,677,488,25));
	    
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
		ImageView swordLocation = new ImageView();
		Image image = new Image(new FileInputStream("src/application/images/ironsword.png"));
		swordLocation.setImage(image);
		swordLocation.setPreserveRatio(true);
		swordLocation.setFitWidth(60);
		swordLocation.setFitHeight(80);
		swordLocation.setLayoutX(620);
		swordLocation.setLayoutY(340);
		
		
		Items IronSword = new Weapons(5,"Iron Sword","Deals 5 damage.",swordLocation);
		items.add(IronSword);
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

		Rectangle warpToBattle = new Rectangle(620,313,53,59);
		warpToBattle.setFill(Color.TRANSPARENT); 
		
		warpPoints.add(warpToBattle);
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
		if (currentText < dialogue.size()) {
			dialogueText.setText(dialogue.get(currentText));
			currentText++;
		} else if(currentText >= dialogue.size()) {
			try {
				root = (Pane)loader.load(new FileInputStream("src/fxml/Scene1Map.FXML"));
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				Scene1MapController map1 = loader.getController();
				map1.linkToApplication(this.app, this, musicData);
				
				createObstacles();
				loadObstacles();
				createWarpPoints();
				loadWarpPoints();
				createItems();
				loadItems();
				
				Scene scene = new Scene(root,1320,703);
				scene.getStylesheets().add(getClass().getResource("Scene1.css").toExternalForm());
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
