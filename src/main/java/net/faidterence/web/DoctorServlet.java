package net.faidterence.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.faidterence.dao.DoctorDAO;
import net.faidterence.model.Doctor;

/**
 * Servlet implementation class DoctorServlet
 */
@WebServlet("/")
public class DoctorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DoctorDAO doctorDAO;

    public void init() {
        doctorDAO = new DoctorDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                try {
                    insertDoctor(request, response);
                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                }
                break;
//            case "/delete":
//                try {
//                    deleteDoctor(request, response);
//                } catch (SQLException | IOException e) {
//                    e.printStackTrace();
//                }
//                break;
            case "/edit":
                try {
                    showEditForm(request, response);
                } catch (SQLException | ServletException | IOException e) {
                    e.printStackTrace();
                }
                break;
            case "/update":
                try {
                    updateDoctor(request, response);
                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                try {
                    listDoctors(request, response);
                } catch (SQLException | IOException | ServletException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("doctor-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertDoctor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String specialization = request.getParameter("specialization");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String licenseNumber = request.getParameter("licenseNumber");

        Doctor newDoctor = new Doctor(firstName, lastName, specialization, email, phoneNumber, address, licenseNumber);
        doctorDAO.insertDoctor(newDoctor);
        response.sendRedirect("list");
    }

//    private void deleteDoctor(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, IOException {
//        Long id = Long.parseLong(request.getParameter("id"));
//        doctorDAO.deleteDoctor(id);
//        response.sendRedirect("list");
//    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Doctor existingDoctor = doctorDAO.selectDoctor(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("doctor-form.jsp");
        request.setAttribute("doctor", existingDoctor);
        dispatcher.forward(request, response);
    }

    private void updateDoctor(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String specialization = request.getParameter("specialization");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String licenseNumber = request.getParameter("licenseNumber");

        Doctor doctor = new Doctor(id, firstName, lastName, specialization, email, phoneNumber, address, licenseNumber);
        doctorDAO.updateDoctor(doctor);
        response.sendRedirect("list");
    }

    private void listDoctors(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Doctor> listDoctors = doctorDAO.selectAllDoctors();
        request.setAttribute("listDoctors", listDoctors);
        RequestDispatcher dispatcher = request.getRequestDispatcher("doctor-list.jsp");
        dispatcher.forward(request, response);
    }
}
