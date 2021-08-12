package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.*;
import javafx.scene.Node;
public class Scene1MapController {

	
	@FXML
	private ImageView player;
	private int x;
	private int y;
	
	
	private ArrayList<Rectangle> obstacles;
	private ArrayList<Items> items;
	private ArrayList<Rectangle> warpPoints;
	
	private Main app;
	private Scene1Controller s1;
	private Scene scene;
	private Stage primarystage;
	private Pane root;
	private MediaPlayer media;
	
	
	private Stage stage;
	private FXMLLoader loader = new FXMLLoader();
	Scene2Controller scene2 = loader.getController();
	
	
	/**
	 * Takes an instance of Main and have app reference it.
	 * Takes an instance of Scene1Controller and have s1 reference it.
	 * Takes an instance of MediaPlayer and have media reference it.
	 * @param app
	 * @param s1
	 * @param media
	 * @throws FileNotFoundException
	 */
	public void linkToApplication(Main app, Scene1Controller s1, MediaPlayer media) throws FileNotFoundException {
		this.app = app;
		this.s1 = s1;
		this.media = media;
		obstacles=s1.getObstacleArrayList();
		items = s1.getItemsArrayList();
		warpPoints = s1.getWarpPointArrayList();
		Image image = new Image(new FileInputStream("src/application/images/sprite.png"));
		player.setImage(image);
	}
	
	
	/**
	 * Takes KeyEvent as parameter. Moves character based on which key is pressed.
	 * @param event
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void movePlayer(KeyEvent event) {
		if (event.getCode()==KeyCode.A&& isCollision()){
			player.setX(x-=10);
			isItemPickup();
			isWarp(event);
			if(isCollision()==false) {player.setX(x+=10);}
		}
		if (event.getCode()==KeyCode.D && isCollision()) {
			player.setX(x+=10);
			isItemPickup();
			isWarp(event);
			if(isCollision()==false) {player.setX(x-=10);}
		}
		if (event.getCode()==KeyCode.W && isCollision()){
			player.setY(y-=10);
			isItemPickup();
			isWarp(event);
			if(isCollision()==false) {player.setY(y+=10);}
		}
		if (event.getCode()==KeyCode.S && isCollision()){
			player.setY(y+=10);
			isItemPickup();
			isWarp(event);
			if(isCollision()==false) {player.setY(y-=10);}
		}
		
	}
	
	/**
	 * Detects if the player is colliding with and obstacle.
	 * @return True if there is no collision and False if there is.
	 */
	public boolean isCollision() {
		boolean collision = true;
		for (int i=0;i<obstacles.size();i++) {
			if (player.getBoundsInParent().intersects(obstacles.get(i).getBoundsInParent())) {
				collision = false;
				break;
			}
		}
		
		return collision;
	}
	
	/**
	 * Detects if the player is colliding with an item.
	 * If player is colliding with an item, item's ImageView is removed from root and Item object is appended to user's items.
	 */
	public void isItemPickup() {
		for (int i=0; i<items.size();i++) {
			if (player.getBoundsInParent().intersects(items.get(i).getMapItem().getBoundsInParent())) {
				s1.getRoot().getChildren().remove(items.get(i).getMapItem());
				app.user.addItem(items.get(i));
				items.remove(i);
			}
	
		}
	}
	
	/**
	 * Detects if the player is colliding with a warpPoint.
	 * If player is colliding with a warpPoint, new scene is called. 
	 * @param event
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void isWarp(KeyEvent event) {
		for (int i=0;i<warpPoints.size();i++) {
			if (player.getBoundsInParent().intersects(warpPoints.get(i).getBoundsInParent())) {
				try {
					media.pause();
					Pane root = (Pane)loader.load(new FileInputStream("src/fxml/battle.FXML"));
		            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		            BattleController battleScreen = loader.getController();
		            battleScreen.linkToApplication(this.app);
		            battleScreen.setMap1(this);
		            
		            Enemies scene1Enemy = new Enemies("Black Ghost", 1, 20, "src/application/images/BlackGhost.png");
		            battleScreen.scene1 = 1;
		            scene = new Scene(root,1320,703);
		            
		            ArrayList<String> cssFiles = new ArrayList<String>();
		            cssFiles.add("Scene1.css");
		            battleScreen.initiate(app.user, scene1Enemy, 2, "src/fxml/Scene2.FXML", cssFiles);
		            
		            scene.getStylesheets().add(getClass().getResource("Battle.css").toExternalForm());
		            stage.setScene(scene);
		            stage.show();
				} catch(Exception e){e.printStackTrace();}
				break;
			}
		}
	}
	
	/**
	 * 
	 * @return scene
	 */
	public Scene getScene() {
		return scene;
	}
}
