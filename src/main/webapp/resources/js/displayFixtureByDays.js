/**
 * Created by BO034731 on 19/12/2014.
 */

const previousResultsDiv = '#previousResultsDiv';

function displayFixtureByDays(noOfDays, typeOfFixture, divId) {

    var myFootballClubId = $('#myFootballClubId').val();

    $.ajax( {
        type: "POST",
        url: "http://localhost:8080/MyFootballClub/retrieveFixturesByDays",
        data: { teamId:  myFootballClubId, numberOfDays: noOfDays, typeOfFixture: typeOfFixture },
        success: function(data) {

            var json = jQuery.parseJSON(data);

            $(divId).empty();

            var table = $("<table>").addClass("table");

            $.each(json, function(idx, fixture) {

                var tableRow = $("<tr>");

                var vs_td = $("<td>").html("<td><b> vs. </b></td>");

                tableRow.append(displayNameOfTeam(fixture.homeTeam))
                    .append(displayGoalsByTeam(fixture.goalsHomeTeam)).
                    append(vs_td).append(displayGoalsByTeam(fixture.goalsAwayTeam))
                    .append(displayNameOfTeam(fixture.awayTeam));

                table.append(tableRow);

            });

            $(divId).append(table);

        }

    })

    function displayNameOfTeam(teamName) {
        return $("<td>").html("<td>" + teamName + "</td>");
    }

    function displayGoalsByTeam(value) {

        var goalsByTeam;

        if(divId == previousResultsDiv) {
            goalsByTeam = goalsAwayTeam = $("<td>").html("<td>" + value + "</td>");
        }

        return goalsByTeam;
    }

};
