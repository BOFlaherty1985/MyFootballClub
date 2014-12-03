package uk.co.myfootballclub.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import uk.co.myfootballclub.model.league.LeagueRanking;

import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * League Ranking Deserializer Test
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 02/12/2014
 * @project MyFootballClub
 */
public class LeagueRankingDeserializerTest {

    private LeagueRankingDeserializer deserializer = new LeagueRankingDeserializer();

    private JsonParser jsonparser;
    private DeserializationContext context;
    private JsonNode jsonNode;
    private ObjectCodec objectCode;

    @Before
    public void setUp() {
        jsonparser = Mockito.mock(JsonParser.class);
        jsonNode = Mockito.mock(JsonNode.class);
        context = Mockito.mock(DeserializationContext.class);
        objectCode = Mockito.mock(ObjectCodec.class);
    }

    @Test
    public void assertThatDeserializerDoesNotReturnANullLeagueRankingList() throws IOException {

        when(jsonparser.getCodec()).thenReturn(objectCode);
        when(jsonparser.getCodec().readTree(jsonparser)).thenReturn(jsonNode);

        List<LeagueRanking> leagueRanking = deserializer.deserialize(jsonparser, context);
        assertNotNull("Deserializer does not return a null object.", leagueRanking);
    }



}
