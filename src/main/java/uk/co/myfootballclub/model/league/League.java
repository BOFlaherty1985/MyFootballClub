package uk.co.myfootballclub.model.league;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonIgnore
    @JsonProperty("_links")
    private LeagueLink links;
    private String leagueCaption;
    private int matchday;
    @JsonDeserialize(using = LeagueRankingDeserializer.class)
    private List<LeagueStanding> standing;

    public LeagueLink getLinks() {
        return links;
    }

    public void setLinks(LeagueLink links) {
        this.links = links;
    }

    public String getLeagueCaption() {
        return leagueCaption;
    }

    public void setLeagueCaption(String leagueCaption) {
        this.leagueCaption = leagueCaption;
    }

    public int getMatchday() {
        return matchday;
    }

    public void setMatchday(int matchday) {
        this.matchday = matchday;
    }

    public List<LeagueStanding> getStanding() {
        return standing;
    }

    public void setStanding(List<LeagueStanding> standing) {
        this.standing = standing;
    }

    @Override
    public String toString() {
        return "League{" +
                "links=" + links +
                ", leagueCaption='" + leagueCaption + '\'' +
                ", matchday=" + matchday +
                ", standing=" + standing +
                '}';
    }

}
