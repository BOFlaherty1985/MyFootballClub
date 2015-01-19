package uk.co.myfootballclub.model.league;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * League Ranking Model Object.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 02/12/2014
 * @project MyFootballClub
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeagueStanding {

    @JsonProperty("_links")
    @JsonIgnore
    private StandingLink link;
    private int position;
    private String teamName;
    private int playedGames;
    private int points;
    private int goals;
    private int goalsAgainst;
    private int goalDifference;

    public StandingLink getLink() {
        return link;
    }

    public void setLink(StandingLink link) {
        this.link = link;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getPlayedGames() {
        return playedGames;
    }

    public void setPlayedGames(int playedGames) {
        this.playedGames = playedGames;
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
        return "LeagueStanding{" +
                "link=" + link +
                ", position=" + position +
                ", teamName='" + teamName + '\'' +
                ", playedGames=" + playedGames +
                ", points=" + points +
                ", goals=" + goals +
                ", goalsAgainst=" + goalsAgainst +
                ", goalDifference=" + goalDifference +
                '}';
    }
}
