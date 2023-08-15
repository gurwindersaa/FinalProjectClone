<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
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
                <h2 class="mb-4">Manage User Accounts</h2>
            </div>
        </div>
        <div class="container mt-4">
            <div class="form-group">
                <form>
                    <label for="users">Choose user</label>
                    <select name="userEmail" class="form-control" required>
                        <c:forEach items="${users}" var="user">
                            <option value="${user.email}">${user.email}</option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="action" value="userSelected">
                    <button type="submit" class="btn btn-primary mt-3 mb-3">Select</button>
                </form>
            </div>

            <c:if test="${user ne null}">
                <div class="mb-3">
                    <h2>  Edit Account for ${user.firstName} ${user.lastName} </h2>
                </div>
                <form action="ManageUsers" method="post">
                    <div class="mb-3">
                        <label for="email">Email Address:</label>
                        <input type="hidden" class="form-control" value="${user.email}" name="email">
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

                    <button type="submit" class="btn btn-primary mt-3">Update</button>
                </form>
                <div class="row">
                    <c:if test="${user.active eq true}">
                        <form action="ManageUsers" method="post" class="col-md-4">
                            <input type="hidden" class="form-control" value="${user.email}" name="email">
                            <input type="hidden" name="action" value="deactivate">
                            <button type="submit" class="btn btn-danger mt-3">Deactivate Account</button>
                        </form>
                    </c:if>
                    <c:if test="${user.active eq false}">
                        <form action="ManageUsers" method="post" class="col-md-4 ">
                            <input type="hidden" class="form-control" value="${user.email}" name="email">
                            <input type="hidden" name="action" value="activate">
                            <button type="submit" class="btn btn-primary mt-3">Activate Account</button>
                        </form>
                    </c:if>
                    <form action="ManageUsers" method="post" class="col-md-4">
                        <input type="hidden" class="form-control" value="${user.email}" name="email">
                        <input type="hidden" name="action" value="delete">
                        <button type="submit" class="btn btn-danger mt-3">Delete Account</button>
                    </form>
                </c:if>
                <div class="col-md-12">
                    <a href="admin" class="btn btn-secondary mt-2">Back</a>
                </div>
            </div>
            <div class="mt-3">${message}</div>
        </div>
    </body>
</html>
