package uk.co.myfootballclub.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import uk.co.myfootballclub.model.weather.WeatherType;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Weather Description Deserializer Test
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 03/12/2014
 * @project MyFootballClub
 */
public class WeatherDescriptionDeserializerTest {

    private WeatherDescriptionDeserializer deserializer = new WeatherDescriptionDeserializer();

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

        mockJsonNode();
    }

    @Test
    public void assertThatWeatherDescriptionDeserializerMethodDoesNotReturnNull() throws IOException {
        assertNotNull(deserializer.deserialize(jsonparser, context));
    }


    @Test
    public void assertThatWeatherTypeObjectIsReturnedWithValidMainField() throws IOException {
        when(jsonNode.get(0).get("main").textValue()).thenReturn("mainValue");

        WeatherType weatherType = deserializer.deserialize(jsonparser, context);
        assertEquals("WeatherType Main Field is Equal to mainValue", "mainValue", weatherType.getMain());
    }

    @Test
    public void assertThatWeatherTypeObjectIsReturnedWithValidDescriptionField() throws IOException {
        when(jsonNode.get(0).get("description").textValue()).thenReturn("descriptionValue");

        WeatherType weatherType = deserializer.deserialize(jsonparser, context);
        assertEquals("WeatherType Main Field is Equal to descriptionValue", "descriptionValue", weatherType.getDescription());
    }

    private void mockJsonNode() throws IOException {
        when(jsonparser.getCodec()).thenReturn(objectCode);
        when(jsonparser.getCodec().readTree(jsonparser)).thenReturn(jsonNode);
        when(jsonNode.get(0)).thenReturn(jsonNode);
        when(jsonNode.get(0).get("main")).thenReturn(jsonNode);
        when(jsonNode.get(0).get("description")).thenReturn(jsonNode);
    }

}
