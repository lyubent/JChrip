    <%-- 
    Document   : index
    Created on : Jul 2, 2012, 10:29:28 PM
    Author     : fps700
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:useBean id="twitterBean" scope="request" class="net.zanity.cassandra.schema.TweetHandler" />
    </head>
    <body>
        <h1>Hello World!</h1>
        <c:forEach var="x" begin="0" end="10" step="2">
            <tr><td><c:out value="${x}"/></td>
            <td><c:out value="${x * x}"/></td></tr>
        </c:forEach>
            <c:out value="${twitterBean.displayTestData()}" />
        
        
    </body>
</html>
