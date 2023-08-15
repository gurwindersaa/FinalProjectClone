<%-- 
    Document   : ManageAccount
    Created on : 3-Aug-2023, 4:00:10 PM
    Author     : gurwi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Account</title>
        <!-- Add Bootstrap CSS link -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body>
          <div class="header bg-dark text-white py-3">
            <div class="container d-flex justify-content-between align-items-center">
                           <h2 class="mb-3">Manage Account</h2>
            </div>
        </div>
        <div class="container mt-4">
            <c:if test="${user ne null}">
                <form action="Accounts" method="post">
                    <div class="mb-3">
                        <label for="email">Email Address:</label>
                        <p class="form-control-static">${user.email}</p>
                    </div>
                    <div class="mb-3">
                        <label for="password">Password:</label>
                        <input type="password" class="form-control" name="password" required>
                    </div>
                    <div class="mb-3">
                        <label for="fname">First Name:</label>
                        <input type="text" class="form-control" name="fname" value="${user.firstName}">
                    </div>
                    <div class="mb-3">
                        <label for="lname">Last Name:</label>
                        <input type="text" class="form-control" name="lname" value="${user.lastName}">
                    </div>
                    <input type="hidden" name="action" value="update">
                    <button type="submit" class="btn btn-primary">Update</button>
                </form>
                <form action="Accounts" method="post" class="mt-3">
                    <input type="hidden" name="action" value="deactivate">
                    <button type="submit" class="btn btn-danger">Deactivate</button>
                </form>
            </c:if>
            <a href="Inventory" class="btn btn-secondary mt-2">Back</a>
            <div class="mt-3">${message}</div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
