<%--
  Created by IntelliJ IDEA.
  User: BO034731
  Date: 08/12/2014
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <title>Register User</title>

        <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
        <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">

    </head>

    <body>

        <div id="container">

            <form:form action="/MyFootballClub/registerUser" method="post" commandName="user">

                <table class="table-condensed">
                    <tr>
                        <td><form:label path="firstName">First Name:</form:label></td>
                        <td><form:input path="firstName"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="lastName">Last Name:</form:label></td>
                        <td><form:input path="lastName"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="username">Username:</form:label></td>
                        <td><form:input path="username"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="email">Email:</form:label></td>
                        <td><form:input path="email"/></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Register User"/></td>
                    </tr>
                </table>

            </form:form>

        </div>

    </body>
</html>
