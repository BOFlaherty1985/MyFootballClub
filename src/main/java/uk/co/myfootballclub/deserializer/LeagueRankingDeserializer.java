package uk.co.myfootballclub.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import uk.co.myfootballclub.model.league.LeagueStanding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * League Ranking Deserializer (JSON)
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 02/12/2014
 * @project MyFootballClub
 */
public class LeagueRankingDeserializer extends JsonDeserializer<List<LeagueStanding>> {

    @Override
    public List<LeagueStanding> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        JsonNode node = jp.getCodec().readTree(jp);

        List<LeagueStanding> leagueStandings = new ArrayList<LeagueStanding>();

        for(int index = 0; index < node.size(); index++) {

            LeagueStanding aTeam = new LeagueStanding();

            aTeam.setPosition(deserializeIntegerElement(node, index, "position"));
            aTeam.setTeamName(deserializeTextElement(node, index, "teamName"));
            aTeam.setPlayedGames(deserializeIntegerElement(node, index, "playedGames"));
            aTeam.setPoints(deserializeIntegerElement(node, index, "points"));
            aTeam.setGoals(deserializeIntegerElement(node, index, "goals"));
            aTeam.setGoalsAgainst(deserializeIntegerElement(node, index, "goalsAgainst"));
            aTeam.setGoalDifference(deserializeIntegerElement(node, index, "goalDifference"));

            leagueStandings.add(aTeam);

        }

        return leagueStandings;
    }

    private int deserializeIntegerElement(JsonNode node, int index, String fieldName) {
        return node.get(index).get(fieldName).intValue();
    }

    private String deserializeTextElement(JsonNode node, int index, String fieldName) {
        return node.get(index).get(fieldName).textValue();
    }

}