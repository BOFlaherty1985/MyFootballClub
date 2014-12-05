package uk.co.myfootballclub.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import uk.co.myfootballclub.config.TestConfig;
import uk.co.myfootballclub.config.WebInitializer;
import uk.co.myfootballclub.exception.ClubNameIsNotValidException;
import uk.co.myfootballclub.model.ClubDetails;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Processes details from JSON files for a Football Club.
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 05/12/2014
 * @project MyFootballClub
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {TestConfig.class, WebInitializer.class})
@WebAppConfiguration
public class ClubDetailsServiceTest {

    @InjectMocks
    private ClubDetailsService clubDetailsService;

    @Mock
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private InputStream inputStream;

    @Test
    public void retrieveClubDetails_methodReturn_notNull() throws Exception {
        assertNotNull("retrieveClubDetails() is not null." + clubDetailsService.retrieveClubDetails("clubName"));
    }

    @Test
    public void retrieveClubDetails_typeOfObjectReturned_instanceOfClubDetails() throws Exception {

        ClubDetailsService clubDetailsObj = Mockito.spy(new ClubDetailsService());
        when(clubDetailsObj.retrieveJsonFileForClub("clubName")).thenReturn(inputStream);

        when(objectMapper.readValue(inputStream, ClubDetails.class)).thenReturn(new ClubDetails());

        Object clubDetails = clubDetailsService.retrieveClubDetails("clubName");
        assertTrue("Object is instanceOf ClubDetails.", clubDetails instanceof ClubDetails);

    }

    @Test
    public void retrieveClubDetails_hasObjectMapperBeenCalled_Once() throws Exception {

        ClubDetailsService clubDetailsObj = Mockito.spy(new ClubDetailsService());
        when(clubDetailsObj.retrieveJsonFileForClub("clubName")).thenReturn(inputStream);

        clubDetailsService.retrieveClubDetails("clubName");
        verify(objectMapper, times(1)).readValue(inputStream, ClubDetails.class);

    }

    @Test
    public void retrieveClubDetails_ClubDetailsNickName_EqualsTheTesters() throws Exception {

        ClubDetailsService clubDetailsObj = Mockito.spy(new ClubDetailsService());
        when(clubDetailsObj.retrieveJsonFileForClub("clubName")).thenReturn(inputStream);

        ClubDetails result = new ClubDetails();
        result.setClubNickname("The Testers");

        when(objectMapper.readValue(inputStream, ClubDetails.class)).thenReturn(result);

        ClubDetails clubDetails = clubDetailsService.retrieveClubDetails("clubName");
        assertNotNull("ClubDetails getClubNickname() is not null", clubDetails.getClubNickname());
        assertEquals("CluBDetails Nickname is equal to The Testers", result.getClubNickname(),
                clubDetails.getClubNickname());

    }

    @Test
    public void retrieveClubDetails_ClubDetailsFounded_Equals1900() throws Exception {

        ClubDetailsService clubDetailsObj = Mockito.spy(new ClubDetailsService());
        when(clubDetailsObj.retrieveJsonFileForClub("clubName")).thenReturn(inputStream);

        ClubDetails result = new ClubDetails();
        result.setClubFounded("1900");

        when(objectMapper.readValue(inputStream, ClubDetails.class)).thenReturn(result);

        ClubDetails clubDetails = clubDetailsService.retrieveClubDetails("clubName");
        assertNotNull("ClubDetails getClubFounded() is not null", clubDetails.getClubFounded());
        assertEquals("ClubDetails getClubFounded is equal to 1900", result.getClubFounded(), clubDetails.getClubFounded());

    }


    @Test
    public void retrieveClubDetails_ClubDetailsStadium_EqualsLondonStadium() throws Exception {

        ClubDetailsService clubDetailsObj = Mockito.spy(new ClubDetailsService());
        when(clubDetailsObj.retrieveJsonFileForClub("clubName")).thenReturn(inputStream);

        ClubDetails result = new ClubDetails();
        result.setClubStadium("London Stadium");

        when(objectMapper.readValue(inputStream, ClubDetails.class)).thenReturn(result);

        ClubDetails clubDetails = clubDetailsService.retrieveClubDetails("clubName");
        assertNotNull("ClubDetails getClubStadium() is not null", clubDetails.getClubStadium());
        assertEquals("ClubDetails getClubStadium() is equal to London Stadium", result.getClubStadium(),
                clubDetails.getClubStadium());

    }

    @Test
    public void retrieveClubDetails_ClubDetailsStadiumCapacity_Equals20000() throws Exception {

        ClubDetailsService clubDetailsObj = Mockito.spy(new ClubDetailsService());
        when(clubDetailsObj.retrieveJsonFileForClub("clubName")).thenReturn(inputStream);

        ClubDetails result = new ClubDetails();
        result.setClubCapacity("20,000");

        when(objectMapper.readValue(inputStream, ClubDetails.class)).thenReturn(result);

        ClubDetails clubDetails = clubDetailsService.retrieveClubDetails("clubName");
        assertNotNull("ClubDetails getClubCapacity() is not null", clubDetails.getClubCapacity());
        assertEquals("ClubDetails getClubCapacity() is equal to 20,000", result.getClubCapacity(),
                clubDetails.getClubCapacity());

    }

    @Test
    public void retrieveClubDetails_ClubDetailClubWebsite_EqualsGivenString() throws Exception {

        ClubDetailsService clubDetailsObj = Mockito.spy(new ClubDetailsService());
        when(clubDetailsObj.retrieveJsonFileForClub("clubName")).thenReturn(inputStream);

        ClubDetails result = new ClubDetails();
        result.setClubWebsite("http://website.com");

        when(objectMapper.readValue(inputStream, ClubDetails.class)).thenReturn(result);

        ClubDetails clubDetails = clubDetailsService.retrieveClubDetails("clubName");
        assertNotNull("ClubDetails getClubWebsite() is not null", clubDetails.getClubWebsite());
        assertEquals("ClubDetails getClubWebsite() is equal to http://website.com", result.getClubWebsite(),
                clubDetails.getClubWebsite());

    }

    @Test
    public void retrieveClubDetails_ClubDetailFacebook_EqualsGivenString() throws Exception {

        ClubDetailsService clubDetailsObj = Mockito.spy(new ClubDetailsService());
        when(clubDetailsObj.retrieveJsonFileForClub("clubName")).thenReturn(inputStream);

        ClubDetails result = new ClubDetails();
        result.setClubFacebook("http://facebook.com/myClub");

        when(objectMapper.readValue(inputStream, ClubDetails.class)).thenReturn(result);

        ClubDetails clubDetails = clubDetailsService.retrieveClubDetails("clubName");
        assertNotNull("ClubDetails getClubWebsite() is not null", clubDetails.getClubFacebook());
        assertEquals("ClubDetails getClubWebsite() is equal to http://website.com", result.getClubFacebook(),
                clubDetails.getClubFacebook());

    }

    @Test
    public void throwExceptionWhenClubNameParameterIsInvalidAndJsonFileIsNotFound() {

        try {
            clubDetailsService.retrieveClubDetails(null);
            fail("Exception thrown when clubName parameter is invalid.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void throwClubNameIsNotValidExceptionWhenClubNameStringParameterIsNotValid() throws IOException {

        when(objectMapper.readValue(inputStream, ClubDetails.class)).thenThrow(new IOException());

        try {
            clubDetailsService.retrieveClubDetails("abcdefg");
            fail("Exception thrown when clubName parameter is invalid.");
        } catch (ClubNameIsNotValidException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void throwClubNameIsNotValidExceptionAndExceptionHasValidErrorMessage() throws IOException {

        when(objectMapper.readValue(inputStream, ClubDetails.class)).thenThrow(new IOException());

        try {
            clubDetailsService.retrieveClubDetails("abcdefg");
            fail("Exception thrown when clubName parameter is invalid.");
        } catch (ClubNameIsNotValidException e) {
            assertEquals("ClubNameIsNotvalidException error message is equal to clubName Parameter Is Invalid.",
                    "ClubName Parameter Is Invalid.", e.getMessage());
        }

    }

}
