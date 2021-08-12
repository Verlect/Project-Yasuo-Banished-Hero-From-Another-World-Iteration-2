/*
 * Project Name: Project Yasuo Banished Hero From Another Hero
 * Author: Bismarck Leung, David Tran
 */
package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.*;

public class Scene2MapController {

	@FXML
	private ImageView player;
	private int x;
	private int y;
	private MediaPlayer media;
	
	
	private ArrayList<Rectangle> obstacles;
	private ArrayList<Items> items;
	private ArrayList<Rectangle> warpPoints;
	
	private Main app;
	private Scene2Controller s2;
	
	private Stage stage;
	private Scene scene;
	private FXMLLoader loader = new FXMLLoader();
	private Pane root;
	
	
	
	/**
	 * Takes an instance of Main and have app reference it.
	 * Takes an instance of Scene2Controller and have s2 reference it.
	 * Takes an instance of MediaPlayer and have media reference it.
	 * @param app
	 * @param s2
	 * @param media
	 * @throws FileNotFoundException
	 */
	public void linkToApplication(Main app, Scene2Controller s2, MediaPlayer media) throws FileNotFoundException {
		this.app = app;
		this.s2 = s2;
		this.media = media;
		obstacles=s2.getObstacleArrayList();
		items = s2.getItemsArrayList();
		warpPoints = s2.getWarpPointArrayList();
		Image image = new Image(new FileInputStream("src/application/images/leftsprite.png"));
		player.setImage(image);
	}
	
	
	/**
	 * Takes KeyEvent as parameter. Moves character based on which key is pressed.
	 * @param event
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void movePlayer(KeyEvent event) throws FileNotFoundException, IOException {
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
				s2.getRoot().getChildren().remove(items.get(i).getMapItem());
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
	public void isWarp(KeyEvent event) throws FileNotFoundException, IOException {
		for (int i=0;i<warpPoints.size();i++) {
			if (player.getBoundsInParent().intersects(warpPoints.get(i).getBoundsInParent())) {
				media.pause();
				root = (Pane)loader.load(new FileInputStream("src/fxml/battle2.FXML"));
	            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	            BattleController2 battleScreen = loader.getController();
	            battleScreen.linkToApplication(this.app);
	            
	            Enemies scene2Enemy = new Enemies("Black Ghost", 0, 15, "src/application/images/BlackGhost.png");
	            scene = new Scene(root,1320,703);
	            
	            ArrayList<String> cssFiles = new ArrayList<String>();
	            cssFiles.add("TBC.css");
	            battleScreen.initiate(app.user, scene2Enemy, 3, "src/fxml/TBC.FXML", cssFiles);
	            
	            scene.getStylesheets().add(getClass().getResource("Battle2.css").toExternalForm());
	            stage.setScene(scene);
	            stage.show();
			}
		}
	}
	
	
	
}
