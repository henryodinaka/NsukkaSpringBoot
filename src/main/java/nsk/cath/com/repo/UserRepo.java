package nsk.cath.com.repo;

import nsk.cath.com.enums.Status;
import nsk.cath.com.model.User;
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
public interface UserRepo extends JpaRepository<User, Long> {

    @Query("select u from User u")
    Page<User> getAllUsers(Pageable pageable);

    @Query("select u from User u where u.status = :status")
    Page<User> getAllUsersByStatus(@Param("status")Status status, Pageable pageable);

    @Query("select u from User u where u.parish.id = :parishId")
    Page<User> getAllUsersByParish(@Param("parishId")Long parishId, Pageable pageable);

    @Query("select u from User u where u.email = :email")
    User findUserByEmailAddress(@Param("email") String email);

    @Query(" select count(u.id) from User u where u.phoneNumber = :phoneNumber")
    User findByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query("select u from User u where u.email = :email and u.id <> :id")
    User findUserByEmailAddress(@Param("email") String email, @Param("id") Long id);

    @Query(" select count(u.id) from User u where u.email = :email")
    long countByEmailAddress(@Param("email") String email);

    @Query(" select count(u.id) from User u where u.email = :email and u.id <> :id")
    long countByEmailAddress(@Param("email") String email, @Param("id") Long id);

    @Query(" select count(u.id) from User u where u.phoneNumber = :phoneNumber")
    long countByPhoneNum(@Param("phoneNumber") String phoneNumber);

    @Query(" select count(u.id) from User u where u.phoneNumber = :phoneNumber and u.id <> :id")
    long countByPhoneNum(@Param("phoneNumber") String phoneNumber, @Param("id") Long id);

    @Query(" select count(u.id) from User u where u.parish.id = :parishId")
    long countByParish(@Param("parishId") Long parishId);

    @Query("select upper(u.email) from User u")
    List<String> getAllEmailAddress();

    @Query("select sum ( case when dn.diocese.id =:dioceseId then 1 else 0 end) as user_count from User u inner join u.parish p inner join p.deanery dn inner join dn.diocese dc")
    long countByDiocese(@Param("dioceseId") Long dioceseId);

    @Query("select sum ( case when p.deanery.id =:deaneryId then 1 else 0 end) as user_count from User u inner join u.parish p inner join p.deanery dn")
    long countByDeanery(@Param("deaneryId") Long deaneryId);
}
