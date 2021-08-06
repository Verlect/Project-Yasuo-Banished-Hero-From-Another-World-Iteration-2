package application;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;

import java.util.concurrent.TimeUnit;

public class Scene2Controller {

	
	private Main app;
	private Scene1MapController lastScene;    //change "Scene1MapController" to the class of last scene.
	
	private int textCounter;
	private int eventCounter;
	
	
	@FXML
	private Label textBox;
	private ArrayList<String> dialogue = new ArrayList<String>();
	
	
	public void linkToApplication(Main app, Scene1MapController lastScene) { //change "Scene1MapController" to the class of last scene.
		this.app = app;
		this.lastScene = lastScene;
	}
	
	
	public void addDialogue() {
		textBox.setText("Unknown Summoner: Blasphemy, how can the mandate of celestia be bound by such weakness? I’ll handle this myself…. ");
		dialogue.add("[Unknown Summoner casts Chaos Dark Flames and kills the Wicked Horror]");
		dialogue.add("Unknown Summoner: One has no use for nugatory subortanants among one’s ranks. Guards, cast this one off the Cliffs of Sacred Tears");
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
		}
		else {
			
			//callScene2 Map here
			
			
			
		}
	}
	
}
