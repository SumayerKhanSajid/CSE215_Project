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

public class LoginAsDoctorFrame {
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

		ArrayList<Doctor> doctorlist = (ArrayList<Doctor>) FileLoader.loadDoctorsDataFromFile();
		String email = emailFieldBtn.getText();
		String pass = passwordFieldBtn.getText();
		System.out.println("clicked");
		boolean isfound = false;
		Doctor doctorsession = null;
		for (Doctor doctor : doctorlist) {
			if (doctor.getEmail().contentEquals(email) && doctor.getPassword().contentEquals(pass)) {
				isfound = true;
				doctorsession = doctor;

				break;
			}
		}

		if (isfound == true && doctorsession != null) {
			// DoctorDashboard dashboard = new DoctorDashboard(doctorsession);
			// dashboard.createFrame();
			System.out.println("found"+doctorsession.getEmail());
			DoctorDashboard doctorDashboard = new DoctorDashboard();

			DoctorDashboard.createFrame(doctorsession);


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

		//System.out.println(doctorsession.getEmail());

	}

	public static void createFrame() {
		Stage stage = new Stage();

		try {

			FXMLLoader loader = new FXMLLoader(LoginAsDoctorFrame.class.getResource("LoginAsDoctorFrame.fxml"));
			Parent root = loader.load();

			Scene scene = new Scene(root);

			stage.setTitle("Login as Doctor");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
