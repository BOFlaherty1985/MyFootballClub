package uk.co.myfootballclub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uk.co.myfootballclub.persistence.dao.TeamListRepository;
import uk.co.myfootballclub.persistence.domain.TeamList;
import uk.co.myfootballclub.service.interfaces.IDropdownService;

import java.util.List;

/**
 * Service to retrieve teams for drop down menu
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 10/12/2014
 * @project MyFootballClub
 */
@Service
public class TeamsDropdownService implements IDropdownService<TeamList> {

    @Autowired
    private TeamListRepository teamListRepository;

    @Override
    public List<TeamList> retrieveDropdownListItems(int retrieveById) {
        return teamListRepository.findByLeagueId(retrieveById, new Sort(Sort.Direction.ASC, "teamDescription"));
    }
}


