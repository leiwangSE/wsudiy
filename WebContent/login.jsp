<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
    <form action="login" method="post">
        
       
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                   
                        Login User
                   
                </h2>
            </caption>
                        
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
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>

</body>
</html>