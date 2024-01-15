package net.faidterence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import net.faidterence.model.Doctor;

public class DoctorDAO {
	
	  private String jdbcURL = "jdbc:postgresql://localhost:5432/postgres";
	    private String jdbcUsername = "postgres";
	    private String jdbcPassword = "thestunna420";
	    
	    private static final String INSERT_DOCTOR_SQL = "INSERT INTO doctors (first_name, last_name, specialization, email, phone_number, address, license_number) VALUES (?, ?, ?, ?, ?, ?, ?)";

	    private static final String SELECT_DOCTOR_BY_ID = "SELECT id, first_name, last_name, specialization, email, phone_number, address, license_number FROM doctors WHERE id = ?";
	    private static final String SELECT_ALL_DOCTORS = "SELECT * FROM doctors";
	    private static final String DELETE_DOCTOR_SQL = "DELETE FROM doctors WHERE id = ?";
	    private static final String UPDATE_DOCTOR_SQL = "UPDATE doctors SET first_name = ?, last_name = ?, specialization = ?, email = ?, phone_number = ?, address = ?, license_number = ? WHERE id = ?";

	    
	    
	    
	    public DoctorDAO() {}
	    
	    protected Connection connectToDB() {
	        Connection connection = null;
	        try {
	            Class.forName("org.postgresql.Driver");
	            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        return connection;
	    }
	    
	    public void insertDoctor(Doctor doctor) throws SQLException {
	        System.out.println(INSERT_DOCTOR_SQL);
	        // try-with-resource statement will auto close the connection.
	        try (Connection connection = connectToDB(); 
	             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DOCTOR_SQL)) {
	            preparedStatement.setString(1, doctor.getFirstName());
	            preparedStatement.setString(2, doctor.getLastName());
	            preparedStatement.setString(3, doctor.getSpecialization());
	            preparedStatement.setString(4, doctor.getEmail());
	            preparedStatement.setString(5, doctor.getPhoneNumber());
	            preparedStatement.setString(6, doctor.getAddress());
	            preparedStatement.setString(7, doctor.getLicenseNumber());
	            
	            System.out.println(preparedStatement);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	           e.printStackTrace();
	        }
	    }

}
