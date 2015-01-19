package uk.co.myfootballclub.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import uk.co.myfootballclub.model.Fixture;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Fxitures Deserializer
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 15/01/2015
 * @project MyFootballClub
 */
public class FixturesDeserializer extends JsonDeserializer<List<Fixture>> {

    public List<Fixture> deserialize(JsonParser jsonparser, DeserializationContext context) throws IOException {

        List<Fixture> resultList = new ArrayList<>();
        resultList.add(new Fixture());

        JsonNode jsonNode = jsonparser.getCodec().readTree(jsonparser);

        jsonNode.get(0).get("id").intValue();

        return resultList;
    }
}
