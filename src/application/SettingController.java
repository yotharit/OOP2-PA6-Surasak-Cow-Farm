package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.poi.hssf.record.chart.SeriesTextRecord;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import farmData.Account;
import farmData.Setting;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class SettingController implements Initializable {

	@FXML
	private JFXButton saveButton;

	@FXML
	private JFXTextField cowField;

	@FXML
	private JFXTextField ferField;

	@FXML
	private JFXTextField addressField;

	@FXML
	private JFXTextField phoneField;

	@FXML
	private JFXTextField passwordField;

	@FXML
	private JFXTextField nameField;

	@FXML
	private JFXTextField surnameField;

	@FXML
	private JFXTextField idField;

	@FXML
	private JFXTextField birthdayField;

	@FXML
	private JFXTextField usernameField;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			ConnectionSource connectionSource = new JdbcConnectionSource("jdbc:mysql://35.189.162.227:3306/sukprasert",
					"root", "1234");
			Dao<Account, String> accountDao = DaoManager.createDao(connectionSource, Account.class);
			Dao<Setting, String> settingDao = DaoManager.createDao(connectionSource, Setting.class);
			Setting setting = settingDao.queryForId("default");
			Account account = accountDao.queryForId(setting.getCurrentUser());

			usernameField.setText(account.getUsername());
			birthdayField.setText(account.getBirthDate());
			idField.setText(account.getId());
			surnameField.setText(account.getSurname());
			nameField.setText(account.getName());
			passwordField.setText(account.getPassword());
			phoneField.setText(account.getPhone());
			addressField.setText(account.getAddress());
			ferField.setText(setting.getFertilizerPrize() + "");
			cowField.setText(setting.getCowPrice() + "");
			

			connectionSource.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void save(ActionEvent event) throws SQLException, IOException{
		ConnectionSource connectionSource = new JdbcConnectionSource("jdbc:mysql://35.189.162.227:3306/sukprasert",
				"root", "1234");
		Dao<Account, String> accountDao = DaoManager.createDao(connectionSource, Account.class);
		Dao<Setting, String> settingDao = DaoManager.createDao(connectionSource, Setting.class);
		Setting setting = settingDao.queryForId("default");
		Account account = accountDao.queryForId(setting.getCurrentUser());
		
		account.setAddress(addressField.getText());
		account.setPassword(passwordField.getText());
		account.setPhone(phoneField.getText());
		setting.setCowPrice(Double.parseDouble(cowField.getText()));
		setting.setFertilizerPrize(Double.parseDouble(ferField.getText()));
		
		settingDao.update(setting);
		accountDao.update(account);
		
		connectionSource.close();

	}
	
}
