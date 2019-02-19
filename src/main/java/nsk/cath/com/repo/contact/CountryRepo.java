package nsk.cath.com.repo.contact;

import nsk.cath.com.enums.Continent;
import nsk.cath.com.model.contact.Country;
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
public interface CountryRepo extends JpaRepository<Country, Long> {

    @Query("select c from Country c")
    List<Country> findAllCountries();

    @Query("select c from Country c where c.continent =:continent")
    List<Country> getAllByContinent(@Param("continent")Continent continent);

    @Query("select c from Country c where c.continent =:continent")
    Page<Country> getAllByContinent(@Param("continent")Continent continent, Pageable pageable);

}