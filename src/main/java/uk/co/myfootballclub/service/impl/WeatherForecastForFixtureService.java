package uk.co.myfootballclub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.co.myfootballclub.model.Fixture;
import uk.co.myfootballclub.model.weather.WeatherFixture;

import static java.lang.String.format;

/**
 * Weather Forecast for a given Fixture
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 03/12/2014
 * @project MyFootballClub
 */
@PropertySource("/resources/weatherLocation.properties")
@Service
public class WeatherForecastForFixtureService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    private static final String WEATHER_API_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static final String WEATHER_METRICS = "&units=metric";

    public WeatherFixture retrieveWeatherForecastForFixture(Fixture nextFixture) throws Exception {

        validateFixtureList(nextFixture);

        String weatherLocation = environment.getProperty(nextFixture.getHomeTeam().replaceAll("\\s",""));

        return restTemplate.getForObject(format("%s%s%s", WEATHER_API_URL, weatherLocation, WEATHER_METRICS),
                WeatherFixture.class);
    }

    private void validateFixtureList(Fixture nextFixture) throws Exception {
        if(nextFixture == null) throw new Exception();
    }

}
