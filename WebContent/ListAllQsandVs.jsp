<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List Videos</title>
</head>
<body>
    <center>
        <h1>List of Videos</h1>
      
    </center>
    <div class="container" align="center" style="position:relative; margin-top:0 px; ">
    <h2 align="center">Questions and Videos</h2>
        <table border="1" cellpadding="5" style="position:relative; margin-top:0 px;">
            <c:forEach var="que" items="${listQuestions}">
           
            <tr>
            <th><c:out value="${que.question}" /></th>
            </tr>
            <tr>
                <th>Title</th>
                <th>Post Date</th>
                <th>Url</th>
            </tr>
                <c:forEach var="video" items="${listVideos}">
                <c:if test= "${que.qid==video.qid}">
                <tr>
                    <td><c:out value="${video.title}" /></td>
                    <td><c:out value="${video.postDate}" /></td>
                    <td><a href="player?url=<c:out value="${video.url}" />"><c:out value="${video.url}" /></a></td>
                    
                </tr>
                </c:if>
                </c:forEach>
             
            </c:forEach>
        </table>
    </div>   
</body>
</html>