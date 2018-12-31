package nsk.cath.com.nsukka.repo.minutes;

import nsk.cath.com.nsukka.enums.Status;
import nsk.cath.com.nsukka.model.minutes.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface AccountRepo extends JpaRepository<Account,Long> {
    @Query("select a from Account a where a.id =:id")
    Account findById(@Param("id")Long id);

    @Query("select a from Account a where a.meetingNumber =:meetingNumber")
    Account findByMeetingNumber(@Param("meetingNumber")String id);


    @Query("select a from Account a where a.status =:status")
    List<Account> findByStatus(@Param("status")Status status);

    @Query("select a from Account a where a.topic =:topic")
    List<Account> findByTopic(@Param("topic") String topic);

    @Query("select a from Account a where a.meetingDate =:meetingDate")
    List<Account> findByMeetingDate(@Param("meetingDate") String meetingDate);

    @Query("select a from Account a where a.postedBy.id =:userId")
    Page<Account> findByPostedBy(@Param("userId") Long userId, Pageable pageable);

    @Query("select a from Account a where a.approvedBy.id =:userId")
    Page<Account> findByApprovedBy(@Param("userId") Long userId,Pageable pageable);

}
