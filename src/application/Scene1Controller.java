package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Scene1Controller extends Application{
	private Main app;
	private int currentText = 0;
	private int isDeath = 0;
	private Stage stage;
	private Pane root;
	private FXMLLoader loader = new FXMLLoader();
	private ArrayList<Rectangle> obstacles = new ArrayList<Rectangle>();  //added from David's Implementation	
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
	}
	
	public void setDialogue() {
		dialogueText.setText("\nPRESENT\n" + app.user.getName()+ ": Where am I-");
		dialogue.add("Unknown Summoner: Go now, smite thy hevonous wretch!");
		dialogue.add(app.user.getName() + ": I-");
		dialogue.add("Unknown Summoner: Go now, grab that sword and fight!");
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
				map1.linkToApplication(this.app, this);
				
				
				
				
				
				
				
				
				//MainMenusController startScreen = loader.getController();
				//startScreen.linkToApplication(this.app);
				Scene scene = new Scene(root,1320,703);
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
