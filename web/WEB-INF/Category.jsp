<%-- 
    Document   : Category
    Created on : 8-Aug-2023, 1:06:50 PM
    Author     : gurwi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Category Selection</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body>
        <div class="header bg-dark text-white py-3">
            <div class="container d-flex justify-content-between align-items-center">
                <h1 class="mb-3">Manage Categories</h1>
            </div>
        </div>
        <div class="container mt-5">
            <div>
                <h2>Edit or Add Category</h2>
            </div>
            <form action="category" method="post" class="needs-validation" novalidate>
                <table class="table table-striped table-bordered">
                    <thead class="thead-dark">
                        <tr>
                            <th>Category</th>
                            <th>Edit</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${categories}" var="category">
                            <tr>
                                <td>${category.categoryName}</td>
                                <td>
                                    <a href="<c:url value='category'>
                                           <c:param name='action' value='edit' />
                                           <c:param name='categoryName' value='${category.categoryName}' />
                                       </c:url>" class="btn btn-primary btn-sm">Edit</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </form>
        </div>

        <c:if test="${category eq null}">
            <div class="container mt-2">
                <h2>Add Category</h2>
                <form action="category" method="post">
                    <div class="form-group">
                        <label for="categoryName">Name:</label>
                        <input type="text" name="categoryName" class="form-control" required>
                        <input type="hidden" name="action" value="create">
                        <button type="submit" class="btn btn-success mt-3">Create</button>
                    </div>
                </form>
            </div>
            <div class="container mt-4">
                <a href="admin" class="btn btn-secondary">Back</a>
            </div>
        </c:if>

        <c:if test="${category ne null}">
            <div class="container mt-4">
                <h2>Edit Category</h2>
                <form action="category" method="post">
                    <div class="form-group">
                        <label for="categoryName">Name:</label>
                        <input type="text" name="categoryName" class="form-control" value="${category.categoryName}" required>
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="categoryId" value="${category.categoryId}">
                        <button type="submit" class="btn btn-success mt-3">Update</button>
                    </div>
                </form>
            </div>
            <div class="container mt-4">
                <a href="category" class="btn btn-secondary">Cancel Edit</a>
            </div>
        </c:if>
    </body>
</html>
