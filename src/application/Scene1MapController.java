package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Scene1MapController {

	
	@FXML
	private Rectangle player;
	private int x;
	private int y;
	
	
	private ArrayList<Rectangle> obstacles = new ArrayList<Rectangle>();
	
	private Main app;
	private Scene1Controller s1;
	
	
	
	
	public void linkToApplication(Main app, Scene1Controller s1) {
		this.app = app;
		//placeObstacles();
	}
	
	
	public void movePlayer(KeyEvent event) {
		if (event.getCode()==KeyCode.A&& isCollision()){
			player.setX(x-=10);
			//if(isCollision()==false) {character.setCenterX(x+=10);}
			System.out.println("A");
		}
		if (event.getCode()==KeyCode.D && isCollision()) {
			player.setX(x+=10);
			//if(isCollision()==false) {character.setCenterX(x-=10);}
			System.out.println("D");
		}
		if (event.getCode()==KeyCode.W && isCollision()){
			player.setY(y-=10);
			//if(isCollision()==false) {character.setCenterY(y+=10);}
			System.out.println("W");
		}
		if (event.getCode()==KeyCode.S && isCollision()){
			player.setY(y+=10);
			//if(isCollision()==false) {character.setCenterY(y-=10);}
			System.out.println("S");
		}
		
	}
	
	
	public boolean isCollision() {
		boolean collision = true;
		return collision;
	}
	
}