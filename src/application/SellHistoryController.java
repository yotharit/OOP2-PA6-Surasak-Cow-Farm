package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class SellHistoryController implements Initializable {

	@FXML
	private JFXTreeTableView<HistoryRow> sellTable;

	@FXML
	private JFXTextField billIDField;

    @FXML
    private JFXButton findButton;

    @FXML
    private JFXButton refreshButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		JFXTreeTableColumn<HistoryRow, String> billNum = new JFXTreeTableColumn<>("Bill Number");
		billNum.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<HistoryRow, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<HistoryRow, String> param) {
						return param.getValue().getValue().billNumber;
					}
				});
		billNum.setPrefWidth(100);

		JFXTreeTableColumn<HistoryRow, String> buyerName = new JFXTreeTableColumn<>("Buyer Name");
		buyerName.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<HistoryRow, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<HistoryRow, String> param) {
						return param.getValue().getValue().buyerName;
					}
				});
		buyerName.setPrefWidth(200);

		JFXTreeTableColumn<HistoryRow, String> date = new JFXTreeTableColumn<>("Date");
		date.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<HistoryRow, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<HistoryRow, String> param) {
						return param.getValue().getValue().date;
					}
				});
		date.setPrefWidth(160);

		JFXTreeTableColumn<HistoryRow, String> info = new JFXTreeTableColumn<>("Sell Information");
		info.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<HistoryRow, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<HistoryRow, String> param) {
						return param.getValue().getValue().sellInformation;
					}
				});
		info.setPrefWidth(500);
		
		JFXTreeTableColumn<HistoryRow, String> price = new JFXTreeTableColumn<>("Sum");
		price.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<HistoryRow,String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<HistoryRow, String> param) {
				return param.getValue().getValue().price;
			}
		});
		price.setPrefWidth(180);

		ObservableList<HistoryRow> historyRow = FXCollections.observableArrayList();
		TreeItem<HistoryRow> root = new RecursiveTreeItem<>(historyRow, RecursiveTreeObject::getChildren);
		sellTable.getColumns().setAll(billNum, buyerName, date, info,price);
		sellTable.setRoot(root);
		sellTable.setShowRoot(false);

		try {
			ConnectionSource connectionSource = new JdbcConnectionSource("jdbc:mysql://35.189.162.227:3306/sukprasert",
					"root", "1234");
			Dao<Bill, String> billDao = DaoManager.createDao(connectionSource, Bill.class);
			List<Bill> billList = billDao.queryForAll();
			for (Bill bill : billList) {
				sellTable.getRoot().getChildren().add(new TreeItem<HistoryRow>(new HistoryRow(bill.getBillnumer(),
						bill.getBuyer(), bill.getDate(), bill.getSellInfomation(), bill.getSumPrice())));
			}
			connectionSource.close();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		refreshButton.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler ->{
			try {
				sellTable.getRoot().getChildren().clear();
				ConnectionSource connectionSource = new JdbcConnectionSource("jdbc:mysql://35.189.162.227:3306/sukprasert",
						"root", "1234");
				Dao<Bill, String> billDao = DaoManager.createDao(connectionSource, Bill.class);
				List<Bill> billList = billDao.queryForAll();
				for (Bill bill : billList) {
					sellTable.getRoot().getChildren().add(new TreeItem<HistoryRow>(new HistoryRow(bill.getBillnumer(),
							bill.getBuyer(), bill.getDate(), bill.getSellInfomation(), bill.getSumPrice())));
				}
				connectionSource.close();
				billIDField.clear();
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		findButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			try {
				sellTable.getRoot().getChildren().clear();
				ConnectionSource connectionSource = new JdbcConnectionSource("jdbc:mysql://35.189.162.227:3306/sukprasert",
						"root", "1234");
				Dao<Bill, String> billDao = DaoManager.createDao(connectionSource, Bill.class);
				List<Bill> billList = billDao.queryForAll();
				for (Bill bill : billList) {
					if(bill.getBillnumer().contains(billIDField.getText()))
					sellTable.getRoot().getChildren().add(new TreeItem<HistoryRow>(new HistoryRow(bill.getBillnumer(),
							bill.getBuyer(), bill.getDate(), bill.getSellInfomation(), bill.getSumPrice())));
					
				}
				connectionSource.close();
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
		});
	}
	
	

	@FXML
	void onComboChanged(ActionEvent e) {

	}

}

class HistoryRow extends RecursiveTreeObject<HistoryRow> {

	StringProperty billNumber;
	StringProperty buyerName;
	StringProperty date;
	StringProperty sellInformation;
	StringProperty price;

	public HistoryRow(String billNumber, String buyerName, String date, String sellInformation,String price) {
		this.billNumber = new SimpleStringProperty(billNumber);
		this.buyerName = new SimpleStringProperty(buyerName);
		this.date = new SimpleStringProperty(date);
		this.sellInformation = new SimpleStringProperty(sellInformation);
		this.price = new SimpleStringProperty(price);
	}
	
	

	public StringProperty getPrice() {
		return price;
	}



	public void setPrice(StringProperty price) {
		this.price = price;
	}



	public StringProperty getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(StringProperty billNumber) {
		this.billNumber = billNumber;
	}

	public StringProperty getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(StringProperty buyerName) {
		this.buyerName = buyerName;
	}

	public StringProperty getDate() {
		return date;
	}

	public void setDate(StringProperty date) {
		this.date = date;
	}

	public StringProperty getSellInformation() {
		return sellInformation;
	}

	public void setSellInformation(StringProperty sellInformation) {
		this.sellInformation = sellInformation;
	}

}