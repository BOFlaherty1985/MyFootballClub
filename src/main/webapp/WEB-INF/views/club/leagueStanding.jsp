<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row">

    <div class="col-md-1"></div>

    <div class="col-md-10">
        <p>
        <h3>
            <b><c:out value="${leagueStandings.leagueCaption}"/> Standings</b>
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
            <c:forEach var="standing" items="${leagueStandings.standing}">
                <tr>
                    <td><c:out value="${standing.position}"/></td>
                    <td id="team_${standing.position}"><c:out value="${standing.teamName}"/></td>
                    <td><c:out value="${standing.goals}"/></td>
                    <td><c:out value="${standing.goalsAgainst}"/></td>
                    <td><c:out value="${standing.goalDifference}"/></td>
                    <td><c:out value="${standing.points}"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="col-md-1"></div>

</div>
