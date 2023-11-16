package application;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DoctorDashboard implements EventHandler<ActionEvent>, Initializable {

	private static Doctor currentDoctorSession;
	private ArrayList<Patient> patientList = (ArrayList<Patient>) FileLoader.loadPatientsDataFromFile();
	private ArrayList<Appointment> appointmentList = (ArrayList<Appointment>) FileLoader.loadAppointmentDataFromFile();

	@FXML
	private TextField appointmentIdField;

	@FXML
	private TextField appointmentPendingField;

	@FXML
	private TextField appointmentRejectedField;

	@FXML
	private TextField appointmentTime;

	@FXML
	private TextField doctorNameField;

	@FXML
	private TextField doctorappointedEmailField;

	@FXML
	private Button logoutBtn;

	@FXML
	private TextField patientAgeField;

	@FXML
	private TextField patientContactField;

	@FXML
	private TextField patientEmailField;

	@FXML
	private TextField patientGenderField;

	@FXML
	private TextField patientIdField;

	@FXML
	private TextField patientNameField;

	@FXML
	private TextField patientappointedEmailField;

	@FXML
	private Button profileBtn;

	@FXML
	private ListView<Appointment> appointmentListView;
	@FXML
	private ListView<Patient> patientListView;

	@FXML
	void viewProfile(ActionEvent event) {
	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    alert.setTitle("Doctor Information");
	    alert.setHeaderText(null); // No header text
	    alert.setContentText("Name: " + currentDoctorSession.getName() + "\n"
	            + "Specialty: " + currentDoctorSession.getSpecialty() + "\n"
	            + "Qualifications: " + currentDoctorSession.getQualifications() + "\n"
	            + "Contact Number: " + currentDoctorSession.getContactNumber() + "\n"
	            + "Email: " + currentDoctorSession.getEmail());

	    // Show the dialog
	    alert.showAndWait();
	}


	public DoctorDashboard(Doctor doctor) {
		currentDoctorSession = doctor;

	}

	public DoctorDashboard() {
		// TODO Auto-generated constructor stub
	}

	public void setCurrentDoctorSession(Doctor doctorsession) {
		// TODO Auto-generated method stub
		currentDoctorSession = doctorsession;
		doctorNameField.setText(currentDoctorSession.getName());
		doctorNameField.setEditable(false);
		loadDynamicData();

	}

	private void loadDynamicData() {
		ArrayList<Patient> filteredPatientList = new ArrayList<Patient>();

		for (Appointment appointment : appointmentList) {
			System.out.println(appointment.getDoctortEmail());
			if (appointment.getDoctortEmail().contentEquals(currentDoctorSession.getEmail())) {
				
				Patient patient=Patient.getPatientObjectByEmail(patientList, appointment.getPatientEmail());
				System.out.println(patient.getEmail()+patient.getName());
				filteredPatientList.add(patient);
			}
		}

		patientListView.getItems().addAll(filteredPatientList);

		patientListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				// A new patient is selected. Update the text fields with patient details.
				patientIdField.setText((newValue.getId()));
				patientNameField.setText(newValue.getName());
				patientAgeField.setText(Integer.toString(newValue.getAge()));
				patientContactField.setText(newValue.getContactNumber());
				patientEmailField.setText(newValue.getEmail());
				patientGenderField.setText(newValue.getGender());
			} else {
				// No patient is selected. Clear the text fields or perform other actions.
				patientIdField.clear();
				patientNameField.clear();
				patientAgeField.clear();
				patientContactField.clear();
				patientEmailField.clear();
			}
		});

		appointmentListView.getItems().addAll(appointmentList);

		appointmentListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				// A new patient is selected. Update the text fields with patient details.
				appointmentIdField.setText(Integer.toString(newValue.getAppointmentID()));
				doctorappointedEmailField.setText(newValue.getDoctortEmail());
				patientappointedEmailField.setText(newValue.getPatientEmail());
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

				// Format the appointment date and time
				String formattedTime = newValue.getAppointmentDateTime().format(formatter);
				appointmentTime.setText(formattedTime);
				appointmentPendingField.setText(newValue.isAppointmentPending() ? "Yes" : "No");
				appointmentRejectedField.setText(newValue.isAppointmentRejected() ? "Yes" : "No");

			} else {
				// No patient is selected. Clear the text fields or perform other actions.

				appointmentPendingField.clear();
				appointmentRejectedField.clear();
			}
		});

	}
    @FXML
    void logout(ActionEvent event) {
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

	public static void createFrame(Doctor doctorSession) {
		Stage stage = new Stage();

		try {
			FXMLLoader loader = new FXMLLoader(LoginAsDoctorFrame.class.getResource("DoctorDashboard.fxml"));
			Parent root = loader.load();
			// After loading the FXML, get the controller
			DoctorDashboard doctorDashboardController = loader.getController();

			// Set the currentDoctorSession
			doctorDashboardController.setCurrentDoctorSession(doctorSession);

			Scene scene = new Scene(root);

			stage.setTitle("Login as Doctor");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	private void cancelAppointment() {
		// Implement the cancellation logic
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		loadPatientRecords();

	}

	private void loadPatientRecords() {

		// TODO Auto-generated method stub

	}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
