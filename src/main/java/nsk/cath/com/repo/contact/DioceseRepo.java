package nsk.cath.com.repo.contact;

import nsk.cath.com.model.contact.Diocese;
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
public interface DioceseRepo extends JpaRepository<Diocese,Long>{
    @Query("select d from Diocese d where d.name =:name")
    Diocese findAllByName(@Param("name")String name);

    @Query("select d from Diocese d where d.id =:id")
    Diocese findAllById(@Param("id")String id);

    @Query("select d from Diocese d where d.state.id =:stateId")
    Page<Diocese> findAllByState(@Param("stateId")String stateId, Pageable pageable);

    @Query("select d from Diocese d where d.state.id =:stateId")
    List<Diocese> findAllByState(@Param("stateId")String stateId);
}
