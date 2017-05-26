package application;

import java.io.IOException;
import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import farmData.Cow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * CowManagerController is a controller for CowManager.fxml
 * @author Tharit Pongsaneh
 *
 */
public class CowManagerController {

	//FXML Attributes
	@FXML
	private JFXTextField cowID;

	@FXML
	private JFXButton findButton;

	@FXML
	private JFXTextField importedDateField;

	@FXML
	private JFXTextField shedField;

	@FXML
	private JFXTextField stallField;

	@FXML
	private JFXTextField ageField;

	@FXML
	private JFXTextField genderField;

	@FXML
	private JFXTextField typeField;

	@FXML
	private JFXTextField specificLookField;

	@FXML
	private JFXTextField heightField;

	@FXML
	private JFXTextField weightField;

	@FXML
	private JFXDatePicker firstvaccineField;

	@FXML
	private JFXTextField firstvaccineInfoField;

	@FXML
	private JFXDatePicker secondvaccineField;

	@FXML
	private JFXTextField secondvaccineInfoField;

	@FXML
	private JFXDatePicker thirdvaccineField;

	@FXML
	private JFXTextField thirdvaccineInfoField;

	@FXML
	private JFXButton saveButton;

	@FXML
	private JFXButton addcowButton;

	@FXML
	private JFXButton removeButton;
	
	@FXML
	private Label warningCowLabel;

	@FXML
    private JFXButton showButton;

	/**
	 * Open AddCow.fxml when button clicked
	 * @throws IOException
	 */
	@FXML
	void addCow() throws IOException {
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(ClassLoader.getSystemResource("application/AddCow.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();		
	}

	/**
	 * Find cow information from database when find button clicked
	 * @throws SQLException
	 * @throws IOException
	 */
	@FXML
	void findCow() throws SQLException, IOException {
		warningCowLabel.setText("");
		firstvaccineField.setValue(null);
		secondvaccineField.setValue(null);
		thirdvaccineField.setValue(null);
		ConnectionSource connectionSource =
				new JdbcConnectionSource("jdbc:mysql://35.189.162.227:3306/sukprasert","root","1234");
		Dao<Cow, String> cowDao = DaoManager.createDao(connectionSource, Cow.class);
		TableUtils.createTableIfNotExists(connectionSource, Cow.class);
		if(cowDao.idExists(cowID.getText())){
			Cow cow = cowDao.queryForId(cowID.getText());
			importedDateField.setText(cow.getImportedDate());
			stallField.setText(cow.getStall());
			shedField.setText(cow.getShed());
			ageField.setText(cow.getAge());
			genderField.setText(cow.getSex());
			typeField.setText(cow.getType());
			specificLookField.setText(cow.getSpecificLook());
			heightField.setText(cow.getHeight());
			weightField.setText(cow.getWeight());
			firstvaccineField.setPromptText(cow.getFirstvaccineDate());
			firstvaccineInfoField.setText(cow.getFirstvaccineInfo());
			secondvaccineField.setPromptText(cow.getSecondvaccineDate());
			secondvaccineInfoField.setText(cow.getSecondvaccineInfo());
			thirdvaccineField.setPromptText(cow.getThirdvaccineDate());
			thirdvaccineInfoField.setText(cow.getThirdvaccineInfo());
			connectionSource.close();
		}
		else if(cowID.getText().equals("")){
			warningCowLabel.setText("Insert Cow ID!!!");
			connectionSource.close();
		}
		else {
			warningCowLabel.setText("Not Found Cow ID!!!");
			connectionSource.close();
		}
	}

	/**
	 * Save Changed information to Database when save button clicked
	 * @throws SQLException
	 * @throws IOException
	 */
	@FXML
	void save() throws SQLException, IOException {
		ConnectionSource connectionSource =
				new JdbcConnectionSource("jdbc:mysql://35.189.162.227:3306/sukprasert","root","1234");
		Dao<Cow, String> cowDao = DaoManager.createDao(connectionSource, Cow.class);
		TableUtils.createTableIfNotExists(connectionSource, Cow.class);
		Cow cow = cowDao.queryForId(cowID.getText());
		cow.setImportedDate(importedDateField.getText());
		cow.setStall(stallField.getText());
		cow.setShed(shedField.getText());
		cow.setAge(ageField.getText());
		cow.setSex(genderField.getText());
		cow.setType(cow.getType());
		cow.setSpecificLook(specificLookField.getText());
		cow.setHeight(heightField.getText());
		cow.setWeight(weightField.getText());
		if(firstvaccineField.getValue()!=null)
		cow.setFirstvaccineDate(firstvaccineField.getValue().toString());
		cow.setFirstvaccineInfo(firstvaccineInfoField.getText());
		if(secondvaccineField.getValue()!=null)
		cow.setSecondvaccineDate(secondvaccineField.getValue().toString());
		cow.setSecondvaccineInfo(secondvaccineInfoField.getText());
		if(thirdvaccineField.getValue()!=null)
		cow.setThirdvaccineDate(thirdvaccineField.getValue().toString());
		cow.setThirdvaccineInfo(thirdvaccineInfoField.getText());
		cowDao.update(cow);
		connectionSource.close();
	}

	/**
	 * Remove cow from database when removeButton Clicked
	 * @throws SQLException
	 * @throws IOException
	 */
	@FXML
	void remove() throws SQLException, IOException {
		ConnectionSource connectionSource =
				new JdbcConnectionSource("jdbc:mysql://35.189.162.227:3306/sukprasert","root","1234");
		Dao<Cow, String> cowDao = DaoManager.createDao(connectionSource, Cow.class);
		TableUtils.createTableIfNotExists(connectionSource, Cow.class);
		Cow cow = cowDao.queryForId(cowID.getText());
		cowDao.delete(cow);
		connectionSource.close();
		warningCowLabel.setText("");
	}
	
	/**
	 * load ShowAllCow.fxml when show button Clicked
	 * @throws IOException
	 */
	@FXML
	void show() throws IOException {
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(ClassLoader.getSystemResource("application/ShowAllCow.fxml"));
		Scene loginScene = new Scene(root);
		loginScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(loginScene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}
