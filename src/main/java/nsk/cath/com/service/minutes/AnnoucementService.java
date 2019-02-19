package nsk.cath.com.service.minutes;

import nsk.cath.com.enums.Status;
import nsk.cath.com.model.minutes.Announcement;
import nsk.cath.com.repo.minutes.AnnouncemntRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AnnoucementService {
    private AnnouncemntRepo announcemntRepo;

    @Autowired
    public void setAnnouncemntRepo(AnnouncemntRepo announcemntRepo) {
        this.announcemntRepo = announcemntRepo;
    }

    public Announcement findById(Long id)
    {
        return announcemntRepo.findByAnnouncementId(id);
    }
    public List<Announcement> findByStatus(Status status)
    {
        return announcemntRepo.findByStatus(status);
    }
    public List<Announcement> findByTopic(String topic)
    {
        return announcemntRepo.findByTopic(topic);
    }
    public List<Announcement> findByEventDate(Date eventDate)
    {
        return announcemntRepo.findByEventDate(eventDate);
    }
    public Page<Announcement> findByPostedBy(Long userId, Pageable pageable)
    {
        return announcemntRepo.findByPostedBy(userId,pageable);
    }
    public Page<Announcement> findByApprovedBy(Long userId,Pageable pageable)
    {
        return announcemntRepo.findByApprovedBy(userId,pageable);
    }
}
