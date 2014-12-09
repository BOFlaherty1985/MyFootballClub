<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <title>My Football Team</title>

        <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
        <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">

    </head>

    <body>

        <div class="container">

            <div class="row">

                <div class="col-md-1"></div>

                <div class="col-md-10">
                    <!-- Register User Modal -->
                    <button type="button" class="btn btn-primary" data-toggle="modal"
                            data-target=".bs-example-modal-lg">Register User</button>
                </div>

                <div class="col-md-1"></div>

            </div>

            <div class="row">

                <div class="col-md-1"></div>

                <div class="col-md-10">
                    <p class="text-center">
                        <img src="http://upload.wikimedia.org/wikipedia/en/e/e0/West_Ham_United_FC.svg"
                             alt="..." class="img-thumbnail" style="height: 200px; width: 200px">
                    </p>
                    <p>
                        <h2>
                            <b>West Ham United FC</b>
                        </h2>
                        <h4>Club Information</h4>
                        <h5>
                            <b>Nickname:</b>
                            <c:out value="${clubDetails.clubNickname}"/><br/>
                            <b>Founded: </b>
                            <c:out value="${clubDetails.clubFounded}"/><br/>
                            <b>Stadium: </b>
                            <c:out value="${clubDetails.clubStadium}"/><br/>
                            <b>Capacity: </b>
                            <c:out value="${clubDetails.clubCapacity}"/><br/>
                            <b>Website: </b>
                            <a href="<c:out value="${clubDetails.clubWebsite}"/>" target="_blank">Official Club Website</a><br/>
                            <b>Facebook: </b>
                            <c:out value="${clubDetails.clubFacebook}"/><br/>
                        </h5>
                    </p>
                </div>

                <div class="col-md-1"></div>

            </div>

            <div class="row">

                <div class="col-md-1"></div>

                <div class="col-md-10">
                    <p>
                        <h4>
                            <b>Next Fixture</b>
                        </h4>
                    </p>
                    <p>
                        <table class="table">
                            <tr>
                                <td>
                                    <c:out value="${teamsNextFixture.homeTeam}"/>
                                </td>
                                <td>
                                    <b>vs.</b>
                                </td>
                                <td>
                                    <c:out value="${teamsNextFixture.awayTeam}"/>
                                </td>
                            </tr>
                        </table>
                    </p>
                    <p>
                        <b>Location: </b><c:out value="${weatherForFixture.name}"/><br/>
                        <b>Forecast: </b><c:out value="${weatherForFixture.weather.main}"/> - <c:out value="${weatherForFixture.weather.description}"/>
                        <b>Temperature: </b>: <c:out value="${weatherForFixture.main.temp}"/> (Min: <c:out value="${weatherForFixture.main.temp_min}"/> / Max: <c:out value="${weatherForFixture.main.temp_max}"/>)<br/>
                    </p>
                </div>

                <div class="col-md-1"></div>

            </div>

            <div class="row">

                <div class="col-md-1"></div>

                <div class="col-md-5">
                    <p>
                        <h4>
                            <b>Upcoming Fixtures</b>
                        </h4>
                    </p>

                    <p>
                        <table class="table">
                            <c:forEach var="fixture" items="${upcomingFixtures}">
                                <tr>
                                    <td>
                                        <c:out value="${fixture.homeTeam}"/>
                                    </td>
                                    <td>
                                        <b>vs.</b>
                                    </td>
                                    <td>
                                        <c:out value="${fixture.awayTeam}"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </p>

                </div>

                <div class="col-md-5">
                    <p>
                        <h4>
                            <b>Previous Results</b>
                        </h4>
                    </p>

                    <p>
                        <table class="table">
                            <c:forEach var="result" items="${recentResults}">
                                <tr>
                                    <td>
                                        <c:out value="${result.homeTeam}"/>
                                    </td>
                                    <td>
                                        <c:out value="${result.goalsHomeTeam}"/>
                                    </td>
                                    <td>
                                        <b>vs.</b>
                                    </td>
                                    <td>
                                        <c:out value="${result.goalsAwayTeam}"/>
                                    </td>
                                    <td>
                                        <c:out value="${result.awayTeam}"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </p>

                </div>

                <div class="col-md-1"></div>

            </div>

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

        </div>

        <div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
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
                                <td><input type="submit" value="Register User" class="btn btn-primary"/></td>
                            </tr>
                        </table>

                    </form:form>
                </div>
            </div>
        </div>

    </body>

</html>