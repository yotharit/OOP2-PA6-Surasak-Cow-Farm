package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeView;

import farmData.Account;
import farmData.Bill;
import farmData.Cow;
import farmData.Setting;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Controller for SellSummarize.fxml
 * @author Tharit Pongsaneh
 *
 */
public class SellSummarizeController implements Initializable {

	//FXML Attributes
	@FXML
	private JFXTextField sellingCountField;

	@FXML
	private JFXTextField totalIncomeField;

	@FXML
	private JFXTextField totalAssetField;

	@FXML
	private JFXTextField buyingCostField;

	@FXML
	private JFXTreeView<String> farmView;

	@FXML
	private JFXButton refreshButton;

	/**
	 * Run when FXML Loaded
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		final Node rootIcon = new ImageView(
				new Image(ClassLoader.getSystemResourceAsStream("image/1495653797_set_of_garden_tools.png")));
		final Node cowIcon = new ImageView(
				new Image(ClassLoader.getSystemResourceAsStream("image/1495653539_cow.png")));
		final Node billIcon = new ImageView(
				new Image(ClassLoader.getSystemResourceAsStream("image/1495653463_Document.png")));
		final Node accounntIcon = new ImageView(
				new Image(ClassLoader.getSystemResourceAsStream("image/1495653470_Profile01.png")));
		final Node settingIcon = new ImageView(
				new Image(ClassLoader.getSystemResourceAsStream("image/1495653489_Setting.png")));
		TreeItem<String> root = new TreeItem<String>("Sukprasert Farm", rootIcon);
		TreeItem<String> cowItem = new TreeItem<String>("Cow", cowIcon);
		TreeItem<String> accountItem = new TreeItem<String>("Account", accounntIcon);
		TreeItem<String> billItem = new TreeItem<String>("Bill", billIcon);
		TreeItem<String> settingItem = new TreeItem<String>("Setting", settingIcon);

		try {
			ConnectionSource connectionSource = new JdbcConnectionSource("jdbc:mysql://35.189.162.227:3306/sukprasert",
					"root", "1234");
			Dao<Bill, String> billDao = DaoManager.createDao(connectionSource, Bill.class);
			Dao<Cow, String> cowDao = DaoManager.createDao(connectionSource, Cow.class);
			Dao<Setting, String> settingDao = DaoManager.createDao(connectionSource, Setting.class);
			Dao<Account, String> accountDao = DaoManager.createDao(connectionSource, Account.class);
			List<Bill> billList = billDao.queryForAll();
			List<Cow> cowList = cowDao.queryForAll();
			List<Account> accountList = accountDao.queryForAll();
			int count = billList.size();
			double sum = 0;
			for (Bill bill : billList) {
				sum += Double.parseDouble(bill.getSumPrice());
			}
			sellingCountField.setText(count + "");
			totalIncomeField.setText(sum + "");
			Setting setting = settingDao.queryForId("default");
			double assetSum = 0;
			double importedPrice = 0;
			for (Cow cow : cowList) {
				assetSum += Double.parseDouble(cow.getWeight()) * setting.getCowPrice();
				importedPrice += Double.parseDouble(cow.getImportPrice());
			}
			totalAssetField.setText("" + assetSum);
			buyingCostField.setText("" + importedPrice);
			

			
			for (Cow cow : cowList) {
				cowItem.getChildren()
						.add(new TreeItem<String>("Cow-ID:" + cow.getSerialNumber() + "-" + cow.getWeight() + " Kg."));
			}
			for (Account account : accountList) {
				accountItem.getChildren().add(new TreeItem<String>(
						account.getName() + " " + account.getSurname() + " Call:" + account.getPhone()));
			}
			for (Bill bill : billList) {
				billItem.getChildren().add(
						new TreeItem<String>("Bill-ID:" + bill.getBillnumer() + "-" + bill.getSumPrice() + " Baht"));
			}
			settingItem.getChildren().add(new TreeItem<String>("Cow-Price:" + setting.getCowPrice() + " Baht"
					+ " Fertilizer-Price:" + setting.getFertilizerPrize() +" Baht"));
			root.getChildren().addAll(accountItem,cowItem,billItem,settingItem);
			farmView.setRoot(root);
			connectionSource.close();

		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		refreshButton.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler->{
			try {
				ConnectionSource connectionSource = new JdbcConnectionSource("jdbc:mysql://35.189.162.227:3306/sukprasert",
						"root", "1234");
				Dao<Bill, String> billDao = DaoManager.createDao(connectionSource, Bill.class);
				Dao<Cow, String> cowDao = DaoManager.createDao(connectionSource, Cow.class);
				Dao<Setting, String> settingDao = DaoManager.createDao(connectionSource, Setting.class);
				Dao<Account, String> accountDao = DaoManager.createDao(connectionSource, Account.class);
				List<Bill> billList = billDao.queryForAll();
				List<Cow> cowList = cowDao.queryForAll();
				List<Account> accountList = accountDao.queryForAll();
				Setting setting = settingDao.queryForId("default");
				cowItem.getChildren().clear();
				accountItem.getChildren().clear();
				billItem.getChildren().clear();
				settingItem.getChildren().clear();
				for (Cow cow : cowList) {
					cowItem.getChildren()
							.add(new TreeItem<String>("Cow-ID:" + cow.getSerialNumber() + "-" + cow.getWeight() + " Kg."));
				}
				for (Account account : accountList) {
					accountItem.getChildren().add(new TreeItem<String>(
							account.getName() + " " + account.getSurname() + " Call:" + account.getPhone()));
				}
				for (Bill bill : billList) {
					billItem.getChildren().add(
							new TreeItem<String>("Bill-ID:" + bill.getBillnumer() + "-" + bill.getSumPrice() + " Baht"));
				}
				settingItem.getChildren().add(new TreeItem<String>("Cow-Price:" + setting.getCowPrice() + " Baht"
						+ " Fertilizer-Price:" + setting.getFertilizerPrize() +" Baht"));
				
				int count = billList.size();
				double sum = 0;
				for (Bill bill : billList) {
					sum += Double.parseDouble(bill.getSumPrice());
				}
				sellingCountField.setText(count + "");
				totalIncomeField.setText(sum + "");
				double assetSum = 0;
				double importedPrice = 0;
				for (Cow cow : cowList) {
					assetSum += Double.parseDouble(cow.getWeight()) * setting.getCowPrice();
					importedPrice += Double.parseDouble(cow.getImportPrice());
				}
				totalAssetField.setText("" + assetSum);
				buyingCostField.setText("" + importedPrice);
				connectionSource.close();
				
			} catch (SQLException | IOException e) {
				// TODO: handle exception
			}
		});
	}

}
