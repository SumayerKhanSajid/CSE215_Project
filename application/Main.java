package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		FileLoader.makeFileUsingSampleData();

		Parent root = FXMLLoader.load(getClass().getResource("HomePageFrame.fxml"));

		Scene scene = new Scene(root, 430, 345);

		primaryStage.setTitle("Login Portal");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
