package uk.co.myfootballclub.persistence.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.co.myfootballclub.persistence.domain.User;

/**
 * Spring Data User Repository Interface
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 08/12/2014
 * @project MyFootballClub
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {}
