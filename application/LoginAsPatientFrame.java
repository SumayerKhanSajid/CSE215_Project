package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginAsPatientFrame {

	@FXML
	private TextField emailFieldBtn;

	@FXML
	private Button goBackBtn;

	@FXML
	private Button loginPatientBtn;

	@FXML
	private PasswordField passwordFieldBtn;

	@FXML
	void goBack(ActionEvent event) {

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
	void login(ActionEvent event) {

		ArrayList<Patient> patientList = (ArrayList<Patient>) FileLoader.loadPatientsDataFromFile();
		String email = emailFieldBtn.getText();
		String pass = passwordFieldBtn.getText();
		boolean isfound = false;
		Patient patientSession = null;
		for (Patient patient : patientList) {
			if (patient.getEmail().contentEquals(email) && patient.getPassword().contentEquals(pass)) {
				isfound = true;
				patientSession = patient;

				break;
			}
		}

		if (isfound == true && patientSession != null) {
			// DoctorDashboard dashboard = new DoctorDashboard(doctorsession);
			// dashboard.createFrame();
			System.out.println("found" + patientSession.getEmail());
			// PatientDashboard patientDashboard = new PatientDashboard();

			PatientDashboard.createFrame(patientSession);

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
		} else {
			System.out.println("patient not found");
		}

	}

	public static void createFrame() {
		Stage stage = new Stage();

		try {

			FXMLLoader loader = new FXMLLoader(LoginAsDoctorFrame.class.getResource("LoginAsPatientFrame.fxml"));
			Parent root = loader.load();

			Scene scene = new Scene(root);

			stage.setTitle("Login as Patient");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
