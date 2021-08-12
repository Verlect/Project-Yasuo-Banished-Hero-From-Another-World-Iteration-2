/*
 * Project Name: Project Yasuo Banished Hero From Another Hero
 * Author: Bismarck Leung, David Tran
 */

package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class StartScreenController extends Application{
	private Main app;
	private Stage stage;
	private Scene scene;
	private Pane root;
	private FXMLLoader loader = new FXMLLoader();
	private MediaPlayer mainMenuMusic;
	
	@FXML
	private TextField name;
	
	@FXML
	private Button beginButton;
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Inititates the function and sets the Main application and mediaplayer instance
	 * variables
	 * @param app
	 * @param mainMenu
	 */
	public void linkToApplication(Main app, MediaPlayer mainMenu) {
		this.app = app;
		this.mainMenuMusic = mainMenu;
	}
	
	/**
	 * This is where the user will be able to set the name of their character
	 * and will load the next scene after.
	 * @param event
	 */
	@FXML
	public void submitName(ActionEvent event) {
		app.user.setName(name.getText()); 
		
		
		try {
			this.mainMenuMusic.pause();
			root = (Pane)loader.load(new FileInputStream("src/fxml/Prologue.FXML"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			PrologueController prologuecontroller = loader.getController();
			prologuecontroller.linkToApplication(this.app);
			prologuecontroller.setDialogue();
			Scene scene = new Scene(root,1320,703);
			scene.getStylesheets().add(getClass().getResource("Prologue.css").toExternalForm());
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
