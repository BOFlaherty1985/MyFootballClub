package uk.co.myfootballclub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.co.myfootballclub.model.Fixture;
import uk.co.myfootballclub.model.weather.WeatherFixture;

import java.util.List;

import static java.lang.String.format;

/**
 * Weather Forecast for a given Fixture
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 03/12/2014
 * @project MyFootballClub
 */
@PropertySource("classpath:/uk/co/myfootballclub/config/weatherLocation.properties")
@Service
public class WeatherForecastForFixtureService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    private static final String WEATHER_API_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static final String WEATHER_METRICS = "&units=metric";

    public WeatherFixture retrieveWeatherForecastForFixture(List<Fixture> fixtureList) throws Exception {

        if(fixtureList == null) {
            throw new Exception();
        }

        WeatherFixture weatherFixture = new WeatherFixture();

        if(!fixtureList.isEmpty()) {

            String weatherLocation = environment.getProperty(fixtureList.get(0).getHomeTeam().replaceAll(" ", ""));

            weatherFixture = restTemplate.getForObject(format("%s%s%s", WEATHER_API_URL, weatherLocation, WEATHER_METRICS),
                    WeatherFixture.class);

        }

        return weatherFixture;
    }

}
