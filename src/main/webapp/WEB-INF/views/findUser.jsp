<%--
  Created by IntelliJ IDEA.
  User: BO034731
  Date: 08/12/2014
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Finder User</title>
    </head>
    <body>
        <c:out value="${user.username}"/>
        <c:out value="${user.firstName}"/>
        <c:out value="${user.lastName}"/>
        <c:out value="${user.email}"/>
    </body>
</html>
