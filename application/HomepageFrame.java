package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomepageFrame {
	@FXML
	private Button loginDoctorBtn;

	@FXML
	private Button loginPatientBtn;

	@FXML
	private Button signupBtn;

	@FXML
	void loginAsDoctor(ActionEvent event) {
		// Open the "LoginAsDoctorFrame"
		LoginAsDoctorFrame.createFrame();

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

	@FXML
	void loginAsPatient(ActionEvent event) {
		// Open the "LoginAsDoctorFrame"
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

	@FXML
	void signupAsPatient(ActionEvent event) {
		SignUpPatientFrame.createFrame();
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

	private void showAlert(String title, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();
	}
}
