package application;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class LoginController  {

	@FXML
	private JFXButton loginButton;
	
	@FXML
	private JFXTextField userfNameText;
	
	@FXML
	private JFXPasswordField passwordText;
	
	@FXML
	private Label warningLabel;
	
	@FXML
	void login(ActionEvent event) throws IOException{
		if(userfNameText.getText().equals("admin")&&passwordText.getText().equals("1234")){
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(ClassLoader.getSystemResource("application/Main.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();		
		}	
		else {
			warningLabel.setText("Wrong Username or Password!!!");
		}
	}

//	public void login(ActionEvent event) throws IOException{
//		if(userfNameText.getText().equals("admin")&&passwordText.getText().equals("1234")){
//			((Node) event.getSource()).getScene().getWindow().hide();
//			Stage primaryStage = new Stage();
//			Parent root = FXMLLoader.load(ClassLoader.getSystemResource("application/Main.fxml"));
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();		
//		}	
//		else {
//			warningLabel.setText("Incorrect Username or Password!!!x");
//		}
//	}
}
