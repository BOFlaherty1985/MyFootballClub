package uk.co.myfootballclub.persistence.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uk.co.myfootballclub.persistence.domain.TeamList;

import java.util.ArrayList;
import java.util.List;

/**
 * Team List Repository
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 10/12/2014
 * @project MyFootballClub
 */
@Repository
public interface TeamListRepository extends CrudRepository<TeamList, Long> {

    @Query("SELECT tL FROM TeamList tL WHERE (tL.leagueId) = :leagueId ORDER BY tL.teamDescription")
    public List<TeamList> retrieveTeamListByLeagueId(@Param("leagueId") int leagueId);

}
