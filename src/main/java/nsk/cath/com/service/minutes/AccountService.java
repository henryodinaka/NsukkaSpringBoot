package nsk.cath.com.service.minutes;

import nsk.cath.com.enums.Status;
import nsk.cath.com.model.minutes.Account;
import nsk.cath.com.repo.minutes.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AccountService {
    private AccountRepo accountRepo;

    @Autowired
    public void setAccountRepo(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }
    public Account findById(Long id)
    {
        return accountRepo.findByAccountId(id);
    }
    public Account findByMeetingNumber(String id)
    {
        return accountRepo.findByMeetingNumber(id);
    }
    public List<Account> findByStatus(Status status)
    {
        return accountRepo.findByStatus(status);
    }
    public List<Account> findByTopic(String topic)
    {
        return accountRepo.findByTopic(topic);
    }
    public List<Account> findByMeetingDate(Date meetingDate)
    {
        return accountRepo.findByMeetingDate(meetingDate);
    }
    public Page<Account> findByPostedBy(Long userId, Pageable pageable)
    {
        return accountRepo.findByPostedBy(userId,pageable);
    }
    public Page<Account> findByApprovedBy(Long userId,Pageable pageable)
    {
        return accountRepo.findByApprovedBy(userId,pageable);
    }
}
