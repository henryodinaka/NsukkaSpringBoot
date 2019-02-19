package nsk.cath.com.repo.career;

import nsk.cath.com.enums.Qualification;
import nsk.cath.com.model.career.Education;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EducationRepo extends JpaRepository<Education,Long> {

    @Query("select e from Education e where e.school =:school")
    Page<Education> findBySchool(@Param("school")String school,Pageable pageable);

    @Query("select e from Education e where e.course =:course")
    Page<Education> findByCourse(@Param("course")String course,Pageable pageable);

    @Query("select e from Education e where e.user.id =:userId")
    List<Education> findByUser(@Param("userId") Long userId);

    @Query("select e from Education e where e.qualification=:qualification")
    Page<Education> findByQualification(@Param("qualification") Qualification qualification, Pageable pageable);

}
