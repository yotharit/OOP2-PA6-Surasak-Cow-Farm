package application;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import farmData.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
			warningLabel.setText("Input Missing Information!!!");
		}
		else {
			String databaseUrl = "jdbc:h2:mem:account";
			try {
				ConnectionSource connectionSource =
						new JdbcConnectionSource("jdbc:mysql://35.189.162.227:3306/sukprasert","root","1234");
				Dao<Account, String> accountDao = DaoManager.createDao(connectionSource, Account.class);
				TableUtils.createTableIfNotExists(connectionSource, Account.class);
				Account account = new Account();
				String username = usernameField.getText();
				String password = passwordField.getText();
				String id = idField.getText();
				String name = nameField.getText();
				String surname = surnameField.getText();
				String address = addressField.getText();
				String phone = phoneField.getText();
				LocalDate date = dateField.getValue();
				account.setUsername(username);
				account.setPassword(password);
				account.setId(id);
				account.setName(name);
				account.setSurname(surname);
				account.setAddress(address);
				account.setBirthDate(date.toString());
				account.setPhone(phone);
				accountDao.create(account);
				connectionSource.close();
				((Node) event.getSource()).getScene().getWindow().hide();
			} catch (SQLException | IOException e) {
				e.printStackTrace();
				warningLabel.setText("Connection Error!!!");
			}


		}
	}


}
