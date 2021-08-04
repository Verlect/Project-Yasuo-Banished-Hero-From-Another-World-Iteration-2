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
import javafx.stage.Stage;

public class StartScreenController extends Application{
	private Main app;
	private Stage stage;
	private Scene scene;
	private Pane root;
	private FXMLLoader loader = new FXMLLoader();
	
	@FXML
	private TextField name;
	
	@FXML
	private Button beginButton;
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void linkToApplication(Main app) {
		this.app = app;
	}
	
	@FXML
	public void submitName(ActionEvent event) {
		app.name = name.getText();
		System.out.println(app.name);
		
		try {
			root = (Pane)loader.load(new FileInputStream("src/application/Scene1.FXML"));
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
	
	public void Scene1() {
		
	}
}
