package nsk.cath.com.repo.contact;

import nsk.cath.com.model.contact.Branch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepo extends JpaRepository<Branch,Long> {
    @Override
    Page<Branch> findAll(Pageable pageable);

    @Query("select b from Branch b where b.state.id =:stateId")
    Page<Branch> findAllByState(@Param("stateId") Long stateId, Pageable pageable);
    @Query("select b from Branch b where b.state.id =:stateId")
    List<Branch> findAllByState(@Param("stateId")Long stateId);
//    @Query("select b from Branch b where b.country.id =:countryId")
//    Page<Branch> findAllByCountry(@Param("countryId")Long countryId,Pageable pageable);
}
