package uk.co.myfootballclub.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import uk.co.myfootballclub.deserializer.LeagueRankingDeserializer;

import java.util.List;

/**
 * League Model Object.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 28/11/2014
 * @project MyFootballClub
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class League {

    private String league;
    private int matchday;
    @JsonDeserialize(using = LeagueRankingDeserializer.class)
    private List<LeagueRanking> ranking;

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public int getMatchday() {
        return matchday;
    }

    public void setMatchday(int matchday) {
        this.matchday = matchday;
    }

    public List<LeagueRanking> getRanking() {
        return ranking;
    }

    public void setRanking(List<LeagueRanking> ranking) {
        this.ranking = ranking;
    }

    @Override
    public String toString() {
        return "League{" +
                "league='" + league + '\'' +
                ", matchday=" + matchday +
                ", ranking=" + ranking +
                '}';
    }
}
