package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class AddCowController implements Initializable {

	@FXML
	private JFXDatePicker importedDate;

	@FXML
	private JFXButton addCowButton;

	@FXML
	private JFXButton claerButton;

	@FXML
	private Label warningAddCow;

	@FXML
	private JFXTextField cowID;

	@FXML
	private JFXTextField shedNumber;

	@FXML
	private JFXTextField stallNumer;

	@FXML
	private JFXTextField age;

	@FXML
	private JFXTextField type;

	@FXML
	private JFXTextField specificLook;

	@FXML
	private JFXComboBox<?> gender;

	@FXML
	private JFXTextField height;

	@FXML
	private JFXTextField weight;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

	}


}
