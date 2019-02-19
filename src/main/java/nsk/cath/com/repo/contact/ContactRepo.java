package nsk.cath.com.repo.contact;

import nsk.cath.com.model.contact.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface ContactRepo extends JpaRepository<Contact, Long>{

    @Query("select c from Contact c where c.user.id =:userId")
    List<Contact> findByUser(@Param("userId") Long userId);

    @Query("select c from Contact c where c.user.id =:userId and c.homeAddress =:homeAddress")
    Contact findByUserAndHomeAddress(@Param("userId") Long userId,@Param("homeAddress") boolean homeAddress);
}
