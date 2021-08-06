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
	private ArrayList<ImageView> warpPoints = new ArrayList<ImageView>();
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
	public void linkToApplication(Main app) {
		this.app = app;
		musicData.setCycleCount(MediaPlayer.INDEFINITE);
		musicData.play();
	}
	
	public void setDialogue() {
		dialogueText.setText("\nPRESENT\n" + app.user.getName()+ ": Where am I-");
		dialogue.add("Unknown Summoner: Go now, smite thy hevonous wretch!");
		dialogue.add(app.user.getName() + ": I-");
		dialogue.add("Unknown Summoner: Go now, grab that sword and fight!");
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
		ImageView swordLocation = new ImageView();
		swordLocation.setPreserveRatio(true);
		swordLocation.setFitWidth(53);
		swordLocation.setFitHeight(59);
		swordLocation.setLayoutX(620);
		swordLocation.setLayoutY(313);
		Items IronSword = new Weapons(5,"Iron Sword","Deals 5 damage.",swordLocation);
		items.add(IronSword);
	}
	
	public void loadItems() {
		for (int i=0; i<items.size();i++) {
			root.getChildren().add(items.get(i).getMapItem());
		}
	}
	
	public ArrayList<Items> getItemsArrayList() {
		return items;
	}
	
	public void createWarpPoints() throws FileNotFoundException {
		ImageView sword = new ImageView();
		Image image = new Image(new FileInputStream("src/application/images/ironsword.png"));
		sword.setImage(image);
		
		sword.setPreserveRatio(true);
		sword.setFitWidth(100);
	    sword.setFitHeight(100);
	    sword.setLayoutX(600);
	    sword.setLayoutY(300);
		warpPoints.add(sword);
	}
	
	public void loadWarpPoints() {
		for (int i=0; i<warpPoints.size();i++) {
			root.getChildren().add(warpPoints.get(i));
		}
	}
	
	public ArrayList<ImageView> getWarpPointArrayList() {
		return warpPoints;
	}
	
	
	public Pane getRoot() {
		return root;
	}
	
	public Stage getStage() {
		return stage;
	}
	
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
				createItems();
				loadItems();
				createWarpPoints();
				loadWarpPoints();
				
				
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
