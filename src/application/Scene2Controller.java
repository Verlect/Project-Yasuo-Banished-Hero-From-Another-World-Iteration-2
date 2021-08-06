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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
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
	
	private int textCounter;
	private int eventCounter;
	
	
	@FXML
	private Label textBox;
	private ArrayList<String> dialogue = new ArrayList<String>();
	private ArrayList<Rectangle> obstacles = new ArrayList<Rectangle>();
	private ArrayList<Items> items = new ArrayList<Items>();
	private ArrayList<Rectangle> warpPoints = new ArrayList<Rectangle>();
	
	public void linkToApplication(Main app, BattleController lastScene) { //change "Scene1MapController" to the class of last scene.
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
		Rectangle ob1 = new Rectangle(6,7,1311,93); //Making new Obstacle and putting it in ArrayList
	    ob1.setFill(Color.TRANSPARENT);  
	    obstacles.add(ob1);
	}
	
	public ArrayList<Rectangle> getObstacleArrayList() {
		return obstacles;
	}
	
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
	
	public void loadItems() {
		for (int i=0; i<items.size();i++) {
			root.getChildren().add(items.get(i).getMapItem());
		}
	}
	
	public ArrayList<Items> getItemsArrayList() {
		return items;
	}
	
	public void createWarpPoints() {
		Rectangle warp1 = new Rectangle(210,100,72,93);
		warp1.setFill(Color.TRANSPARENT);
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
			try {
				root = (Pane)loader.load(new FileInputStream("src/fxml/Scene2Map.FXML"));
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				Scene2MapController map1 = loader.getController();
				map1.linkToApplication(this.app, this);
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
