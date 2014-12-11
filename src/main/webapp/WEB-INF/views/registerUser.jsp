<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form action="/MyFootballClub/registerUser" method="post" commandName="user">

    <table class="table table-responsive">
        <tr class="active">
            <td colspan="2">
                <b>User Registration</b>
            </td>
        </tr>
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
            <td><form:label path="myFootballClub">Your Team:</form:label></td>
            <td>
                <form:select path="myFootballClub">
                    <c:forEach var="team" items="${teamsForLeague}">
                        <form:option value="${team.teamId}" label="${team.teamDescription}"/>
                    </c:forEach>
                </form:select>
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="Register User" class="btn btn-primary"/></td>
        </tr>
    </table>

</form:form>