package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import farmData.Cow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 * AddCowController Work as a controller Class for AddCow.fxml
 * @author Tharit Pongsaneh
 *
 */
public class AddCowController implements Initializable {

	
	//FXML Attributes
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
	private JFXComboBox<String> gender;

	@FXML
	private JFXTextField height;

	@FXML
	private JFXTextField weight;

    @FXML
    private JFXTextField importedPrice;
	
    /**
     * Initialize when fxml is load
     */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		gender.getItems().add("Male");
		gender.getItems().add("Female");
		gender.setEditable(false);
		gender.setPromptText("Gender");

	}

	/**
	 * Clear Field when clear button clicked
	 * @param e
	 */
	@FXML
	void clear(ActionEvent e){
		height.clear();
		weight.clear();
		specificLook.clear();
		type.clear();
		age.clear();
		stallNumer.clear();
		shedNumber.clear();
		cowID.clear();
		importedPrice.clear();
	}

	/**
	 * Empty action need to bind it with commbobox
	 * @param e
	 */
	@FXML
	void onComboChanged(ActionEvent e) {

	}
	
	/**
	 * Add Cow when add button clicked
	 * @param event
	 * @throws SQLException
	 * @throws IOException
	 */
	@FXML
	void addCow(ActionEvent event) throws SQLException, IOException{
		if(height.getText().isEmpty() || weight.getText().isEmpty() || specificLook.getText().isEmpty() 
				|| type.getText().isEmpty() || age.getText().isEmpty() || stallNumer.getText().isEmpty()
				|| shedNumber.getText().isEmpty() || cowID.getText().isEmpty() || gender.getValue().isEmpty()
				|| importedDate.getValue().toString().isEmpty()|| importedPrice.getText().isEmpty()){
			warningAddCow.setText("Filling all the Information!!!");
		}
		else {
			ConnectionSource connectionSource =
					new JdbcConnectionSource("jdbc:mysql://35.189.162.227:3306/sukprasert","root","1234");
			Dao<Cow, String> cowDao = DaoManager.createDao(connectionSource, Cow.class);
			TableUtils.createTableIfNotExists(connectionSource, Cow.class);
			Cow cow = new Cow();
			cow.setAge(age.getText());
			cow.setHeight(height.getText());
			cow.setWeight(weight.getText());
			cow.setType(type.getText());
			cow.setSpecificLook(specificLook.getText());
			cow.setStall(stallNumer.getText());
			cow.setShed(shedNumber.getText());
			cow.setImportedDate(importedDate.getValue().toString());
			cow.setSex(gender.getValue());
			cow.setSerialNumber(cowID.getText());
			cow.setImportPrice(importedPrice.getText());
			cowDao.create(cow);
			((Node) event.getSource()).getScene().getWindow().hide();
			connectionSource.close();
		}
	}
}