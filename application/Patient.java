package application;

import java.util.List;

public class Patient {
	private String id; // Unique identifier for the patient
	private String name; // Patient's name
	private int age; // Patient's age
	private String gender; // Patient's gender
	private String contactNumber; // Contact number
	private String email;
	private String password;// Patient's email address

	public Patient(String id, String name, int age, String gender, String contactNumber, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.contactNumber = contactNumber;
		this.email = email;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public static Patient getPatientObjectByEmail(List<Patient> patients, String email) {
	    for (Patient patient : patients) {
	        if (patient.getEmail().equals(email)) {
	            return patient;
	        }
	    }
	    return null; // Return null if no patient with the specified email is found
	}


	@Override
	public String toString() {
		return "Patient ID : " + id;
	}
	
	

	// Constructors, getters, and setters
}
