package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Main class use to run Application
 * @author Tharit Pongsaneh
 *
 */
public class Main extends Application {
	
	/**
	 * Load Login.fxml
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(ClassLoader.getSystemResource("application/Login.fxml"));
			Scene loginScene = new Scene(root);
			loginScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(loginScene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * launch the program
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
