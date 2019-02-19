package nsk.cath.com.repo.career;

import nsk.cath.com.model.career.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface SkillRepo extends JpaRepository<Skill,Long> {
    @Query("select s from  Skill s where s.user.id =:userId")
    List<Skill> findByUser(@Param("userId") Long userId);
}
