package uk.co.myfootballclub.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import uk.co.myfootballclub.config.TestConfig;
import uk.co.myfootballclub.config.WebInitializer;
import uk.co.myfootballclub.model.Fixture;
import uk.co.myfootballclub.model.weather.WeatherDetails;
import uk.co.myfootballclub.model.weather.WeatherFixture;
import uk.co.myfootballclub.model.weather.WeatherType;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Weather Forecast For Given Fixture
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 03/12/2014
 * @project MyFootballClub
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, WebInitializer.class})
@WebAppConfiguration
public class WeatherForecastForFixtureServiceTest {

      /*
        RESTFul API
        http://api.openweathermap.org/data/2.5/weather?q=London,gb&units=metric

        Create Properties file with key/value pairs (teamName.XXXXX) / city api call, suffix ,gb

        Example JSON

        {
            "weather": [
                {
                   "id": 803,
                   "main": "Clouds",
                   "description": "broken clouds",
                   "icon": "04d"
               }
            ],
             "main": {
                   "temp": 5.42,
                   "pressure": 1024,
                   "humidity": 75,
                   "temp_min": 4,
                   "temp_max": 7
             },
             "name": "London"
        }


        1. retrieve next fixture (fixturesByDaysService) and use the first entry in the List<Fixture>
        2. extract homeTeam from Fixture object
        3. use homeTeam value (remove all whitespace) to find corresponding weather city via a properties file
           inject Environment and query using env.getProperty()
        4. call restTemplate getObjectFor() and build a JSON Weather object (as per json above)

        WeatherFixture

          - WeatherType
                - main
                - description
          - WeatherDetails
                - temp
                - temp_min
                - temp_max

           - name

         5. send weather object to controller and save value to the model.

       */

    private MockMvc mockMvc;

    @InjectMocks
    private WeatherForecastForFixtureService service;

    @Before
    public void setUp() {
      MockitoAnnotations.initMocks(this);

    }

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private Environment environment;

    @Mock
    Fixture nextFixture;

    @Test
    public void verifyThatWeatherForecastForFixtureServiceMethodDoesNotReturnNull() throws Exception {

    when(nextFixture.getHomeTeam()).thenReturn("Test Team");
    when(environment.getProperty("TestTeam")).thenReturn("testLocation,gb");

    when(restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?q=testLocation,gb&units=metric",
            WeatherFixture.class)).thenReturn(new WeatherFixture());

    assertNotNull("retrieveWeatherForecastForFixture() return type is not null.",
            service.retrieveWeatherForecastForFixture(nextFixture));

    }

    @Test
    public void assertThatWeatherForecastForFixtureServicesReturnsObjectOfTypeWeatherFixture() throws Exception {

        when(nextFixture.getHomeTeam()).thenReturn("Test Team");
        when(environment.getProperty("TestTeam")).thenReturn("testLocation,gb");

        when(restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?q=testLocation,gb&units=metric",
                WeatherFixture.class)).thenReturn(new WeatherFixture());

      Object object = service.retrieveWeatherForecastForFixture(nextFixture);
      assertTrue("retrieveWeatherForecastForFixture() return is of type Weather.", object instanceof WeatherFixture);

    }

    @Test
    public void throwExceptionWhenFixtureParameterIsNull() {

        try {
            service.retrieveWeatherForecastForFixture(null);
            fail("List of Fixtures Parameter must not be null.");
        }  catch (Exception e) {
            System.out.println("List of Fixtures is null.");
        }

    }

    @Test
    public void verifyThatTheGetHomeTeamMethodIsCalledOnNextFixtureObject() throws Exception {

        when(nextFixture.getHomeTeam()).thenReturn("West Ham United FC");

        service.retrieveWeatherForecastForFixture(nextFixture);
        verify(nextFixture, times(1)).getHomeTeam();

    }



    @Test
    public void verifyThatEnvironmentGetPropertyIsCalledWithValueOfHomeTeamForNextFixture() throws Exception {

        when(nextFixture.getHomeTeam()).thenReturn("ManchesterUnited FC");

        service.retrieveWeatherForecastForFixture(nextFixture);
        verify(environment, times(1)).getProperty("ManchesterUnitedFC");

    }

    @Test
    public void verifyServiceMethodHasAnInstanceOfRestTemplate() throws Exception {

        when(nextFixture.getHomeTeam()).thenReturn("Newcastle United");
        when(environment.getProperty("NewcastleUnited")).thenReturn("newcastle upon Tyne,gb");

        service.retrieveWeatherForecastForFixture(nextFixture);
        verify(restTemplate, times(1)).getForObject(
                "http://api.openweathermap.org/data/2.5/weather?q=newcastle upon Tyne,gb&units=metric",
                WeatherFixture.class);
    }

    @Test
    public void givenHomeTeamIsEqualToStokeCityRestTemplateApiUrlCityShouldEqualStokeOnTrent() throws Exception {

        when(nextFixture.getHomeTeam()).thenReturn("Stoke City FC");
        when(environment.getProperty("StokeCityFC")).thenReturn("stoke-on-Trent,gb");

        service.retrieveWeatherForecastForFixture(nextFixture);
        verify(restTemplate, times(1)).getForObject(
                "http://api.openweathermap.org/data/2.5/weather?q=stoke-on-Trent,gb&units=metric",
                WeatherFixture.class);
    }

    @Test
    public void assertThatReturnedWeatherObjectHasTheCorrectLocationValueAsNameAttribute() throws Exception {

        when(nextFixture.getHomeTeam()).thenReturn("Stoke City FC");
        when(environment.getProperty("StokeCityFC")).thenReturn("stoke-on-Trent,gb");

        WeatherFixture weatherResult = mockWeatherFixture("Stoke-on-Trent", null, "light rain");

        when(restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?q=stoke-on-Trent,gb&units=metric",
                WeatherFixture.class)).thenReturn(weatherResult);

        WeatherFixture weather = service.retrieveWeatherForecastForFixture(nextFixture);
        assertEquals("WeatherFixture has location (name) value of Stoke-on-Trent", "Stoke-on-Trent", weather.getName());

    }

    @Test
    public void assertThatReturnedWeatherObjectHasValidWeatherType() throws Exception {

        when(nextFixture.getHomeTeam()).thenReturn("Everton FC");
        when(environment.getProperty("EvertonFC")).thenReturn("liverpool,gb");

        WeatherFixture weatherResult = mockWeatherFixture("Liverpool", "Mist", "mist");

        when(restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?q=liverpool,gb&units=metric",
                WeatherFixture.class)).thenReturn(weatherResult);

        WeatherFixture weather = service.retrieveWeatherForecastForFixture(nextFixture);
        assertEquals("WeatherFixture has Type Of Weather (weather.main) of Value Mist", "Mist",
                weather.getWeather().getMain());

    }

    @Test
    public void assertThatReturnedWeatherObjectHasValidWeatherTypeDescription() throws Exception {

        when(nextFixture.getHomeTeam()).thenReturn("Hull City FC");
        when(environment.getProperty("HullCityFC")).thenReturn("hull,gb");

        WeatherFixture weatherResult = mockWeatherFixture("Hull", "Rain", "light rain");

        when(restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?q=hull,gb&units=metric",
                WeatherFixture.class)).thenReturn(weatherResult);

        WeatherFixture weather = service.retrieveWeatherForecastForFixture(nextFixture);
        assertEquals("WeatherFixture has Type Of Weather Description (weather.description) of value light rain", "light rain",
                weather.getWeather().getDescription());

    }

    @Test
    public void assertThatReturnedWeatherObjectHasValidWeatherMainTemp() throws Exception {

        when(nextFixture.getHomeTeam()).thenReturn("Hull City FC");
        when(environment.getProperty("HullCityFC")).thenReturn("hull,gb");

        WeatherFixture weatherResult = mockWeatherFixture("Hull", "Rain", "light rain");

        when(restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?q=hull,gb&units=metric",
                WeatherFixture.class)).thenReturn(weatherResult);

        WeatherFixture weather = service.retrieveWeatherForecastForFixture(nextFixture);
        assertTrue("WeatherFixture has Temp (main.temp) of value 10.0", weather.getMain().getTemp() == 10D);

    }

    @Test
    public void assertThatReturnedWeatherObjectHasValidWeatherMainTempMax() throws Exception {

        when(nextFixture.getHomeTeam()).thenReturn("Hull City FC");
        when(environment.getProperty("HullCityFC")).thenReturn("hull,gb");

        WeatherFixture weatherResult = mockWeatherFixture("Hull", "Rain", "light rain");

        when(restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?q=hull,gb&units=metric",
                WeatherFixture.class)).thenReturn(weatherResult);

        WeatherFixture weather = service.retrieveWeatherForecastForFixture(nextFixture);
        assertTrue("WeatherFixture has Temp (main.temp_max) of value 20.0", weather.getMain().getTemp_max() == 20D);

    }

    @Test
    public void assertThatReturnedWeatherObjectHasValidWeatherMainTempMin() throws Exception {

        when(nextFixture.getHomeTeam()).thenReturn("Hull City FC");
        when(environment.getProperty("HullCityFC")).thenReturn("hull,gb");

        WeatherFixture weatherResult = mockWeatherFixture("Hull", "Rain", "light rain");

        when(restTemplate.getForObject("http://api.openweathermap.org/data/2.5/weather?q=hull,gb&units=metric",
                WeatherFixture.class)).thenReturn(weatherResult);

        WeatherFixture weather = service.retrieveWeatherForecastForFixture(nextFixture);
        assertTrue("WeatherFixture has Temp (main.temp_max) of value 0.0", weather.getMain().getTemp_min() == 0D);

    }


    private WeatherFixture mockWeatherFixture(String location, String typeOfWeather, String typeDesc) {
        WeatherFixture weatherResult = new WeatherFixture();
        weatherResult.setName(location);

        WeatherType typeSection = new WeatherType();
        typeSection.setMain(typeOfWeather);
        typeSection.setDescription(typeDesc);

        weatherResult.setWeather(typeSection);

        WeatherDetails mainSection = new WeatherDetails();
        mainSection.setTemp(10D);
        mainSection.setTemp_max(20D);
        mainSection.setTemp_min(0D);

        weatherResult.setMain(mainSection);

        return weatherResult;
    }

    private Fixture mockFixtureObject(String homeTeam) {
        Fixture nextFixture = new Fixture();
        nextFixture.setHomeTeam(homeTeam);

        return nextFixture;
    }

}
