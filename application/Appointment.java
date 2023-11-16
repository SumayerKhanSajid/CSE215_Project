package application;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Appointment {
	private int appointmentID; // Unique identifier for the appointment
	private String patientEmail; // Patient who made the appointment
	private String doctortEmail; // Patient who made the appointment
	private LocalDate appointmentDateTime; // Date and time of the appointment
	private boolean isAppointmentPending;
	private boolean isAppointmentRejected;

	
	public Appointment(int appointmentID, String patientEmail, String doctortEmail, LocalDate appointmentDateTime,
			boolean isAppointmentPending, boolean isAppointmentRejected) {
		super();
		this.appointmentID = appointmentID;
		this.patientEmail = patientEmail;
		this.doctortEmail = doctortEmail;
		this.appointmentDateTime = appointmentDateTime;
		this.isAppointmentPending = isAppointmentPending;
		this.isAppointmentRejected = isAppointmentRejected;
	}


	public int getAppointmentID() {
		return appointmentID;
	}


	public String getPatientEmail() {
		return patientEmail;
	}


	public String getDoctortEmail() {
		return doctortEmail;
	}


	public LocalDate getAppointmentDateTime() {
		return appointmentDateTime;
	}


	public boolean isAppointmentPending() {
		return isAppointmentPending;
	}


	public boolean isAppointmentRejected() {
		return isAppointmentRejected;
	}


	public void setAppointmentID(int appointmentID) {
		this.appointmentID = appointmentID;
	}


	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}


	public void setDoctortEmail(String doctortEmail) {
		this.doctortEmail = doctortEmail;
	}


	public void setAppointmentDateTime(LocalDate appointmentDateTime) {
		this.appointmentDateTime = appointmentDateTime;
	}


	public void setAppointmentPending(boolean isAppointmentPending) {
		this.isAppointmentPending = isAppointmentPending;
	}


	public void setAppointmentRejected(boolean isAppointmentRejected) {
		this.isAppointmentRejected = isAppointmentRejected;
	}


	@Override
	public String toString() {
		return "ID :" + appointmentID;
	}

	// Constructors, getters, and setters
}
