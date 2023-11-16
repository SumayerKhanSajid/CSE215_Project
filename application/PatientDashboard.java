package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PatientDashboard implements EventHandler<ActionEvent>, Initializable {
	private static Patient currentPatientSession;
	private ArrayList<Doctor> doctorlist = (ArrayList<Doctor>) FileLoader.loadDoctorsDataFromFile();
	private ArrayList<Appointment> appointmentList = (ArrayList<Appointment>) FileLoader.loadAppointmentDataFromFile();

	@FXML
	private DatePicker datepickedField;

	@FXML
	private TextField docEmailField;

	@FXML
	private TextField docNameField;

	@FXML
	private TextField docQualificationField;

	@FXML
	private TextField docSpecialityField;

	@FXML
	private TextField docnumberField;

	@FXML
	private Button logoutBtn;

	@FXML
	private TextField patientAgeField;

	@FXML
	private TextField patientContactField;

	@FXML
	private TextField patientGenderField;

	@FXML
	private TextField patientIdEmailField;

	@FXML
	private TextField patientIdField;

	@FXML
	private TextField patientnameField;

	@FXML
	private ListView<Doctor> requestAppointmentListview;

	@FXML
	private Button submitBtn;
	@FXML
	private Label welcomeLabel;

	@FXML
	private ListView<String> upcomingAppointmentListview;

	String currentEmail;

	public void setCurrentPatientSession(Patient patientSession) {

		// TODO Auto-generated method stub
		currentPatientSession = patientSession;
		patientnameField.setText(currentPatientSession.getName());
		patientIdField.setText(currentPatientSession.getId());
		patientAgeField.setText(currentPatientSession.getAge() + "");
		patientGenderField.setText(currentPatientSession.getGender() + "");
		patientContactField.setText(currentPatientSession.getContactNumber() + "");
		patientIdEmailField.setText(currentPatientSession.getEmail() + "");
		patientnameField.setText(currentPatientSession.getName() + "");
		welcomeLabel.setText("Welcome " + currentPatientSession.getName());
		currentEmail = patientSession.getEmail();
		loadDynamicData();
		System.out.println("currrent email :" + currentEmail);

	}

	public static void createFrame(Patient patientSession) {
		Stage stage = new Stage();

		try {

			FXMLLoader loader = new FXMLLoader(LoginAsPatientFrame.class.getResource("patientDashboard.fxml"));
			Parent root = loader.load();

			// Set the controller programmatically
			PatientDashboard patientDashboardController = loader.getController();
			// loader.setController(patientDashboardController);

			patientDashboardController.setCurrentPatientSession(patientSession);

			Scene scene = new Scene(root);

			stage.setTitle("Login as Patient");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	@FXML
	void submit(ActionEvent event) {
		String docemail = docEmailField.getText();
		ArrayList<Appointment> appointmentList = (ArrayList<Appointment>) FileLoader.loadAppointmentDataFromFile();

		// Assuming datepickedField is a DatePicker
		LocalDate selectedDate = datepickedField.getValue();
		String date = (selectedDate != null) ? selectedDate.toString() : null;

		int appointmentID = appointmentList.size() + 1;
		Appointment appointment = new Appointment(appointmentID, currentEmail, docemail, selectedDate, true, false);
		appointmentList.add(appointment);
		FileLoader.saveAppointmentDataToFile(appointmentList);
		showSuccessPopup("Appointment Added", "Appointment added successfully!");

		/* add popup appointment Added successsfully */

	}

	private void showSuccessPopup(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	public void loadDynamicData() {
		// Load dynamic data into UI components
		int appointmentCounter = 0;
		String upcomingAppointment[] = new String[appointmentList.size()];

		for (Appointment appointment : appointmentList) {

			if (appointment.getPatientEmail().contentEquals(currentEmail)) {
				upcomingAppointment[appointmentCounter] = "Appointment: (" + appointment.getAppointmentID() + ") "
						+ " With [" + appointment.getDoctortEmail() + "] Date : " + appointment.getAppointmentDateTime();
				appointmentCounter++;
			}

		}

		if (appointmentCounter <= 0) {
			upcomingAppointmentListview.getItems().addAll("No appointment");

		} else {

			upcomingAppointmentListview.getItems().addAll(upcomingAppointment);
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		int i;

		String doctorArr[] = new String[doctorlist.size()];

		i = 0;
		for (Doctor doctor : doctorlist) {
			doctorArr[i] = doctor.getId() + "";
			System.out.println(doctorArr[i]);
			i++;

		}

		requestAppointmentListview.getItems().addAll(doctorlist);

		requestAppointmentListview.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> {
					if (newValue != null) {
						docNameField.setText((newValue.getName()));
						docQualificationField.setText(newValue.getQualifications());
						docEmailField.setText(newValue.getEmail());
						docnumberField.setText(newValue.getContactNumber());
						docSpecialityField.setText(newValue.getSpecialty());

					}
				});

	}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
