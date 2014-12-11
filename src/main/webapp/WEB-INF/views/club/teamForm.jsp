<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row">

    <div class="col-md-1"></div>

    <div class="col-md-5">
        <p>
        <h4><b>Upcoming Fixtures</b></h4>
        </p>
        <p>
        <table class="table">
            <c:forEach var="fixture" items="${upcomingFixtures}">
                <tr>
                    <td><c:out value="${fixture.homeTeam}"/></td>
                    <td><b>vs.</b></td>
                    <td><c:out value="${fixture.awayTeam}"/></td>
                </tr>
            </c:forEach>
        </table>
        </p>

    </div>

    <div class="col-md-5">
        <p>
        <h4> <b>Previous Results</b></h4>
        </p>
        <p>
        <table class="table">
            <c:forEach var="result" items="${recentResults}">
                <tr>
                    <td><c:out value="${result.homeTeam}"/></td>
                    <td><c:out value="${result.goalsHomeTeam}"/></td>
                    <td><b>vs.</b></td>
                    <td><c:out value="${result.goalsAwayTeam}"/></td>
                    <td><c:out value="${result.awayTeam}"/></td>
                </tr>
            </c:forEach>
        </table>
        </p>

    </div>

    <div class="col-md-1"></div>

</div>