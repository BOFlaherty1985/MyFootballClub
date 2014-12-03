package uk.co.myfootballclub.model.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import uk.co.myfootballclub.deserializer.WeatherDescriptionDeserializer;
import uk.co.myfootballclub.deserializer.WeatherMainDeserializer;

/**
 * Weather Model Object
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 03/12/2014
 * @project MyFootballClub
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherFixture {

    private String name;
    @JsonDeserialize(using = WeatherDescriptionDeserializer.class)
    @JsonProperty("weather")
    private WeatherType weather;
    @JsonDeserialize(using = WeatherMainDeserializer.class)
    @JsonProperty("main")
    private WeatherDetails main;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WeatherType getWeather() {
        return weather;
    }

    public void setWeather(WeatherType weather) {
        this.weather = weather;
    }

    public WeatherDetails getMain() {
        return main;
    }

    public void setMain(WeatherDetails main) {
        this.main = main;
    }

    @Override
    public String toString() {
        return "WeatherFixture{" +
                "name='" + name + '\'' +
                ", weather=" + weather +
                ", main=" + main +
                '}';
    }

}
