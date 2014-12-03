<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>My Football Team</title>
    </head>

    <body>

        My Football Team Information Goes Here

        <br/>
        <c:out value="${weatherForFixture.name}"/><br/>
        <c:out value="${weatherForFixture.weather.main}"/> - <c:out value="${weatherForFixture.weather.description}"/><br/>
        Temp: <c:out value="${weatherForFixture.main.temp}"/><br/>
        Temp Min: <c:out value="${weatherForFixture.main.temp_min}"/><br/>
        Temp Max: <c:out value="${weatherForFixture.main.temp_max}"/><br/>

        <br/>
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

        <br/>
        <br/>
        <c:out value="${leagueStandings.league}"/><br/>
        <c:out value="${leagueStandings.matchday}"/><br/>

        <table>
            <tr>
                <th>Position</th>
                <th>Team Name</th>
                <th>GF</th>
                <th>GA</th>
                <th>GD</th>
                <th>Points</th>
            </tr>
            <c:forEach var="ranking" items="${leagueStandings.ranking}">
                <tr>
                    <td><c:out value="${ranking.rank}"/></td>
                    <td><c:out value="${ranking.team}"/></td>
                    <td><c:out value="${ranking.goals}"/></td>
                    <td><c:out value="${ranking.goalsAgainst}"/></td>
                    <td><c:out value="${ranking.goalDifference}"/></td>
                    <td><c:out value="${ranking.points}"/></td>
                </tr>
            </c:forEach>
        </table>

    </body>

</html>