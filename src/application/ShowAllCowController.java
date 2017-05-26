package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.j256.ormlite.dao.Dao;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import application.util.ConnectionUtil;
import farmData.Cow;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class ShowAllCowController implements Initializable {

	@FXML
	private JFXTreeTableView<CowRow> cowTable;

	@FXML
	private JFXTextField numTable;

	@FXML
	private JFXButton findButton;

	@FXML
	private JFXButton refreshButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		JFXTreeTableColumn<CowRow, String> cowId = new JFXTreeTableColumn<>("COW-ID");
		cowId.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<CowRow, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<CowRow, String> param) {
						return param.getValue().getValue().getCowID();
					}
				});

		JFXTreeTableColumn<CowRow, String> gender = new JFXTreeTableColumn<>("Gender");
		gender.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<CowRow, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<CowRow, String> param) {
						return param.getValue().getValue().getGender();
					}
				});

		JFXTreeTableColumn<CowRow, String> weight = new JFXTreeTableColumn<>("Weight (Kg.)");
		weight.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<CowRow, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<CowRow, String> param) {
						return param.getValue().getValue().getWeight();
					}
				});

		ObservableList<CowRow> cowRow = FXCollections.observableArrayList();
		TreeItem<CowRow> root = new RecursiveTreeItem<>(cowRow, RecursiveTreeObject::getChildren);
		cowTable.getColumns().setAll(cowId, gender, weight);
		cowTable.setRoot(root);
		cowTable.setShowRoot(false);

		ConnectionUtil conUtil = ConnectionUtil.getInstance();
		Dao<Cow, String> cowDao = conUtil.getCowDao();
		try {
			for (Cow cow : cowDao.queryForAll()) {
				root.getChildren()
						.add(new TreeItem<CowRow>(new CowRow(cow.getSerialNumber(), cow.getSex(), cow.getWeight())));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		findButton.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler -> {
			cowTable.getRoot().getChildren().clear();
			try {
				for (Cow cow : cowDao.queryForAll()) {
					if (cow.getSerialNumber().contains(numTable.getText())) {
						root.getChildren().add(
								new TreeItem<CowRow>(new CowRow(cow.getSerialNumber(), cow.getSex(), cow.getWeight())));
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		refreshButton.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler -> {
			cowTable.getRoot().getChildren().clear();
			try {
				for (Cow cow : cowDao.queryForAll()) {
					root.getChildren()
							.add(new TreeItem<CowRow>(new CowRow(cow.getSerialNumber(), cow.getSex(), cow.getWeight())));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

}

class CowRow extends RecursiveTreeObject<CowRow> {

	StringProperty cowID;
	StringProperty gender;
	StringProperty weight;

	public CowRow(String cowID, String gender, String weight) {
		this.cowID = new SimpleStringProperty(cowID);
		this.gender = new SimpleStringProperty(gender);
		this.weight = new SimpleStringProperty(weight);
	}

	public StringProperty getCowID() {
		return cowID;
	}

	public void setCowID(StringProperty cowID) {
		this.cowID = cowID;
	}

	public StringProperty getGender() {
		return gender;
	}

	public void setGender(StringProperty gender) {
		this.gender = gender;
	}

	public StringProperty getWeight() {
		return weight;
	}

	public void setWeight(StringProperty weight) {
		this.weight = weight;
	}

}