package nsk.cath.com.repo.minutes;

import nsk.cath.com.enums.Status;
import nsk.cath.com.model.minutes.Minutes;
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
public interface MinutesRepo  extends JpaRepository<Minutes,Long> {
    @Query("select a from Minutes a where a.id =:id")
    Minutes findByMinuteId(@Param("id")Long id);

    @Query("select a from Minutes a where a.meetingNumber =:meetingNumber")
    Minutes findByMeetingNumber(@Param("meetingNumber")String meetingNumber);

    @Query("select a from Minutes a where a.status =:status")
    List<Minutes> findByStatus(@Param("status")Status status);

    @Query("select a from Minutes a where a.topic =:topic")
    List<Minutes> findByTopic(@Param("topic") String topic);

    @Query("select a from Minutes a where a.meetingDate =:meetingDate")
    List<Minutes> findByMeetingDate(@Param("meetingDate") String meetingDate);

    @Query("select a from Minutes a where a.postedBy.id =:userId")
    Page<Minutes> findByPostedBy(@Param("userId") Long userId, Pageable pageable);

    @Query("select a from Minutes a where a.approvedBy.id =:userId")
    Page<Minutes> findByApprovedBy(@Param("userId") Long userId,Pageable pageable);

}
