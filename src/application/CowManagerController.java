package application;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CowManagerController {
	
    @FXML
    private JFXButton addcowButton;
    
    @FXML
    void addCow() throws IOException {
    	Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(ClassLoader.getSystemResource("application/AddCow.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();		
    }
    
    
	
}
