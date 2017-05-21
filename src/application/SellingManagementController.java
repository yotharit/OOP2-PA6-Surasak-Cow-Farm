package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import farmData.Bill;
import farmData.Cow;
import farmData.Setting;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class SellingManagementController implements Initializable {

	private int currentRowNumber = 0;

	@FXML
	private JFXTextField billIDfield;

	@FXML
	private JFXTextField nameField;

	@FXML
	private JFXDatePicker dateField;

	@FXML
	private JFXButton addButton;

	@FXML
	private JFXComboBox<String> productCombo;

	@FXML
	private JFXTextField addInfoField;

	@FXML
	private JFXButton removeButton;

	@FXML
	private JFXButton sellButton;

	@FXML
	private JFXTextField currentCowPriceField;

	@FXML
	private JFXTextField currentFertilizerPriceField;

	@FXML
	private Label status;

	@FXML
	private JFXTreeTableView<BillRow> sellTable;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		productCombo.getItems().add("Cow");
		productCombo.getItems().add("Fertilizer");
		productCombo.setEditable(false);
		productCombo.setPromptText("Product");

		JFXTreeTableColumn<BillRow, String> rowNo = new JFXTreeTableColumn<>("No");
		rowNo.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<BillRow, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<BillRow, String> param) {
						return param.getValue().getValue().rowNo;
					}
				});
		rowNo.setPrefWidth(60);

		JFXTreeTableColumn<BillRow, String> itemType = new JFXTreeTableColumn<>("Item Type");
		itemType.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<BillRow, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<BillRow, String> param) {
						return param.getValue().getValue().itemType;
					}
				});
		itemType.setPrefWidth(105.3987342);

		JFXTreeTableColumn<BillRow, String> id = new JFXTreeTableColumn<>("ID / Weight");
		id.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<BillRow, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<BillRow, String> param) {
						return param.getValue().getValue().id;
					}
				});
		id.setPrefWidth(326.2341772);

		JFXTreeTableColumn<BillRow, String> price = new JFXTreeTableColumn<>("Price");
		price.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<BillRow, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<BillRow, String> param) {
						return param.getValue().getValue().price;
					}
				});
		price.setPrefWidth(300);

		// set ObservableList to be added
		ObservableList<BillRow> billRow = FXCollections.observableArrayList();

		// add ObservableList to treeview
		TreeItem<BillRow> root = new RecursiveTreeItem<>(billRow, RecursiveTreeObject::getChildren);
		sellTable.getColumns().setAll(rowNo, itemType, id, price);
		sellTable.setRoot(root);
		sellTable.setShowRoot(false);

		try {
			ConnectionSource connectionSource = new JdbcConnectionSource("jdbc:mysql://35.189.162.227:3306/sukprasert",
					"root", "1234");
			Dao<Setting, String> settingDao = DaoManager.createDao(connectionSource, Setting.class);
			Setting setting = settingDao.queryForId("default");
			currentCowPriceField.setText(setting.getCowPrice() + "");
			currentFertilizerPriceField.setText(setting.getFertilizerPrize() + "");
			connectionSource.close();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			if (addInfoField.getText() == "" || productCombo.getValue() == "") {
				status.setText("Fill in all Information");
			} else {
				status.setText("");
				if (productCombo.getValue().equalsIgnoreCase("cow")) {
					try {
						ConnectionSource connectionSource = new JdbcConnectionSource(
								"jdbc:mysql://35.189.162.227:3306/sukprasert", "root", "1234");
						Dao<Cow, String> cowDao = DaoManager.createDao(connectionSource, Cow.class);
						Dao<Setting, String> settingDao = DaoManager.createDao(connectionSource, Setting.class);
						if (cowDao.idExists(addInfoField.getText())) {
							Cow cow = cowDao.queryForId(addInfoField.getText());
							currentRowNumber++;
							Setting setting = settingDao.queryForId("default");
							double cal = Double.parseDouble(cow.getWeight()) * setting.getCowPrice();
							sellTable.getRoot().getChildren()
									.add(new TreeItem<BillRow>(new BillRow(currentRowNumber + "",
											productCombo.getValue(), addInfoField.getText(), cal + "")));
							connectionSource.close();
						} else {
							connectionSource.close();
							status.setText("This CowID doesn't exist!!!");
						}
					} catch (SQLException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					try {
						ConnectionSource connectionSource = new JdbcConnectionSource(
								"jdbc:mysql://35.189.162.227:3306/sukprasert", "root", "1234");
						Dao<Setting, String> settingDao = DaoManager.createDao(connectionSource, Setting.class);
						Setting setting = settingDao.queryForId("default");
						double cal = Double.parseDouble(addInfoField.getText()) * setting.getFertilizerPrize();
						currentRowNumber++;
						sellTable.getRoot().getChildren().add(new TreeItem<BillRow>(new BillRow(currentRowNumber + "",
								productCombo.getValue(), "Fertilizer " + addInfoField.getText() + " kg", cal + "")));
						connectionSource.close();

					} catch (SQLException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		removeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			int i = sellTable.getSelectionModel().getSelectedIndex();
			sellTable.getRoot().getChildren().remove(i);
		});
		
	}

	@FXML
	void onComboChanged(ActionEvent e) {

	}

}

class BillRow extends RecursiveTreeObject<BillRow> {

	StringProperty rowNo;
	StringProperty itemType;
	StringProperty id;
	StringProperty price;

	public BillRow(String rowNo, String itemType, String id, String price) {
		this.rowNo = new SimpleStringProperty(rowNo);
		this.itemType = new SimpleStringProperty(itemType);
		this.id = new SimpleStringProperty(id);
		this.price = new SimpleStringProperty(price);
	}
}