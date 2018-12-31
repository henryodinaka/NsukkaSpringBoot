package nsk.cath.com.nsukka.repo.contact;

import nsk.cath.com.nsukka.model.contact.Parish;
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
public interface ParishRepo extends JpaRepository<Parish,Long>{
    @Query("select p from Parish p where p.name =:name")
    Parish findByName(@Param("name")String name);

    @Query("select p from Parish p where p.id =:id")
    Parish findById(@Param("id")String id);

    @Query("select p from Parish p where p.deanery.id =:deaneryId")
    Page<Parish> findAllByDeanery(@Param("deaneryId")String deaneryId, Pageable pageable);

    @Query("select d from Parish d where d.deanery.id =:deaneryId")
    List<Parish> findAllByDeanery(@Param("deaneryId")String deaneryId);
}
