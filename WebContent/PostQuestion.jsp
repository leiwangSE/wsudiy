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
       <h3 align="center"> Post a Question </h3>

	<div align="center">
       <form action="postQuestion" method="post">
        <table border="1" cellpadding="5">
	

	    <tr>
             <td colspan="2" align="right">
              <c:out value='${username}' />
             </td>
        </tr>

	         

            <tr>
                <th>Question: </th>
                <td>
                    <textarea name="question" cols="40" rows="5"></textarea>
                </td>
            </tr>
            
            <tr>
                <th>Tags: </th>
                <td>
                    <input type="text" name="tags" size="45"
                            
                        />
                </td>
            </tr>
            
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Submit" />
                </td>
            </tr>
  		</table>
       </form>
       <div><a href="listQuestions">List of Questions</a> </div>
    </div> 
    
</body>
</html>