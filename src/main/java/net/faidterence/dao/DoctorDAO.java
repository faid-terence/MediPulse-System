package net.faidterence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	    
	    
	    public void displayDoctor(Long id) {
	        // Step 1: Establishing a Connection
	        try (Connection connection = connectToDB();
	             // Step 2: Create a statement using connection object
	             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DOCTOR_BY_ID);) {
	            preparedStatement.setLong(1, id);
	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            ResultSet rs = preparedStatement.executeQuery();

	            // Step 4: Process the ResultSet object.
	            while (rs.next()) {
	                Long doctorId = rs.getLong("id");
	                String firstName = rs.getString("first_name");
	                String lastName = rs.getString("last_name");
	                String email = rs.getString("email");
	                String specialization = rs.getString("specialization");
	                String phoneNumber = rs.getString("phone_number");
	                String address = rs.getString("address");
	                String licenseNumber = rs.getString("license_number");

	                // Assuming Doctor class has a constructor like: Doctor(Long id, String firstName, String lastName, String email, String specialization, String phoneNumber, String address, String licenseNumber)
	                Doctor doctor = new Doctor(firstName, lastName, email, specialization, phoneNumber, address, licenseNumber);

	                // Displaying the information
	                System.out.println("Doctor ID: " + doctor.getId());
	                System.out.println("First Name: " + doctor.getFirstName());
	                System.out.println("Last Name: " + doctor.getLastName());
	                System.out.println("Email: " + doctor.getEmail());
	                System.out.println("Specialization: " + doctor.getSpecialization());
	                System.out.println("Phone Number: " + doctor.getPhoneNumber());
	                System.out.println("Address: " + doctor.getAddress());
	                System.out.println("License Number: " + doctor.getLicenseNumber());
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public List<Doctor> selectAllDoctors() {
	        // using try-with-resources to avoid closing resources (boilerplate code)
	        List<Doctor> doctors = new ArrayList<>();

	        // Step 1: Establishing a Connection
	        try (Connection connection = connectToDB();
	             // Step 2: Create a statement using connection object
	             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DOCTORS);) {
	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            ResultSet rs = preparedStatement.executeQuery();

	            // Step 4: Process the ResultSet object.
	            while (rs.next()) {
	                Long id = rs.getLong("id");
	                String firstName = rs.getString("firstName");
	                String lastName = rs.getString("lastName");
	                String specialization = rs.getString("specialization");
	                String email = rs.getString("email");
	                String phoneNumber = rs.getString("phoneNumber");
	                String address = rs.getString("address");
	                String licenseNumber = rs.getString("licenseNumber");


	                doctors.add(new Doctor(id, firstName, lastName, specialization, email, phoneNumber, address, licenseNumber));

	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return doctors;
	    }
	    
	    public boolean deleteDoctor(int id) throws SQLException {
	        boolean rowDeleted;
	        try (Connection connection = connectToDB();
	             PreparedStatement statement = connection.prepareStatement(DELETE_DOCTOR_SQL);) {
	            statement.setInt(1, id);
	            rowDeleted = statement.executeUpdate() > 0;
	        }
	        return rowDeleted;
	    }
	    
	    
	    public boolean updateDoctor(Doctor doctor) throws SQLException {
	        boolean rowUpdated;
	        try (Connection connection = connectToDB();
	             PreparedStatement statement = connection.prepareStatement(UPDATE_DOCTOR_SQL )) {

	            statement.setString(1, doctor.getFirstName());
	            statement.setString(2, doctor.getLastName());
	            statement.setString(3, doctor.getSpecialization());
	            statement.setString(4, doctor.getEmail());
	            statement.setString(5, doctor.getPhoneNumber());
	            statement.setString(6, doctor.getAddress());
	            statement.setString(7, doctor.getLicenseNumber());
	            statement.setLong(8, doctor.getId());

	            rowUpdated = statement.executeUpdate() > 0;
	        }
	        return rowUpdated;
	    }

	    
	    
	    private void printSQLException(SQLException ex) {
	        for (Throwable e: ex) {
	            if (e instanceof SQLException) {
	                e.printStackTrace(System.err);
	                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                System.err.println("Message: " + e.getMessage());
	                Throwable t = ex.getCause();
	                while (t != null) {
	                    System.out.println("Cause: " + t);
	                    t = t.getCause();
	                }
	            }
	        }
	    }


}
