package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PrologueController extends Application{
	private Main app;
	private int currentText = 0;
	private Stage stage;
	private Pane root;
	private FXMLLoader loader = new FXMLLoader();
	
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
		dialogueText.setText("PROLOGUE\n" + app.name + ", you're an aspiring computer scientist... ");
		dialogue.add("who has been working hard this past week preparing for an upcoming coding entrance exam.");
		dialogue.add("If you pass, you will be enrolled in the most prestigious university; Kinonatsu University of Coding and Technology.");
		dialogue.add("Unfortunately, when the results came back, you find yourself to have failed miserably. ");
		dialogue.add("Encased in an aura of melancholy,you confine yourself in your room, sulking in depression.");
		dialogue.add("As you thought about the transpired events that have led you to this sorrowful state, your mind starts to drift.");
		dialogue.add("As your mind melds with the abyss, you see only darkness in front of you. Until…..");
		dialogue.add("A blast of primordial energy flashes in front of you, so bright that it burned your eyes. ");
		dialogue.add("When you came to, you realized you were no longer in your bedroom… You weren’t too sure where you were at all..");
	}
	
	public void next(KeyEvent event) {
		if (currentText < dialogue.size()) {
			dialogueText.setText(dialogue.get(currentText));
			currentText++;
		} else if(currentText >= dialogue.size()) {
			try {
				root = (Pane)loader.load(new FileInputStream("src/fxml/Scene1.FXML"));
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				Scene1Controller scene1controller = loader.getController();
				scene1controller.linkToApplication(this.app);
				scene1controller.setDialogue();
				
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
