package application;

public class Doctor {
    
	private int id;            // Unique identifier for the doctor
    private String name;       // Doctor's name
    private String specialty;  // Doctor's medical specialty
    private String qualifications; // Qualifications and degrees
    private String contactNumber;  // Contact number
    private String email;  
    private String password;// Doctor's email address
	public Doctor(int id, String name, String specialty, String qualifications, String contactNumber, String email,
			String password) {
		super();
		this.id = id;
		this.name = name;
		this.specialty = specialty;
		this.qualifications = qualifications;
		this.contactNumber = contactNumber;
		this.email = email;
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getSpecialty() {
		return specialty;
	}
	public String getQualifications() {
		return qualifications;
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
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
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
	@Override
	public String toString() {
		return "Doctor id : " + id + " Specialty : " + specialty + "]";
	}
	

    // Constructors, getters, and setters
}
