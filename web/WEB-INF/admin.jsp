<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory home</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
        <link rel="stylesheet" href="css/StyleSheet.css">
    </head>
    <body>
        <div class="header bg-dark text-white py-3">
            <div class="container d-flex justify-content-between align-items-center">
                <h2 class="m-0">Inventory Home</h2>
                <div>           
                    <a href="category" class="btn btn-light mx-2">Manage Categories</a>
                    <a href="ManageUsers" class="btn btn-light mx-2">Manage Account</a>
                    <a href="login" class="btn btn-light">Logout</a>
                </div>
            </div>
        </div>
        <div class="container mt-4">
            <h2>Admin page</h2>
            <div class="form-group">
                <form>                    
                    <label for="users">Choose user to manage inventory</label>
                    <select name="userEmail" class="form-control" required>
                        <c:forEach items="${users}" var="user">
                            <option value="${user.email}">${user.email}</option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="action" value="userSelected">
                    <button type="submit" class="btn btn-primary  mt-3 mb-3">Select</button>
                </form>
            </div>

            <c:if test="${items ne null}">
            <div>
                 <h3>List of items for ${user.firstName}  ${user.lastName} </h3>
            </div>
                <form action="admin" method="post">                   
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
                                        <a href="<c:url value='admin'>
                                               <c:param name='action' value='edit' />
                                               <c:param name='itemId' value='${item.itemId}' />
                                           </c:url>" class="btn btn-primary btn-sm">Edit</a>
                                    </td>
                                    <td>
                                        <a href="<c:url value='admin'>
                                               <c:param name='action' value='delete' />
                                               <c:param name='itemId' value='${item.itemId}' />
                                           </c:url>" class="btn btn-danger btn-sm">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </form>
                <input type="hidden" name="action" value="added">
                <div>
                    <form action="admin" method="post">
                        <c:if test="${selectedItem eq null}">
                            <h2>Add Item</h2>
                            <div class="form-group">
                                <label for="itemName">Name:</label>
                                <input type="text" name="itemName" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label for="category">Category:</label>
                                <select name="category" class="form-control" required>
                                    <c:forEach items="${categories}" var="category">
                                        <option value="${category.categoryName}">${category.categoryName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="price">Price:</label>
                                <input type="number" name="price" class="form-control" required>
                            </div>
                            <div>
                            <input type="hidden" name="action" value="create">
                            <button type="submit" class="btn btn-success mt-3 mb-3">Create</button>
                            </div> 
                        </c:if>
                    </form>
                </div>
            </c:if>
            <div>
                <form action="admin" method="post">                    
                    <c:if test="${selectedItem ne null}">
                        <h2>Edit item</h2>
                        <input type="hidden" name="itemId" value="${selectedItem.itemId}">
                        <p>Category: ${selectedItem.category.categoryName}</p>
                        <div class="form-group">
                            <label for="itemName">Name:</label>
                            <input type="text" name="itemName" value="${selectedItem.itemName}" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="price">Price:</label>
                            <input type="number" name="price" value="${selectedItem.price}" class="form-control">
                        </div>
                        <input type="hidden" name="action" value="update">
                        <div>
                        <button type="submit" class="btn btn-primary  mt-3">Update</button>
                        </div>
                        <div>
                        <a href="admin" class="btn btn-secondary  mt-3 mb-3">Cancel Edit</a>
                        </div>
                        </c:if>
                </form>
            </div>
        </div>

        <!-- Add Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
