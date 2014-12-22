<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row">

    <div class="col-md-1"></div>

    <div class="col-md-10">
        <p>
        <h4><b>Next Fixture</b></h4>
        </p>
        <p>
        <table class="table">
            <tr>
                <td><c:out value="${teamsNextFixture.homeTeam}"/></td>
                <td><b>vs.</b></td>
                <td><c:out value="${teamsNextFixture.awayTeam}"/></td>
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

    <div class="col-md-10">
        <p>
            <h4> <b>Head 2 Head</b></h4>
        </p>
        <table class="table">
            <c:forEach var="form" varStatus="f" items="${previousForm}">
                <tr id="headtohead_${f.count}">
                    <td id="homeTeam"><c:out value="${form.homeTeam}"/></td>
                    <td id="homeGoals"><c:out value="${form.goalsHomeTeam}"/></td>
                    <td><b>vs.</b></td>
                    <td id="awayGoals"><c:out value="${form.goalsAwayTeam}"/></td>
                    <td id="awayTeam"><c:out value="${form.awayTeam}"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="col-md-1"></div>

</div>