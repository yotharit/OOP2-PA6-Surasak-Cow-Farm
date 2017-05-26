package application;

import java.io.IOException;
import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import application.util.ConnectionUtil;
import farmData.Account;
import farmData.Setting;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Node;

/**
 * LoginController work as Login.fxml's Controller
 * @author Tharit Pongsaneh
 *
 */
public class LoginController  {

	//Connection Util Invoke
	private ConnectionUtil connectionUtil = ConnectionUtil.getInstance();
	
	//FXML Attributes
	@FXML
	private JFXButton loginButton;

	@FXML
	private JFXTextField userfNameText;

	@FXML
	private JFXPasswordField passwordText;

	@FXML
	private Label warningLabel;

	@FXML
	private JFXButton signupButton;

	/**
	 * Load Login.fxml if input is correct run when loginButton Clicked
	 * @param event
	 * @throws IOException
	 * @throws SQLException
	 */
	@FXML
	void login(ActionEvent event) throws IOException, SQLException{
		//change this if logic
		ConnectionSource connectionSource = connectionUtil.getSource();
		Dao<Account, String> accountDao = connectionUtil.getAccountDao();
		connectionUtil.createAccountTable();
		if(accountDao.idExists(userfNameText.getText())){
			Account account = accountDao.queryForId(userfNameText.getText());
			if(account.getPassword().equals(passwordText.getText())){
				Dao<Setting, String> settingDao = connectionUtil.getSettingDao();
				TableUtils.createTableIfNotExists(connectionSource, Setting.class);
				if(settingDao.idExists("default")){
					Setting setting = settingDao.queryForId("default");
					setting.setCurrentUser(userfNameText.getText());
					settingDao.update(setting);
				}
				else {
					Setting setting = new Setting();
					setting.setCurrentUser(userfNameText.getText());
					settingDao.create(setting);
				}
				((Node) event.getSource()).getScene().getWindow().hide();
				Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load(ClassLoader.getSystemResource("application/Main.fxml"));
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
				primaryStage.setResizable(false);
				connectionSource.close();
			}
			else {
				warningLabel.setText("Wrong Username or Password!!!");
				connectionSource.close();
			}
		}	
		else {
			warningLabel.setText("Wrong Username or Password!!!");
		}
	}

	/**
	 * load Signup.fxml when signupButton Clicked
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void signup(ActionEvent event) throws IOException {
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(ClassLoader.getSystemResource("application/Signup.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();		
	}


}
