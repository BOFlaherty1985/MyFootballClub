package uk.co.myfootballclub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.myfootballclub.persistence.dao.TeamListRepository;
import uk.co.myfootballclub.persistence.domain.TeamList;

import java.util.List;

/**
 * Service to retrieve teams for drop down menu
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 10/12/2014
 * @project MyFootballClub
 */
@Service
public class TeamsListService {

    @Autowired
    private TeamListRepository teamListRepository;

    public List<TeamList> retrieveListOfTeams(int leagueId) {
        return teamListRepository.retrieveTeamListByLeagueId(leagueId);
    }
}


