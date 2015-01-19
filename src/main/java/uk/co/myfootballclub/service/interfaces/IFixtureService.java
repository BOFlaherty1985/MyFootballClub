package uk.co.myfootballclub.service.interfaces;

import uk.co.myfootballclub.exception.InvalidFixtureTypeException;
import uk.co.myfootballclub.model.Fixture;

import java.util.List;

/**
 * Fixtures Interface
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 13/01/2015
 * @project MyFootballClub
 */
public interface IFixtureService extends IRetrieveDataByInt<Fixture> {

    List<Fixture> getFixturesByDays(int teamId, String fixtureType, int numberOfDays) throws InvalidFixtureTypeException;

    String getNextOpponentForTeam(Fixture nextFixture, String myFootballClub);

}
