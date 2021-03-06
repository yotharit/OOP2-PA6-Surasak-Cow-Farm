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

/**
 * Controller for SellingManagement.fxml
 * @author tharitpongsaneh
 *
 */
public class SellingManagementController implements Initializable {

	private int currentRowNumber = 0;

	//FXML Attributes
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
	private JFXTextField sumField;

	@FXML
	private JFXTreeTableView<BillRow> sellTable;

	/**
	 * Run when FXML loaded add action to each fxml elements
	 */
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
		id.setPrefWidth(105.3987342);

		JFXTreeTableColumn<BillRow, String> description = new JFXTreeTableColumn<>("Description");
		description.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<BillRow, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<BillRow, String> param) {
						return param.getValue().getValue().description;
					}
				});
		description.setPrefWidth(400);

		JFXTreeTableColumn<BillRow, String> price = new JFXTreeTableColumn<>("Price (Kg)");
		price.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<BillRow, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<BillRow, String> param) {
						return param.getValue().getValue().price;
					}
				});
		price.setPrefWidth(120);

		// set ObservableList to be added
		ObservableList<BillRow> billRow = FXCollections.observableArrayList();

		// add ObservableList to treeview
		TreeItem<BillRow> root = new RecursiveTreeItem<>(billRow, RecursiveTreeObject::getChildren);
		sellTable.getColumns().setAll(rowNo, itemType, id, description, price);
		sellTable.setRoot(root);
		sellTable.setShowRoot(false);

		sumField.setText("0");

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
			if (addInfoField.getText() == "" || productCombo.getValue() == null) {
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
							currentRowNumber = sellTable.getRoot().getChildren().size();
							Setting setting = settingDao.queryForId("default");
							double cal = Double.parseDouble(cow.getWeight()) * setting.getCowPrice();
							sellTable.getRoot().getChildren()
									.add(new TreeItem<BillRow>(new BillRow(currentRowNumber + "",
											productCombo.getValue(), addInfoField.getText(), "Cow : Serial-Number:"
													+ addInfoField.getText() + " " + cow.getWeight() + " Kg.",
											cal + "")));
							connectionSource.close();
							int x = sellTable.getRoot().getChildren().size();
							double sum = 0;
							for (int j = 0; j < x; j++) {
								double added = Double
										.parseDouble(sellTable.getRoot().getChildren().get(j).getValue().getPrice());
								sum += added;
								sellTable.getRoot().getChildren().get(j).getValue().setRowNo((j + 1) + "");
							}
							sumField.setText(sum + "");

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
						currentRowNumber = sellTable.getRoot().getChildren().size();
						sellTable.getRoot().getChildren()
								.add(new TreeItem<BillRow>(new BillRow(currentRowNumber + "", productCombo.getValue(),
										addInfoField.getText(), "Fertilizer : Weight " + addInfoField.getText() + "Kg.",
										cal + "")));
						connectionSource.close();
						int x = sellTable.getRoot().getChildren().size();
						double sum = 0;
						for (int j = 0; j < x; j++) {
							double added = Double
									.parseDouble(sellTable.getRoot().getChildren().get(j).getValue().getPrice());
							sum += added;
							sellTable.getRoot().getChildren().get(j).getValue().setRowNo((j + 1) + "");
						}
						sumField.setText(sum + "");

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
			int x = sellTable.getRoot().getChildren().size();
			double sum = 0;
			for (int j = 0; j < x; j++) {
				double added = Double.parseDouble(sellTable.getRoot().getChildren().get(j).getValue().getPrice());
				sum += added;
				sellTable.getRoot().getChildren().get(j).getValue().setRowNo((j + 1) + "");
			}
			sumField.setText(sum + "");
		});

		sellButton.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler -> {
			if (sellTable.getRoot().getChildren().size() == 0 || billIDfield.getText().isEmpty()
					|| nameField.getText().isEmpty() || dateField.getValue().toString().isEmpty()
					|| billIDfield.getText().isEmpty()) {
				status.setText("Fill All Information!!!");
			} else {
				try {
					ConnectionSource connectionSource = new JdbcConnectionSource(
							"jdbc:mysql://35.189.162.227:3306/sukprasert", "root", "1234");
					Dao<Cow, String> cowDao = DaoManager.createDao(connectionSource, Cow.class);
					Dao<Bill, String> billDao = DaoManager.createDao(connectionSource, Bill.class);
					TableUtils.createTableIfNotExists(connectionSource, Bill.class);
					String information = "";
					int k = sellTable.getRoot().getChildren().size();
					for (int i = 0; i < k; i++) {
						String Type = sellTable.getRoot().getChildren().get(i).getValue().getItemType().charAt(0) + "";
						String Info = sellTable.getRoot().getChildren().get(i).getValue().getId();
						if (i == k - 1) {
							if (Type.equals("F")) {
								information = information + Type + Info;
							} else {
								information = information + Type + Info + "-" + cowDao.queryForId(Info).getWeight();
							}
						} else {
							if (Type.equals("F")) {
								information = information + Type + Info + ",";
							} else {
								information = information + Type + Info + "-" + cowDao.queryForId(Info).getWeight() + ",";
							}						}
						if (Type.equals("C")) {
							cowDao.deleteById(Info);
						}
					}
					Bill bill = new Bill();
					bill.setBillnumer(billIDfield.getText());
					bill.setBuyer(nameField.getText());
					bill.setDate(dateField.getValue().toString());
					bill.setSellInfomation(information);
					bill.setSumPrice(sumField.getText());
					if (billDao.idExists(billIDfield.getText())) {
						status.setText("Bill ID Exist!!!!");
						connectionSource.close();
					} else {
						billDao.create(bill);
						connectionSource.close();
					}
					
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				sellTable.getRoot().getChildren().clear();
				billIDfield.clear();
				sumField.clear();
				nameField.clear();
				dateField.setValue(null);
				addInfoField.clear();
				productCombo.setValue(null);
			}
		});
	}

	@FXML
	void onComboChanged(ActionEvent e) {

	}

}

/**
 * Class use to Keep Row information
 * @author Tharit Pongsaneh
 *
 */
class BillRow extends RecursiveTreeObject<BillRow> {

	StringProperty rowNo;
	StringProperty itemType;
	StringProperty id;
	StringProperty description;
	StringProperty price;

	public BillRow(String rowNo, String itemType, String id, String description, String price) {
		this.rowNo = new SimpleStringProperty(rowNo);
		this.itemType = new SimpleStringProperty(itemType);
		this.id = new SimpleStringProperty(id);
		this.description = new SimpleStringProperty(description);
		this.price = new SimpleStringProperty(price);
	}

	public String getPrice() {
		return price.getValue();
	}

	public void setPrice(String price) {
		this.price.setValue(price);
		;
	}

	public String getDescription() {
		return description.getValue();
	}

	public void setDescription(String description) {
		this.description.setValue(description);
		;
	}

	public String getRowNo() {
		return rowNo.getValue();
	}

	public void setRowNo(String rowNo) {
		this.rowNo.set(rowNo);
	}

	public String getItemType() {
		return itemType.getValue();
	}

	public void setItemType(String itemType) {
		this.itemType.setValue(itemType);
	}

	public String getId() {
		return id.getValue();
	}

	public void setId(String id) {
		this.id.setValue(id);
	}

}