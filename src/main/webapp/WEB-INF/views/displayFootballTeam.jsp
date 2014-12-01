<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>My Football Team</title>
    </head>

    <body>

        My Football Team Information Goes Here

        <c:forEach var="fixture" items="${upcomingFixtures}">
            <br/>
            <c:out value="${fixture.homeTeam}"/> <b>vs.</b> <c:out value="${fixture.awayTeam}"/>
        </c:forEach>

        <br/>
        <br/>
        <c:forEach var="result" items="${recentResults}">
            <br/>
            <c:out value="${result.homeTeam}"/> (<c:out value="${result.goalsHomeTeam}"/>) <b>vs.</b> (<c:out value="${result.goalsAwayTeam}"/>)  <c:out value="${result.awayTeam}"/>
        </c:forEach>


    </body>

</html>