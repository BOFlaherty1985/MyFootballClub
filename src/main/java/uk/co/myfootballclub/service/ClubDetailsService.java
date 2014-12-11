package uk.co.myfootballclub.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.myfootballclub.exception.ClubNameIsNotValidException;
import uk.co.myfootballclub.model.ClubDetails;

import java.io.IOException;
import java.io.InputStream;

import static java.lang.String.format;

/**
 * Club Details Service - retrieves json data a given football club
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 05/12/2014
 * @project MyFootballClub
 */
@Component
public class ClubDetailsService {

    private String CLUB_DETAILS_PATH = "teamdata/premierleague/";

    @Autowired
    private ObjectMapper objectMapper;

    public ClubDetails retrieveClubDetails(String clubName) throws IOException, ClubNameIsNotValidException {

        ClubDetails clubDetails;

        try {
            clubDetails = objectMapper.readValue(retrieveJsonFileForClub(clubName), ClubDetails.class);
        } catch (IOException e) {
            throw new ClubNameIsNotValidException("ClubName Parameter Is Invalid.");
        }

        return clubDetails;
    }

    protected InputStream retrieveJsonFileForClub(String clubName) {
        return getClass().getClassLoader().getResourceAsStream(format("%s%s.json", CLUB_DETAILS_PATH,
                clubName.replaceAll("//s", "").toLowerCase()));
    }

}
