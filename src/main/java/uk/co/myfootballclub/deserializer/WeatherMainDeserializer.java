package uk.co.myfootballclub.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import uk.co.myfootballclub.model.weather.WeatherDetails;

import java.io.IOException;

/**
 * Weather Main section Deserializer
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 03/12/2014
 * @project MyFootballClub
 */
public class WeatherMainDeserializer extends JsonDeserializer<WeatherDetails> {

    @Override
    public WeatherDetails deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        JsonNode node = jp.getCodec().readTree(jp);

        WeatherDetails weatherMain = new WeatherDetails();
        weatherMain.setTemp(node.get("temp").doubleValue());
        weatherMain.setTemp_min(node.get("temp_min").doubleValue());
        weatherMain.setTemp_max(node.get("temp_max").doubleValue());

        return weatherMain;
    }

}
