package application;

import java.util.ArrayList;

public class test {

	public static void main(String[] args) {
		
		ArrayList<Doctor> patienList=(ArrayList<Doctor>) FileLoader.loadDoctorsDataFromFile();
		for(Doctor doctor :patienList)
		{
			System.out.println(doctor);
		}

		

	}

}
