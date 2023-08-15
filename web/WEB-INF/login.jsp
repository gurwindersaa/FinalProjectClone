<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory App - Login</title>
        <!-- Add Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="header bg-dark text-white py-3 text-center">
            <div class="container">
                <h1 class="mb-0">Inventory App</h1>
            </div>
        </div>
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-lg-6">
                    <h2 class="text-center">Login</h2>
                    <c:if test="${createAcc eq null}">
                        <form action="login" method="post" class="mt-4">
                            <div class="form-group">
                                <label for="email">Email:</label>
                                <input type="text" name="email" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label for="password">Password:</label>
                                <input type="password" name="password" class="form-control" required>
                            </div>
                            <button type="submit" class="btn btn-primary">Sign In</button>
                            <a href="<c:url value='login'>
                                   <c:param name='action' value='createUser' />
                               </c:url>" class="btn btn-link">Create Account</a>
                        </form>
                    </c:if>

                    ${message}

                    <c:if test="${createAcc ne null}">
                        <div class="mt-4">
                            <h2>Create Account</h2>
                            <form action="login" method="post">
                                <div class="form-group">
                                    <label for="owner">Email Address:</label>
                                    <input type="email" name="owner" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label for="password">Password:</label>
                                    <input type="password" name="password" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label for="fname">First Name:</label>
                                    <input type="text" name="fname" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label for="lname">Last Name:</label>
                                    <input type="text" name="lname" class="form-control" required>
                                </div>
                                <input type="hidden" name="action" value="create">
                                <button type="submit" class="btn btn-primary">Create Account</button>
                                <a href="login" class="btn btn-link">Back to Login</a>
                            </form>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>

        <!-- Add Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
