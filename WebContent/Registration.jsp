<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Registration Application</title>
</head>
<body>
    <center>
        <h1>WSUDIY</h1>
        <h2>
            <a href="new">Register a user</a>
            &nbsp;&nbsp;&nbsp;
            <!-- <a href="list">List All Students</a> -->
             
        </h2>
    </center>
    <div align="center">
        <c:if test="${student1 != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${user == null}">
            <form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${user != null}">
                        Edit User
                    </c:if>
                    <c:if test="${user == null}">
                        Add New User
                    </c:if>
                </h2>
            </caption>
                <c:if test="${user != null}">
                    <input type="number" name="id" value="<c:out value='${user.username}' />" />
                </c:if>           
            <tr>
                <th>Username: </th>
                <td>
                    <input type="text" name="username" size="45"
                            value="<c:out value='${user.username}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Password: </th>
                <td>
                    <input type="text" name="password" size="45"
                            value="<c:out value='${user.password}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>First Name: </th>
                <td>
                    <input type="text" name="firstname" size="45"
                            value="<c:out value='${user.firstname}' />"
                    />
                </td>
            </tr>
             <tr>
                <th>Last Name: </th>
                <td>
                    <input type="text" name="lastname" size="45"
                            value="<c:out value='${user.lastname}' />"
                    />
                </td>
            </tr>
             <tr>
                <th>Age: </th>
                <td>
                    <input type="number" name="age" size="45"
                            value="<c:out value='${user.age}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>