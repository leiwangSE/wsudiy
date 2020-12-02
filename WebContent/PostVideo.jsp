<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>DIY videos</title>
</head>
<body>
	
    <div align="center">
       <h3 align="center"> Post a Video </h3>

	<div align="center">
       <form action="postVideo" method="post">
        <table border="1" cellpadding="5">
	

	    <tr>
             <td colspan="2" align="right">
              <c:out value='${question.username}' />
             </td>
        </tr>

	        <c:if test="${question !=null}">
                    <input type="hidden" name="qid" size="45" value="<c:out value='${question.qid}'  />"
                            
                        />
           
              </c:if>
           

            <tr>
                <th>Url: </th>
                <td>
                    <input type="text" name="url" size="45"
                            
                        />
                </td>
            </tr>
            
            <tr>
                <th>Title: </th>
                <td>
                    <input type="text" name="title" size="45"
                            
                        />
                </td>
            </tr>
            
             <tr>
                <th>Description: </th>
                <td>
                    <textarea name="des" cols="40" rows="5"></textarea>
                </td>
            </tr>
            
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Submit" />
                </td>
            </tr>
  		</table>
       </form>
    </div> 
</body>
</html>