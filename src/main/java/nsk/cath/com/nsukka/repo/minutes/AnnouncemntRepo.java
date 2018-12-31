package nsk.cath.com.nsukka.repo.minutes;

import nsk.cath.com.nsukka.enums.Status;
import nsk.cath.com.nsukka.model.minutes.Announcement;
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
public interface AnnouncemntRepo extends JpaRepository<Announcement,Long> {
    @Query("select a from Announcement a where a.id =:id")
    Announcement findById(@Param("id")Long id);

    @Query("select a from Announcement a where a.status =:status")
    List<Announcement> findByStatus(@Param("status")Status status);

    @Query("select a from Announcement a where a.topic =:topic")
    List<Announcement> findByTopic(@Param("topic") String topic);

    @Query("select a from Announcement a where a.eventDate =:eventDate")
    List<Announcement> findByEventDate(@Param("eventDate") String eventDate);

    @Query("select a from Announcement a where a.postedBy.id =:userId")
    Page<Announcement> findByPostedBy(@Param("userId") Long userId, Pageable pageable);

    @Query("select a from Announcement a where a.approvedBy.id =:userId")
    Page<Announcement> findByApprovedBy(@Param("userId") Long userId,Pageable pageable);
}
