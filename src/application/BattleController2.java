/*
 * Project Name: Project Yasuo Banished Hero From Another Hero
 * Author: Bismarck Leung, David Tran
 */

package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.*;

public class BattleController2 extends Application{
	private Enemies enemyEntity;
	private User user;
	private int turnNumber = 1;
	private Main app;
	private int playerStun = 0;
	private int enemyStun = 0;
	private String nextScene;
	private ArrayList<String> cssFiles;
	private FXMLLoader loader = new FXMLLoader();
	private FXMLLoader loader2 = new FXMLLoader();
	private Stage primarystage = new Stage();
	private Pane root;
	private int newscene;
	int scene1 = 0;
	private Scene1MapController map1Control;
	private MediaPlayer musicData = new MediaPlayer(new Media(Paths.get("src/sounds/battle2.mp3").toUri().toString()));
	
	@FXML
	private ImageView player;
	
	@FXML
	private ImageView enemypic;
	
	@FXML
	private Label turnText;
	
	@FXML
	private Label attackText;
	
	@FXML
	private Label enemyText;
	
	@FXML
	private VBox attackMenu;
	
	@FXML
	private VBox itemMenu;
	
	@FXML
	private VBox abilityMenu;
	
	@FXML
	private Label userHealth;
	
	@FXML
	private Label enemyHealth;
	
	@FXML
	private Label stunText;
	
	private Random random = new Random();
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Initiates the class and sets the class instance variable for the battle scene
	 * Creates the interface for the battle scene, such as the buttons for the items and abilities.
	 * @param user
	 * @param enemy
	 * @param nextScene
	 * @param scenePath
	 * @param cssFiles
	 * @throws FileNotFoundException
	 */
	public void initiate(User user, Enemies enemy, int nextScene, String scenePath, ArrayList<String> cssFiles) throws FileNotFoundException {
		this.enemyEntity = enemy;
		this.user = user;
		this.newscene = nextScene;
		this.nextScene = scenePath;
		this.cssFiles = cssFiles;
		
		musicData.setCycleCount(MediaPlayer.INDEFINITE);
		musicData.play();
		
		ArrayList<Items> items = user.getItems();
		items.clear();
		ArrayList<Ability> ability = user.getAbility();
		items.add(new Potions(10, "Water Bottle (Diluted)", "Heals for 10 health"));
		ability.add(new Ability("Short Shock", "Stuns for 2 turns", 10, 2));
		
		Image imageP = new Image(new FileInputStream("src/application/images/leftsprite.png"));
		player.setImage(imageP);
		
		this.userHealth.setText("Your Health: " + this.user.getHealth());
		this.enemyHealth.setText("Enemy Health: " + this.enemyEntity.getHealth());
		
		FileInputStream inputstream = new FileInputStream(enemy.getPicture()); 
		Image image = new Image(inputstream); 
		this.turnText.setText("Turn " + this.turnNumber);
		enemypic.setImage(image);
		Button button = new Button("Basic Attack - Deal a random amount of damage");
		button.setOnAction(event -> {
			this.attackText.setText("");
			this.enemyHealth.setText("");
			this.stunText.setText("");
			this.turnText.setText("Turn " + this.turnNumber);
			int rand = random.nextInt(5);
			this.enemyEntity.takeDamage(rand);
			this.attackText.setText("You did " + rand + " damage");
			this.enemyHealth.setText("Enemy Health: " + this.enemyEntity.getHealth());
			enemyTurn();
			stunCheck();
			turnNumber++;
			try {
				isDeath(event);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
		button.wrapTextProperty().setValue(true);
		attackMenu.getChildren().add(button);
		
		for(int i = 0; i < items.size(); i++) {
			Button itembutton = new Button(items.get(i).getName());
			itembutton.wrapTextProperty().setValue(true);
			int thisDamage = items.get(i).getDamage();
			Items temp = items.get(i);
			itembutton.setOnAction(event -> {
				this.turnText.setText("Turn " + this.turnNumber);
				this.attackText.setText("");
				this.enemyHealth.setText("");
				this.stunText.setText("");
				if (temp instanceof Weapons) {
					int rand = random.nextInt(thisDamage);
					this.enemyEntity.takeDamage(rand);
					this.attackText.setText("You did " + rand + " damage");
					this.enemyHealth.setText("Enemy Health: " + this.enemyEntity.getHealth());
					
				} else if (temp instanceof Potions) {
					this.user.heal(temp.getHeal());
					this.attackText.setText("You healed for " + temp.getHeal() + " health");
					this.enemyHealth.setText("Enemy Health: " + this.enemyEntity.getHealth());
					itembutton.setVisible(false);
					items.remove(temp);
				}
				enemyTurn();
				stunCheck();
				turnNumber++;
				try {
					isDeath(event);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        });
			itemMenu.getChildren().add(itembutton);
		}
		
		for(int i = 0; i < ability.size(); i++) {
			Button abilityButton = new Button(ability.get(i).getDescription());
			abilityButton.wrapTextProperty().setValue(true);
			int stunNumber = ability.get(i).getStunTime();
			abilityButton.setOnAction(event -> {
				this.attackText.setText("");
				this.enemyHealth.setText("");
				this.stunText.setText("");
				this.turnText.setText("Turn " + this.turnNumber);
				enemyStun += stunNumber;
				this.attackText.setText("You stunned the enemy for " + stunNumber + " turns.");
				this.enemyHealth.setText("Enemy Health: " + this.enemyEntity.getHealth());
				enemyTurn();
				stunCheck();
				turnNumber++;
				try {
					isDeath(event);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			abilityMenu.getChildren().add(abilityButton);
		}
	}

	/**
	 * Links the class with the original application class
	 * @param app
	 */
	public void linkToApplication(Main app) {
		// TODO Auto-generated method stub
		this.app = app;
	}
	
	/**
	 * Checks if the user is stun, if user is stunned, it will go through enemy turns
	 * until it is not.
	 */
	private void stunCheck() {
		int stunCount = 0;
		while (playerStun > 0) {
			stunCount++;
			this.turnNumber++;
			enemyTurn();
			playerStun--;
			this.enemyText.setText("You were stunned for " + stunCount + " turns.");
		}
	}
	
	/**
	 * This simulates the enemy turn and deals damage and applies a stun to the player
	 * when nessesary
	 */
	private void enemyTurn() {
		if (enemyStun == 0) {
			int end = random.nextInt(this.enemyEntity.getDamage()+1);
			this.user.takeDamage(end);
			this.enemyText.setText("You took " + end + " damage");
			this.userHealth.setText("Your Health: " + this.user.getHealth());
			int stun = random.nextInt(this.enemyEntity.getStunDuration()+1);
			playerStun += stun;
		} else if (enemyStun > 0) {
			this.userHealth.setText("Your Health: " + this.user.getHealth());
			this.stunText.setText(this.enemyEntity.getName() + " is stunned for " + enemyStun + " turn(s).");
		}
		
		if (enemyStun > 0) {
			enemyStun--;
		}
	}
	
	/**
	 * Checks if the player or enemy has 0 health, if player dies, 
	 * moves on to death scene (unless its scene 1), if player wins, 
	 * move on to next scene (unless in scene 1).
	 * @param event
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void isDeath(ActionEvent event) throws FileNotFoundException, IOException {
		
		if (this.user.getHealth() <= 0 && scene1 == 0) {
			musicData.pause();
			root = (Pane)loader.load(new FileInputStream("src/fxml/death.FXML"));
			primarystage = (Stage)((Node)event.getSource()).getScene().getWindow();
			DeathController death = loader.getController();
			death.linkToApplication(this.app);
			Scene scene = new Scene(root,1320,703);
			scene.getStylesheets().add(getClass().getResource("death.css").toExternalForm());
			primarystage.setScene(scene);
			primarystage.show();
		} else if (this.enemyEntity.getHealth() <= 0 || (this.user.getHealth() <= 0 && scene1 == 1)) {
			musicData.pause();
			Pane roots = (Pane)loader.load(new FileInputStream(this.nextScene));
			primarystage = (Stage)((Node)event.getSource()).getScene().getWindow();
			TBCController TBCControl = loader.getController();
			TBCControl.linkToApplication(this.app);
			
			Scene scenes = new Scene(roots,1320,703);
			for (int x = 0; x < cssFiles.size(); x++) {
				scenes.getStylesheets().add(getClass().getResource(this.cssFiles.get(x)).toExternalForm());
			}
			primarystage.setScene(scenes);
			primarystage.show();
			scenes.getRoot().requestFocus();
		}
	}
	
	/**
	 * Sets the scene 1 map controller for use in the battle
	 * @param copy
	 */
	public void setMap1(Scene1MapController copy) {
		this.map1Control = copy;
	}
}
