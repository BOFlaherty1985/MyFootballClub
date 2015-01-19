package uk.co.myfootballclub.service.interfaces;

/**
 * Description Here
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 12/01/2015
 * @project MyFootballClub
 */
public interface IRetrieveDataByString<T> {

    T retrieveDataByString(String input) throws Exception;

}
