package application;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Scene2MapController {

	@FXML
	private Rectangle player;
	private int x;
	private int y;
	
	
	private ArrayList<Rectangle> obstacles;
	private ArrayList<Items> items;
	private ArrayList<Rectangle> warpPoints;
	
	private Main app;
	private Scene2Controller s2;
	
	private Stage stage;
	private Scene scene;
	private FXMLLoader loader = new FXMLLoader();
	
	public void linkToApplication(Main app, Scene2Controller s2) {
		this.app = app;
		this.s2 = s2;
		//obstacles=s2.getObstacleArrayList();
		items = s2.getItemsArrayList();
		//warpPoints = s2.getWarpPointArrayList();
		
	}
	
	
	public void movePlayer(KeyEvent event) {
		if (event.getCode()==KeyCode.A&& isCollision()){
			player.setX(x-=10);
			isItemPickup();
			//isWarp(event);
			if(isCollision()==false) {player.setX(x+=10);}
			System.out.println("A");
		}
		if (event.getCode()==KeyCode.D && isCollision()) {
			player.setX(x+=10);
			isItemPickup();
			//isWarp(event);
			if(isCollision()==false) {player.setX(x-=10);}
			System.out.println("D");
		}
		if (event.getCode()==KeyCode.W && isCollision()){
			player.setY(y-=10);
			isItemPickup();
			//isWarp(event);
			if(isCollision()==false) {player.setY(y+=10);}
			System.out.println("W");
		}
		if (event.getCode()==KeyCode.S && isCollision()){
			player.setY(y+=10);
			isItemPickup();
			//isWarp(event);
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
				s2.getRoot().getChildren().remove(items.get(i).getMapItem());
				app.user.addItem(items.get(i));
				items.remove(i);
			}
	
		}
	}
	
	
	
	
	
}
