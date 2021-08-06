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
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.*;

public class MainMenusController extends Application{
	private Main app;
	private Stage stage;
	private FXMLLoader loader = new FXMLLoader();
	MediaPlayer musicData = new MediaPlayer(new Media(Paths.get("src/sounds/mainMenu.mp3").toUri().toString()));
	
	@FXML
	private Button startButton;
	
	@FXML
	private Button quitButton;

	

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void linkToApplication(Main app) {
		this.app = app;
		stage = app.primaryStage;
		app.user.getAbility().clear();
		app.user.getItems().clear();
		musicData.setCycleCount(MediaPlayer.INDEFINITE);
		musicData.play();
	}
	
	@FXML
	public void quit() {
		stage.close();
	}
	
	@FXML
	void startScreen(ActionEvent event) throws IOException{
		try {
			Pane root = (Pane)loader.load(new FileInputStream("src/fxml/startScreen.FXML"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			StartScreenController startScreen = loader.getController();
			startScreen.linkToApplication(this.app, musicData);
			Scene scene = new Scene(root,1320,703);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			scene.getStylesheets().add(getClass().getResource("buttonStart.css").toExternalForm());
			scene.getStylesheets().add(getClass().getResource("TextField.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
