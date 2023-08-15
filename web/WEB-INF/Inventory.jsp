<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory Home</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
        <link rel="stylesheet" href="css/StyleSheet.css">
    </head>
    <body>
        <div class="header bg-dark text-white py-3">
            <div class="container d-flex justify-content-between align-items-center">
                <h2 class="m-0">Inventory Home</h2>
                <div>
                    <a href="Accounts" class="btn btn-light mx-2">Manage Account</a>
                    <a href="login" class="btn btn-light">Logout</a>
                </div>
            </div>
        </div>
        <div class="container mt-4">
            <h3>Welcome ${user.firstName}</h3>

            <!-- User list table -->
            <table class="table table-striped table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th>Category</th>
                        <th>Item Name</th>
                        <th>Price</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${items}" var="item">
                        <tr>
                            <td>${item.category.categoryName}</td>
                            <td>${item.itemName}</td>
                            <td>${item.price}</td>
                            <td>
                                <a href="<c:url value='Inventory'>
                                       <c:param name='action' value='edit' />
                                       <c:param name='itemId' value='${item.itemId}' />
                                   </c:url>" class="btn btn-primary btn-sm">Edit</a>
                            </td>
                            <td>
                                <a href="Inventory?action=delete&itemId=${item.itemId}" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#deleteItemModal${item.itemId}">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <c:if test="${selectedItem eq null}">
                <form action="Inventory" method="post">
                    <h2>Add Item</h2>
                    <div class="form-group mt-3">
                        <label for="itemName">Name:</label>
                        <input type="text" name="itemName" class="form-control" required>
                    </div>
                    <div class="form-group mt-3">
                        <label for="category">Category:</label>
                        <select name="category" class="form-control " required>
                            <c:forEach items="${categories}" var="category">
                                <option value="${category.categoryName}">${category.categoryName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group mt-3">
                        <label for="price">Price:</label>
                        <input type="number" name="price" class="form-control" required>
                    </div>
                    <input type="hidden" name="action" value="create">
                    <button type="submit" class="btn btn-success mt-3 mb-3">Create</button>
                </form>
            </c:if>
            <div>
                <form action="Inventory" method="post">                    
                    <c:if test="${selectedItem ne null}">
                        <h2>Edit item</h2>
                        <input type="hidden" name="itemId" value="${selectedItem.itemId}">
                        <p class="mt-3">Category: ${selectedItem.category.categoryName}</p>
                        <div class="form-group mt-3">
                            <label for="itemName">Name:</label>
                            <input type="text" name="itemName" value="${selectedItem.itemName}" class="form-control">
                        </div>
                        <div class="form-group mt-3">
                            <label for="price">Price:</label>
                            <input type="number" name="price" value="${selectedItem.price}" class="form-control">
                        </div>
                        <input type="hidden" name="action" value="update">
                        <div class="mt-3">

                            <button type="submit" class="btn btn-primary">Update</button>

                            <div >
                                <a href="admin" class="btn btn-secondary mt-3 mb-3">Cancel Edit</a>
                            </div>
                        </div>
                    </c:if>
                </form>
            </div>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
