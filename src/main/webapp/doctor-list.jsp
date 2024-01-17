<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>

<head>
    <title>Doctor Management Application</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
    <style>
        body {
            background-color: #f0f0f0;
        }

        header {
            background-color: #dc3545;
            padding: 10px 0;
        }

        .navbar-brand {
            color: #fff;
            font-size: 1.5rem;
        }

        .navbar-nav .nav-link {
            color: #fff;
        }

        .container {
            margin-top: 20px;
        }

        .btn-add {
            background-color: #28a745;
            color: #fff;
        }

        .table th, .table td {
            text-align: center;
        }

        footer {
            background-color: #343a40;
            color: #fff;
            padding: 10px 0;
            text-align: center;
            position: fixed;
            bottom: 0;
            width: 100%;
        }

        @media (max-width: 767px) {
            .btn-add {
                width: 100%;
                margin-bottom: 10px;
            }
        }
    </style>
</head>

<body>

    <header>
        <nav class="navbar navbar-expand-md navbar-dark" style="background-color: #dc3545;">
            <div>
                <a href="https://www.javaguides.net" class="navbar-brand"> Doctor Management System </a>
            </div>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/list">Doctors</a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <br>

    <div class="container">
        <h3 class="text-center">List of Doctors</h3>
        <hr>
        <div class="container text-left">
            <a href="<%=request.getContextPath()%>/new" class="btn btn-success btn-add">Add New Doctor</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Specialization</th>
                    <th>Email</th>
                    <th>Phone Number</th>
                    <th>Address</th>
                    <th>License Number</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="doctor" items="${listDoctors}">
                    <tr>
                        <td><c:out value="${doctor.id}" /></td>
                        <td><c:out value="${doctor.firstName}" /></td>
                        <td><c:out value="${doctor.lastName}" /></td>
                        <td><c:out value="${doctor.specialization}" /></td>
                        <td><c:out value="${doctor.email}" /></td>
                        <td><c:out value="${doctor.phoneNumber}" /></td>
                        <td><c:out value="${doctor.address}" /></td>
                        <td><c:out value="${doctor.licenseNumber}" /></td>
                        <td>
                            <a href="edit?id=<c:out value='${doctor.id}' />" class="btn btn-primary btn-sm">Edit</a>
                            <a href="delete?id=<c:out value='${doctor.id}' />" class="btn btn-danger btn-sm">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <footer>
        Copyright &copy; 2023 Terence Faid JABO. All rights reserved.
    </footer>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>

</html>
