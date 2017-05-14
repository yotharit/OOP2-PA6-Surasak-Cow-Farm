package application;

import java.io.IOException;
import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import farmData.Account;
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
	private JFXButton signupButton;

	@FXML
	void login(ActionEvent event) throws IOException, SQLException{
		//change this if logic
		ConnectionSource connectionSource =
				new JdbcConnectionSource("jdbc:mysql://35.189.162.227:3306/sukprasert","root","1234");
		Dao<Account, String> accountDao = DaoManager.createDao(connectionSource, Account.class);
		TableUtils.createTableIfNotExists(connectionSource, Account.class);
		if(accountDao.idExists(userfNameText.getText())){
			Account account = accountDao.queryForId(userfNameText.getText());
			if(account.getPassword().equals(passwordText.getText())){
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
