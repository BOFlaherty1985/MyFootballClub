<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row">

    <div class="col-md-1"></div>

    <div class="col-md-5">
        <p>
            <h4><b>Upcoming Fixtures</b></h4>

            <select id="upcomingFixtureSelect" onchange="daySelection(value, 'n', '#upcomingFixtureDiv');">
                <option value="7">7 Days</option>
                <option value="14">14 Days</option>
                <option value="21">21 Days</option>
                <option value="30">30 Days</option>
                <option value="40">40 Days</option>
                <option value="50">50 Days</option>
            </select>

        </p>

        <div id="upcomingFixtureDiv">

            <p>
                <table class="table" id="upcomingFixtures">
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

    </div>

    <div class="col-md-5">
        <p>
            <h4><b>Previous Results</b></h4>

            <select id="previousResultSelect" onchange="daySelection(value, 'p', '#previousResultsDiv');">
                <option value="7">7 Days</option>
                <option value="14">14 Days</option>
                <option value="21">21 Days</option>
                <option value="30">30 Days</option>
                <option value="40">40 Days</option>
                <option value="50">50 Days</option>
            </select>

        </p>
        <p>

            <div id="previousResultsDiv">

            <table class="table" id="recentResults">
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

            </div>

        </p>

    </div>

    <div class="col-md-1"></div>

</div>