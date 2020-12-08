<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List all My Favorites</title>
</head>
<body>
    <center>
        <h1>My Favorites</h1>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of My Favorites</h2></caption>
            <tr>
                <th>Title</th>
                <th>Url</th>
            </tr>
            <c:forEach var="fav" items="${listFavorites}">
                <tr>
                    <td><c:out value="${fav.title}" /></td>
                    <td><c:out value="${fav.url}" /></td>
                    <td>
                        <a href="showPostForm?qid=<c:out value='${que.qid}' />">Reply</a>
                                        
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>