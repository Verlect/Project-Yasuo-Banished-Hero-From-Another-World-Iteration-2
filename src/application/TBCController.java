package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class TBCController extends Application{
	private FXMLLoader loader = new FXMLLoader();
	Stage primaryStage = new Stage();
	Pane root;
	private Main app;
	MediaPlayer musicData = new MediaPlayer(new Media(Paths.get("src/sounds/TBC.mp3").toUri().toString()));
	
	@FXML
	private Button returnButton;
	
	@FXML
	private ImageView death;

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void linkToApplication(Main app) throws FileNotFoundException {
		this.app = app;
		Image image = new Image(new FileInputStream("src/application/images/TBC.png"));
		death.setImage(image);
		musicData.setCycleCount(MediaPlayer.INDEFINITE);
		musicData.play();
	}
	
	@FXML
	public void returnMain(ActionEvent event) throws FileNotFoundException, IOException {
		musicData.pause();
		root = (Pane)loader.load(new FileInputStream("src/fxml/MainMenu.FXML"));
		primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		MainMenusController mainMenus = loader.getController();
		mainMenus.linkToApplication(this.app);
		Scene scene = new Scene(root,1320,703);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		scene.getStylesheets().add(getClass().getResource("buttonStart.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
}
