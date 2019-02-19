package nsk.cath.com.service.minutes;

import nsk.cath.com.enums.Status;
import nsk.cath.com.model.minutes.Minutes;
import nsk.cath.com.repo.minutes.MinutesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MinutesService {
    private MinutesRepo minutesRepo;

    @Autowired
    public void setMinutesRepo(MinutesRepo minutesRepo) {
        this.minutesRepo = minutesRepo;
    }

    public Minutes findById(Long id)
    {
        return minutesRepo.findByMinuteId(id);
    }
    public Minutes findByMeetingNumberString (String meetingNumber)
    {
        return minutesRepo.findByMeetingNumber(meetingNumber);
    }
    public List<Minutes> findByStatus(Status status)
    {
        return minutesRepo.findByStatus(status);
    }
    public List<Minutes> findByTopic(String topic)
    {
        return minutesRepo.findByTopic(topic);
    }
    public List<Minutes> findByMeetingDate(String meetingDate)
    {
        return minutesRepo.findByMeetingDate(meetingDate);
    }
    public Page<Minutes> findByPostedBy(Long userId, Pageable pageable)
    {
        return minutesRepo.findByPostedBy(userId,pageable);
    }
    public Page<Minutes> findByApprovedBy(Long userId,Pageable pageable)
    {
        return minutesRepo.findByApprovedBy(userId,pageable);
    }
}
