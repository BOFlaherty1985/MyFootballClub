// global variables
var RESULT_EQL_WIN = "#98FB98";
var RESULT_EQL_LOST = "#FFB6C1";
var RESULT_EQL_DRAW = "#F4F4F4";

function determineHeadToHeadRowColour() {

    var myFootballTeam = $('#myFootballClub').val();

    $('tr[id^=headtohead_]').each(function() {

        var homeGoals = $(this).find('#homeGoals').text();
        var awayGoals = $(this).find('#awayGoals').text();

        // is home team
        myFootballTeamEqualsHome($(this), myFootballTeam, homeGoals, awayGoals);
        // is away team
        myFootballTeamEqualsAway($(this), myFootballTeam, homeGoals, awayGoals);
        // is a draw
        myFootballTeamResultIsEqlDraw($(this), myFootballTeam, homeGoals, awayGoals);

    });

}

function myFootballTeamEqualsHome(tableRow, myFootballTeam, homeGoals, awayGoals) {

    if(tableRow.find('#homeTeam').text() == myFootballTeam) {

        if(awayGoals > homeGoals) {
            tableRow.css("background", RESULT_EQL_LOST);
        }

        if(homeGoals > awayGoals) {
            tableRow.css("background", RESULT_EQL_WIN);
        }

    }

}

function myFootballTeamEqualsAway(tableRow, myFootballTeam, homeGoals, awayGoals) {

    if(tableRow.find('#awayTeam').text() == myFootballTeam) {

        if(homeGoals > awayGoals) {
            tableRow.css("background", RESULT_EQL_LOST);
        }

        if(awayGoals > homeGoals) {
            tableRow.css("background", RESULT_EQL_WIN);
        }

    }

}

function myFootballTeamResultIsEqlDraw(tableRow, myFootballTeam, homeGoals, awayGoals) {

    if($(this).find('#homeTeam').text() == myFootballTeam
        || $(this).find('#awayTeam').text() == myFootballTeam) {

        if(awayGoals == homeGoals) {
            $(this).css("background", RESULT_EQL_DRAW);
        }

    }

}
