package uk.co.myfootballclub.service.interfaces;

import java.util.List;

/**
 * Dropdown Interface
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 13/01/2015
 * @project MyFootballClub
 */
public interface IDropdownService<T> {

    List<T> retrieveDropdownListItems(int dropDownType);

}
