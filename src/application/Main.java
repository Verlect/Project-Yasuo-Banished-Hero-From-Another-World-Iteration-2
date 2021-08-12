/*
 * Project Name: Project Yasuo Banished Hero From Another Hero
 * Author: Bismarck Leung, David Tran
 */

package application;
	
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import model.User;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	FXMLLoader loader = new FXMLLoader();
	Stage primaryStage;
	User user = new User();
	Pane root;
	
	/**
	 * Initiates the mainMenu screen and will allow the game to start
	 */
	private void mainMenu() {
		try {
			Pane root = (Pane)loader.load(new FileInputStream("src/fxml/MainMenu.FXML"));
			MainMenusController mainMenus = loader.getController();
			mainMenus.linkToApplication(this);
			Scene scene = new Scene(root,1320,703);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			scene.getStylesheets().add(getClass().getResource("buttonStart.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 *Starts the program and sets the stage
	 */
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		mainMenu();
	}
	
	/**
	 * Starts the program
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
