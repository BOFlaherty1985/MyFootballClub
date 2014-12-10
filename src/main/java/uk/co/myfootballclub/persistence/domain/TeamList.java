package uk.co.myfootballclub.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Team List Domain Object
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 10/12/2014
 * @project MyFootballClub
 */
@Entity
public class TeamList {

    @Id
    private int teamId;
    private String teamDescription;
    private int leagueId;

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamDescription() {
        return teamDescription;
    }

    public void setTeamDescription(String teamDescription) {
        this.teamDescription = teamDescription;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    @Override
    public String toString() {
        return "TeamList{" +
                "teamId=" + teamId +
                ", teamDescription='" + teamDescription + '\'' +
                ", leagueId=" + leagueId +
                '}';
    }

}
