package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Scene2Controller {

	private Main app;
	private Scene1MapController lastScene;    //change "Scene1MapController" to the class of last scene.
	private Scene scene;
	private Pane root;
	private Stage stage;
	private FXMLLoader loader = new FXMLLoader();
	
	private int textCounter;
	private int eventCounter;
	
	
	@FXML
	private Label textBox;
	private ArrayList<String> dialogue = new ArrayList<String>();
	private ArrayList<Rectangle> obstacles = new ArrayList<Rectangle>();
	private ArrayList<Items> items = new ArrayList<Items>();
	private ArrayList<Rectangle> warpPoints = new ArrayList<Rectangle>();
	
	public void linkToApplication(Main app, Scene1MapController lastScene) { //change "Scene1MapController" to the class of last scene.
		this.app = app;
		this.lastScene = lastScene;
	}
	
	
	public void addDialogue() {
		textBox.setText("Unknown Summoner: Blasphemy, how can the mandate of celestia be bound by such weakness? I’ll handle this myself…. ");
		dialogue.add("[Unknown Summoner casts Chaos Dark Flames and kills the Wicked Horror]");
		dialogue.add("Unknown Summoner: One has no use for nugatory subortanants among one’s ranks. Guards, cast this one off the Cliffs of Sacred Tears");
	}
	
	public void loadObstacles() {
		for (int i=0; i<obstacles.size();i++) {
			root.getChildren().add(obstacles.get(i));
		}
		
	}
	public void createObstacles() {
		Rectangle ob1 = new Rectangle(409,221,140,482); //Making new Obstacle and putting it in ArrayList
	    ob1.setFill(Color.TRANSPARENT);  //Change to TRANSPARENT colour later
	    obstacles.add(ob1);
	    Rectangle ob2 = new Rectangle(752,221,140,482);
	    ob2.setFill(Color.TRANSPARENT); 
	    obstacles.add(ob2);
	}
	
	public ArrayList<Rectangle> getObstacleArrayList() {
		return obstacles;
	}
	
	public void createItems() {
		Rectangle MagicalWoodenSwordLocation = new Rectangle(731,201,53,59); //change location later
		MagicalWoodenSwordLocation.setFill(Color.YELLOW);  //Cast to image with CSS later
		Items MagicalWoodenSword = new Weapons(6,"Magical Wooden Sword","Deals 6 damage.",MagicalWoodenSwordLocation);
		items.add(MagicalWoodenSword);
	}
	
	public void loadItems() {
		for (int i=0; i<items.size();i++) {
			root.getChildren().add(items.get(i).getMapItem());
		}
	}
	
	public ArrayList<Items> getItemsArrayList() {
		return items;
	}
	
	public void createWarpPoints() {
		Rectangle warp1 = new Rectangle(620,313,53,59);
		warp1.setFill(Color.BROWN);
		warpPoints.add(warp1);
	}
	
	public void loadWarpPoints() {
		for (int i=0; i<warpPoints.size();i++) {
			root.getChildren().add(warpPoints.get(i));
		}
	}
	
	public ArrayList<Rectangle> getWarpPointArrayList() {
		return warpPoints;
	}
	
	
	public Pane getRoot() {
		return root;
	}
	
	public Stage getStage() {
		return stage;
	}
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
			System.out.println("Hello");
			//callScene2 Map here
			try {
				System.out.println(1);
				root = (Pane)loader.load(new FileInputStream("src/fxml/Scene2Map.FXML"));
				System.out.println(2);
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				System.out.println(3);
				Scene2MapController map1 = loader.getController();
				System.out.println(4);
				map1.linkToApplication(this.app, this);
				System.out.println(5);
				//createObstacles();
				//loadObstacles();
				createItems();
				loadItems();
				//createWarpPoints();
				//loadWarpPoints();
				
				
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
