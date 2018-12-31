package nsk.cath.com.nsukka.repo;

import nsk.cath.com.nsukka.enums.Status;
import nsk.cath.com.nsukka.model.User;
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

    @Query("select u from User u where u.emailAddress = :emailAddress")
    User findUserByEmailAddress(@Param("emailAddress") String emailAddress);

    @Query(" select count(u.id) from User u where u.phoneNumber = :phoneNumber")
    User findByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query("select u from User u where u.emailAddress = :emailAddress and u.id <> :id")
    User findUserByEmailAddressAndNotId(@Param("emailAddress") String emailAddress, @Param("id") Long id);

    @Query(" select count(u.id) from User u where u.emailAddress = :emailAddress")
    long countByEmailAddress(@Param("emailAddress") String emailAddress);

    @Query(" select count(u.id) from User u where u.emailAddress = :emailAddress and u.id <> :id")
    long countByEmailAddressAndNotId(@Param("emailAddress") String emailAddress, @Param("id") Long id);

    @Query(" select count(u.id) from User u where u.phoneNumber = :phoneNumber")
    long countByPhoneNum(@Param("phoneNumber") String phoneNumber);

    @Query(" select count(u.id) from User u where u.phoneNumber = :phoneNumber and u.id <> :id")
    long countByPhoneNumAndNotId(@Param("phoneNumber") String phoneNumber, @Param("id") Long id);

    @Query("select upper(u.emailAddress) from User u")
    List<String> getAllEmailAddress();
}
