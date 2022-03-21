

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         
        <title>User Manager</title>
        <link rel="stylesheet" href="user.css" media="all" type="text/css">
    </head>
    <body>
            <form method="post" action="user">
            <table>
                    <tr>
                        <th>Email:</th>
                        <th>First Name:</th>
                        <th>Last Name:</th>
                        <th>Role:</th>
                        <th>Edit:</th>
                        <th>Delete:</th>
                            
                    </tr>
                    <c:forEach items ="${users}" var="user">
                        <tr>
                        <th>${user.email}</th>
                        <th>${user.firstName}</th>
                        <th>${user.lastName}</th>
                        <c:forEach items ="${roles}" var="role">
                            <c:if test="${user.role == role.roleId}">
                                <th>${role.roleName}</th>
                            </c:if>
                        </c:forEach>
                        <th><a href="user?action=edit&email=${user.email}&firstname=${user.firstName}&lastname=${user.lastName}
                               &password=${user.password}&active=${user.active}">Edit</a></th>
                        <th><a href="user?action=delete&email=${user.email}">Delete</a></th>
                        </tr>
                    </c:forEach>
                </table>
                <div class="first">
                    <h1> Add </h1>
            </form>
            <form method="post" action="user">
                <label>Email:</label><input type="email" name="addemail" value="" >
                <br>
                <label>First Name:</label><input type="text" name="addfirstname" value="">
                <br>
                <label>Last Name:</label><input type="text" name="addlastname" value="">
                <br>
                <label>Password:</label><input type="password" name="password" value="">
                <br>
                <label>Status</label> <input type="checkbox" name="active" >
                 <br>
                <select name="roles">
                    <c:forEach items ="${roles}" var="role">
                        <option value="${role.roleName}">${role.roleName}</option>
                    </c:forEach>
                </select>
                 <br>
                <input type="hidden" name="action" value="add">
                <input type="submit" value="Add">
            </form>
</div>
                
        <div class="second">
            <h1> Edit </h1>
            <form method="post" action="user">
                <label>Email:</label><input type="text" name="email" value="${email}" readonly>
                <br>
                <label>First Name:</label><input type="text" name="firstname" value="${firstname}">
                <br>
                <label>Last Name:</label><input type="text" name="lastname" value="${lastname}">
                <br>
                <label>Password:</label><input type="text" name="password" value="${password}">
                <br>
                <c:choose>
                    <c:when test="${active}">
                        <label>Status</label> <input type="checkbox" name="active" checked>
                        <br>
                </c:when>
                            <c:otherwise>
                        <label>Status</label>  <input type="checkbox" name="active">
                        <br>
                    </c:otherwise>
                </c:choose>
                <select name="role">
                    <c:forEach items ="${roles}" var="role">
                        <option value="${role.roleName}">${role.roleName}</option>
                    </c:forEach>
                </select>
                        <br>
                <input type="submit" value="Edit">
                 <input type="hidden" name="action" value="edit">
            </form>
        </div>

    </body>
</html>
