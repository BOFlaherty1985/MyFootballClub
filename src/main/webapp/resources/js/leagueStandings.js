/**
 * Created by BO034731 on 22/12/2014.
 */

function hightlightMyFootballClubInLeagueTable() {

    var myFootballTeam = $('#myFootballClub').val();

    $('td[id^=team_]').each(function() {

        if($(this).text() == myFootballTeam) {
            $(this).parent().css("background", "#33CC99");
        }

    });

}
