package uk.co.myfootballclub.persistence.dao;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.co.myfootballclub.persistence.domain.TeamList;

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

    public List<TeamList> findByLeagueId(int leagueId, Sort sort);

}
