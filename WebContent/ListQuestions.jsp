<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List all Questions</title>
</head>
<body>
    <center>
        <h1>Questions Board</h1>
        <h2>
            <a href="PostQuestion.jsp">Post a New Question</a>
            &nbsp;&nbsp;&nbsp;
            <a href="listQuestions">List All Questions</a>
             
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Questions</h2></caption>
            <tr>
                <th>Questions</th>
                <th>Ask Date</th>
            </tr>
            <c:forEach var="que" items="${listQuestions}">
                <tr>
                    <td><c:out value="${que.question}" /></td>
                    <td><c:out value="${que.askDate}" /></td>
                    <td>
                        <a href="showPostForm?qid=<c:out value='${que.qid}' />">Reply</a>
                                        
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>