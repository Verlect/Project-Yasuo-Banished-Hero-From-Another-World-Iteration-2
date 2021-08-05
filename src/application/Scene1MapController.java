package application;

import java.io.FileInputStream;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Node;
public class Scene1MapController {

	
	@FXML
	private Rectangle player;
	private int x;
	private int y;
	
	
	private ArrayList<Rectangle> obstacles;
	private ArrayList<Items> items;
	private ArrayList<Rectangle> warpPoints;
	
	private Main app;
	private Scene1Controller s1;
	
	private Stage stage;
	private FXMLLoader loader = new FXMLLoader();
	
	
	
	public void linkToApplication(Main app, Scene1Controller s1) {
		this.app = app;
		this.s1 = s1;
		obstacles=s1.getObstacleArrayList();
		items = s1.getItemsArrayList();
		warpPoints = s1.getWarpPointArrayList();
		
	}
	
	
	public void movePlayer(KeyEvent event) {
		if (event.getCode()==KeyCode.A&& isCollision()){
			player.setX(x-=10);
			isItemPickup();
			isWarp(event);
			if(isCollision()==false) {player.setX(x+=10);}
			System.out.println("A");
		}
		if (event.getCode()==KeyCode.D && isCollision()) {
			player.setX(x+=10);
			isItemPickup();
			isWarp(event);
			if(isCollision()==false) {player.setX(x-=10);}
			System.out.println("D");
		}
		if (event.getCode()==KeyCode.W && isCollision()){
			player.setY(y-=10);
			isItemPickup();
			isWarp(event);
			if(isCollision()==false) {player.setY(y+=10);}
			System.out.println("W");
		}
		if (event.getCode()==KeyCode.S && isCollision()){
			player.setY(y+=10);
			isItemPickup();
			isWarp(event);
			if(isCollision()==false) {player.setY(y-=10);}
			System.out.println("S");
		}
		
	}
	
	
	public boolean isCollision() {
		boolean collision = true;
		for (int i=0;i<obstacles.size();i++) {
			if (player.getBoundsInParent().intersects(obstacles.get(i).getBoundsInParent())) {
				System.out.println("Collision");
				collision = false;
				break;
			}
		}
		
		return collision;
	}
	
	public void isItemPickup() {
		for (int i=0; i<items.size();i++) {
			if (player.getBoundsInParent().intersects(items.get(i).getMapItem().getBoundsInParent())) {
				System.out.println("Found Item");
				s1.getRoot().getChildren().remove(items.get(i).getMapItem());
				app.user.addItem(items.get(i));
				items.remove(i);
			}
	
		}
	}
	
	public void isWarp(KeyEvent event) {
		for (int i=0;i<warpPoints.size();i++) {
			if (player.getBoundsInParent().intersects(warpPoints.get(i).getBoundsInParent())) {
				try {
				System.out.println("WARPING TIME");
				//Insert new Root Scene Controller etc for next scene here
				//s1.getStage().close();
				Pane root = (Pane)loader.load(new FileInputStream("src/fxml/startScreen.FXML"));
	            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	            StartScreenController startScreen = loader.getController();
	            startScreen.linkToApplication(this.app);
	            Scene scene = new Scene(root,1320,703);
	            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	            scene.getStylesheets().add(getClass().getResource("buttonStart.css").toExternalForm());
	            scene.getStylesheets().add(getClass().getResource("TextField.css").toExternalForm());
	            stage.setScene(scene);
	            stage.show();
				} catch(Exception e){}
				break;
			}
		}
	}
	
}
