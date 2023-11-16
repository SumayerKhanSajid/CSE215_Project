package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

public class SignUpPatientFrame {
	@FXML
	private TextField ageField;

	@FXML
	private TextField contactnumberField;

	@FXML
	private TextField emailField;

	@FXML
	private TextField genderField;

	@FXML
	private TextField nameField;

	@FXML
	private TextField passwordField;

	@FXML
	void cancel(ActionEvent event) {

		// Close the current frame (Stage)
		Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		currentStage.close();

		// Open the main frame (Main.java)
		Platform.runLater(() -> {
			Main main = new Main();
			Stage primaryStage = new Stage();
			try {
				main.start(primaryStage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	@FXML
	void signup(ActionEvent event) {
		// Fetch data from the fields
		String name = nameField.getText();
		String email = emailField.getText();
		String password = passwordField.getText();
		String gender = genderField.getText();
		int age = Integer.parseInt(ageField.getText());
		String contactNumber = contactnumberField.getText();
		
		ArrayList<Patient>patientlist=(ArrayList<Patient>) FileLoader.loadPatientsDataFromFile();


		Patient newPatient = new Patient((patientlist.size()+1)+"", name, age, gender, contactNumber, email, password);
		patientlist.add(newPatient);
		
		FileLoader.savePatientsDataToFile(patientlist);
	    showSuccessPopup();
	    
	    LoginAsPatientFrame.createFrame();
	 // Close the current frame (Stage) if possible
	 		if (event.getSource() instanceof Node) {
	 			Node sourceNode = (Node) event.getSource();
	 			Scene scene = sourceNode.getScene();
	 			if (scene != null) {
	 				Stage currentStage = (Stage) scene.getWindow();
	 				if (currentStage != null) {
	 					currentStage.close();
	 				}
	 			}
	 		}


	}
	public static void createFrame() {
		Stage stage = new Stage();

		try {

			FXMLLoader loader = new FXMLLoader(LoginAsDoctorFrame.class.getResource("signupFrame.fxml"));
			Parent root = loader.load();

			Scene scene = new Scene(root);

			stage.setTitle("Login as Patient");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	private void showSuccessPopup() {
	    Alert alert = new Alert(AlertType.INFORMATION);
	    alert.setTitle("Signup Successful");
	    alert.setHeaderText(null);
	    alert.setContentText("Signup successful! Thank you for registering.");
	    alert.showAndWait();
	}
}
