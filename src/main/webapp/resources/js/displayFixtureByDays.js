/**
 * Created by BO034731 on 19/12/2014.
 */

const previousResultsDiv = '#previousResultsDiv';

function displayFixtureByDays(noOfDays, typeOfFixture, divId) {
    $.ajax( {
        type: "POST",
        url: "http://localhost:8080/MyFootballClub/retrieveFixturesByDays",
        data: { teamId:  563, numberOfDays: noOfDays, typeOfFixture: typeOfFixture },
        success: function(data) {

            var json = jQuery.parseJSON(data);

            $(divId).empty();

            var table = $("<table>").addClass("table");

            $.each(json, function(idx, fixture) {

                var tableRow = $("<tr>");

                var home_td = $("<td>").html("<td>" + fixture.homeTeam + "</td>");

                var goalsHomeTeam;

                if(divId == previousResultsDiv) {
                    goalsHomeTeam = $("<td>").html("<td>" + fixture.goalsHomeTeam + "</td>");
                }


                var vs_td = $("<td>").html("<td><b> vs. </b></td>");

                var goalsAwayTeam;

                if(divId == previousResultsDiv) {
                    goalsAwayTeam = $("<td>").html("<td>" + fixture.goalsAwayTeam + "</td>");
                }

                var away_td = $("<td>").html("<td>" + fixture.awayTeam + "</td>");

                tableRow.append(home_td).append(goalsHomeTeam).append(vs_td).append(goalsAwayTeam).append(away_td);
                table.append(tableRow);

            });

            $(divId).append(table);

        }

    })
};
