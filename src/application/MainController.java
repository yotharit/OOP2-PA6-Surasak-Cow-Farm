package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainController implements Initializable{

	@FXML
	private AnchorPane anchorPlane;

	@FXML
	private JFXHamburger hamburger;

	@FXML
	private JFXDrawer drawer;

	@FXML
	private StackPane stackPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {

			//Setting Up Main Layer
			AnchorPane businessPane = FXMLLoader.load(ClassLoader.getSystemResource("application/SellingManagement.fxml"));
			AnchorPane sellingSumPane = FXMLLoader.load(ClassLoader.getSystemResource("application/SellSummarize.fxml"));
			AnchorPane settingPane = FXMLLoader.load(ClassLoader.getSystemResource("application/Setting.fxml"));
			AnchorPane cowPane = FXMLLoader.load(ClassLoader.getSystemResource("application/CowManager.fxml"));
			AnchorPane historyPane = FXMLLoader.load(ClassLoader.getSystemResource("application/SellHistory.fxml"));

			stackPane.getChildren().add(businessPane);
			stackPane.getChildren().add(cowPane);
			stackPane.getChildren().add(historyPane);
			stackPane.getChildren().add(sellingSumPane);
			stackPane.getChildren().add(settingPane);
			businessPane.setVisible(true);
			sellingSumPane.setVisible(false);
			settingPane.setVisible(false);
			cowPane.setVisible(false);
			historyPane.setVisible(false);

			stackPane.setVisible(true);
			stackPane.toFront();

			//Set Up Drawer to be anchor pane
			AnchorPane drawerPane = FXMLLoader.load(ClassLoader.getSystemResource("application/Drawer.fxml"));
			drawer.setSidePane(drawerPane);
			drawer.toBack();

			//Hamburger Button
			HamburgerBasicCloseTransition transition = new HamburgerBasicCloseTransition(hamburger);
			transition.setRate(-1);
			hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
				transition.setRate(transition.getRate()*(-1));
				transition.play();
				if(drawer.isShown()) {
					drawer.close();
					stackPane.toFront();
				}
				else {
					drawer.toFront();
					drawer.open();
				}
			}); 	

			//Set Up Event Handler
			for(Node node : drawerPane.getChildren()){
				if(node.getAccessibleText() != null) {
					node.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
												
						businessPane.setVisible(false);
						sellingSumPane.setVisible(false);
						settingPane.setVisible(false);
						cowPane.setVisible(false);
						historyPane.setVisible(false);
				
						drawer.toBack();
						switch (node.getAccessibleText()) {
						case "businessMenu":
							businessPane.setVisible(true);
							businessPane.toFront();
							transition.setRate(transition.getRate()*(-1));
							transition.play();
							drawer.close();
							break;
						case "cowMenu":
							cowPane.setVisible(true);
							cowPane.toFront();
							transition.setRate(transition.getRate()*(-1));
							transition.play();
							drawer.close();
							break;
						case "sellMenu":
							historyPane.setVisible(true);
							historyPane.toFront();
							transition.setRate(transition.getRate()*(-1));
							transition.play();
							drawer.close();
							break;
						case "setMenu":
							settingPane.setVisible(true);
							settingPane.toFront();
							transition.setRate(transition.getRate()*(-1));
							transition.play();
							drawer.close();
							break;
						case "sumMenu":
							sellingSumPane.setVisible(true);
							sellingSumPane.toFront();
							transition.setRate(transition.getRate()*(-1));
							transition.play();
							drawer.close();
							break;
						case "logMenu":
							System.exit(0);
							break;
						}
					});
				}
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
