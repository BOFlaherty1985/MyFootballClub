

function determineHeadToHeadRowColour() {

    var myFootballTeam = $('#myFootballClub').val();

    $('tr[id^=headtohead_]').each(function() {

        var homeGoals = $(this).find('#homeGoals').text();
        var awayGoals = $(this).find('#awayGoals').text();

        if($(this).find('#homeTeam').text() == myFootballTeam) {

            if(awayGoals > homeGoals) {
                $(this).css("background", "#FFB6C1");
            }

            if(homeGoals > awayGoals) {
                $(this).css("background", "#98FB98");
            }

        }

        if($(this).find('#awayTeam').text() == myFootballTeam) {

            if(homeGoals > awayGoals) {
                $(this).css("background", "#FFB6C1");
            }

            if(awayGoals > homeGoals) {
                $(this).css("background", "#98FB98");
            }

        }

        if($(this).find('#homeTeam').text() == myFootballTeam
            || $(this).find('#awayTeam').text() == myFootballTeam) {

            if(awayGoals == homeGoals) {
                $(this).css("background", "#F4F4F4");
            }

        }

    });

}