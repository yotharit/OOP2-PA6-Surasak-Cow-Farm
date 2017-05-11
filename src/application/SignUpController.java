package application;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class SignUpController {

	@FXML
	private JFXTextField nameField;

	@FXML
	private JFXTextField surnameField;

	@FXML
	private JFXTextField idField;

	@FXML
	private JFXDatePicker dateField;

	@FXML
	private JFXTextField addressField;

	@FXML
	private JFXTextField phoneField;

	@FXML
	private JFXTextField usernameField;

	@FXML
	private JFXTextField passwordField;

	@FXML
	private JFXButton signUpButton;

	@FXML
	private JFXButton clearButton;
	
	@FXML
	private Label warningLabel;

	@FXML
	void clear(ActionEvent event) {
		surnameField.clear();
		idField.clear();
		addressField.clear();
		phoneField.clear();
		usernameField.clear();
		passwordField.clear();
		nameField.clear();
	}
	
	@FXML
	void signUp(ActionEvent event) {
		if(surnameField.getText().isEmpty() || idField.getText().isEmpty() || addressField.getText().isEmpty()
				|| phoneField.getText().isEmpty() || usernameField.getText().isEmpty() 
				|| passwordField.getText().isEmpty() || nameField.getText().isEmpty() || dateField.getValue() == null ){
			warningLabel.setText("Input All Information!!!");
		}
		else {
			((Node) event.getSource()).getScene().getWindow().hide();
			//Add Logic
		}
	}


}
