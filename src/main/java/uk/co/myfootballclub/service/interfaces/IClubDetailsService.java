package uk.co.myfootballclub.service.interfaces;

import uk.co.myfootballclub.model.ClubDetails;

import java.io.InputStream;

/**
 * Club Details Service
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 13/01/2015
 * @project MyFootballClub
 */
public interface IClubDetailsService extends IRetrieveDataByString<ClubDetails> {

    InputStream retrieveJsonFileForClub(String clubName);

}
