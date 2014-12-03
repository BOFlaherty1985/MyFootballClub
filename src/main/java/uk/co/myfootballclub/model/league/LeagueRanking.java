package uk.co.myfootballclub.model.league;

/**
 * League Ranking Model Object.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 02/12/2014
 * @project MyFootballClub
 */
public class LeagueRanking {
    private int rank;
    private String team;
    private String crestURL;
    private int points;
    private int goals;
    private int goalsAgainst;
    private int goalDifference;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getCrestURL() {
        return crestURL;
    }

    public void setCrestURL(String crestURL) {
        this.crestURL = crestURL;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
    }

    @Override
    public String toString() {
        return "LeagueRanking{" +
                "rank=" + rank +
                ", team='" + team + '\'' +
                ", crestURL='" + crestURL + '\'' +
                ", points=" + points +
                ", goals=" + goals +
                ", goalsAgainst=" + goalsAgainst +
                ", goalDifference=" + goalDifference +
                '}';
    }
}
