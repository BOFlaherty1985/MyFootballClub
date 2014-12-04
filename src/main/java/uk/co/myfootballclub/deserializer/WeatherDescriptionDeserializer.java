package uk.co.myfootballclub.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import uk.co.myfootballclub.model.weather.WeatherType;

import java.io.IOException;

/**
 * Weather Description Deserializer
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 03/12/2014
 * @project MyFootballClub
 */
public class WeatherDescriptionDeserializer extends JsonDeserializer<WeatherType> {

    @Override
    public WeatherType deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        JsonNode node = jp.getCodec().readTree(jp);

        WeatherType weatherDetails = new WeatherType();
        weatherDetails.setMain(deserializeTextElement(node, 0, "main"));
        weatherDetails.setDescription(deserializeTextElement(node, 0, "description"));

        return weatherDetails;
    }

    private String deserializeTextElement(JsonNode node, int index, String fieldName) {
        return node.get(index).get(fieldName).textValue();
    }
}
