package nsk.cath.com.nsukka.repo.career;

import nsk.cath.com.nsukka.model.career.Experience;
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
public interface ExperienceRepo extends JpaRepository<Experience,Long>{
    @Query("select e from ExperienceRepo e where e.user.id =:userId")
    List<Experience> findByUser(@Param("userId") Long userId);

    @Query("select e from Experience e where e.jobTitle =:jobTitle")
    Page<Experience> findByJobTitle(@Param("jobTitle") String jobTitle, Pageable pageable);

    @Query("select e from Experience e where e.company =:company")
    Page<Experience> findByCompany(@Param("company") String company, Pageable pageable);

}
