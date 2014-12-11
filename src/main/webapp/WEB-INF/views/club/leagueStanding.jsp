<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row">

    <div class="col-md-1"></div>

    <div class="col-md-10">
        <p>
        <h3>
            <b><c:out value="${leagueStandings.league}"/> Standings</b>
        </h3>
        </p>

        <table class="table table-striped">
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
    </div>

    <div class="col-md-1"></div>

</div>
