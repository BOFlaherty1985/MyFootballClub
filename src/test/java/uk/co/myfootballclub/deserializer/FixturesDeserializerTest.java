package uk.co.myfootballclub.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import uk.co.myfootballclub.model.Fixture;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Fixtures Deserializer
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 15/01/2015
 * @project MyFootballClub
 */
public class FixturesDeserializerTest {

    /*
        links
            _self
            herf

        team
            href

        count

        fixtures
            id
            date
            matchday
            homeTeam
            awayTeam
            goalsHomeTeam
            goalsAwayTeam

    */

    private final FixturesDeserializer deserializer = new FixturesDeserializer();

    private JsonParser jsonparser;
    private DeserializationContext context;
    private JsonNode jsonNode;
    private ObjectCodec objectCode;

    @Before
    public void setUp() throws IOException {
        jsonparser = Mockito.mock(JsonParser.class);
        jsonNode = Mockito.mock(JsonNode.class);
        context = Mockito.mock(DeserializationContext.class);
        objectCode = Mockito.mock(ObjectCodec.class);
    }

    @Test
    public void deserialize_returnType_isNotNull() throws IOException {

        setupJsonParser();

        assertNotNull("Return is not null.", deserializer.deserialize(jsonparser, context));

    }

    @Test
    public void deserialize_returnTypeList_sizeIsGreaterThanZero() throws IOException {

        setupJsonParser();

        List<Fixture> resultList = deserializer.deserialize(jsonparser, context);
        assertTrue("Result list size is greater than 0.", resultList.size() > 0);

    }

    @Test
    public void deserialize_returnTypeList_containsFixtureObject() throws IOException {

        setupJsonParser();

        List<Fixture> resultList = deserializer.deserialize(jsonparser, context);
        assertTrue("Result list contains Fixture object.", resultList.get(0) instanceof Fixture);

    }

    @Test
    public void derialize_jsonParserReadTree_verifyMethodCalled() throws IOException {

        setupJsonParser();

        deserializer.deserialize(jsonparser, context);
        verify(jsonparser.getCodec(), times(1)).readTree(jsonparser);

    }

    @Test
    public void deserialize_singleJsonNode_integerDeserializerCalled() throws IOException {

        setNodeCount(1);

        when(jsonNode.get(0)).thenReturn(jsonNode);
        when(jsonNode.get(0).get("id")).thenReturn(jsonNode);
        when(jsonNode.get(0).get("id").intValue()).thenReturn(1);

        setupJsonParser();

        deserializer.deserialize(jsonparser, context);
        verify(jsonNode.get(0).get("id"), times(1)).intValue();

    }

    @Test
    public void deserialize_multipleJsonNode_integerDeserializerCalled() throws IOException {

        setNodeCount(2);

        // first node
        when(jsonNode.get(0)).thenReturn(jsonNode);
        when(jsonNode.get(0).get("id")).thenReturn(jsonNode);
        when(jsonNode.get(0).get("id").intValue()).thenReturn(1);

        // second node
        when(jsonNode.get(1)).thenReturn(jsonNode);
        when(jsonNode.get(1).get("id")).thenReturn(jsonNode);
        when(jsonNode.get(1).get("id").intValue()).thenReturn(2);

        setupJsonParser();

        deserializer.deserialize(jsonparser, context);
        verify(jsonNode.get(0).get("id"), times(1)).intValue();
        verify(jsonNode.get(1).get("id"), times(1)).intValue();

    }


    private void setNodeCount(int size) {
        when(jsonNode.size()).thenReturn(size);
    }

    private void setupJsonParser() throws IOException {
        when(jsonparser.getCodec()).thenReturn(objectCode);
        when(jsonparser.getCodec().readTree(jsonparser)).thenReturn(jsonNode);
    }


}
