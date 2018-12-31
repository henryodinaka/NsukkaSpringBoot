package nsk.cath.com.nsukka.repo.contact;

import nsk.cath.com.nsukka.model.contact.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface StateRepo extends JpaRepository<State, Long> {

    @Query("select count(s.id) from State s where s.name = :name and s.country.name =:countryName")
    int getCountByName(@Param("name") String name,@Param("countryName") String countryName);

    @Query("select count(s.id) from State s where s.name = :name and s.id <> :id")
    int getCountByNameAndNotId(@Param("name") String name, @Param("id") Long id);

    @Query("select s from State s where s.country.id = :id")
    List<State> getStatesByCountry(@Param("id") Long id);

    @Query("select s from State s where s.country.id = :id")
    Page<State> getStatesByCountry(@Param("id") Long id, Pageable pageable);
}