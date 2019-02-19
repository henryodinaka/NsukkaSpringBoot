package nsk.cath.com.service;

import nsk.cath.com.enums.Status;
import nsk.cath.com.model.User;
import nsk.cath.com.model.contact.Contact;
import nsk.cath.com.repo.UserRepo;
import nsk.cath.com.repo.contact.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepo userRepo;
    private ContactRepo contactRepo;

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Page<User> getAllUsers(Pageable pageable)
    {
        return userRepo.getAllUsers(pageable);
    }
    public  Page<User> getAllUsersByStatus(Status status, Pageable pageable)
    {
        return userRepo.getAllUsersByStatus(status,pageable);
    }
    public User findUserByEmailAddress(String emailAddress)
    {
        return userRepo.findUserByEmailAddress(emailAddress);
    }
    public User findByPhoneNumber(String phoneNumber)
    {
        return userRepo.findByPhoneNumber(phoneNumber);
    }
    public User findUserByEmailAddressAndNotId(String emailAddress, Long id)
    {
        return userRepo.findUserByEmailAddressAndNotId(emailAddress,id);
    }
    public long countByEmailAddress(String emailAddress)
    {
        return userRepo.countByEmailAddress(emailAddress);
    }
    public long countByEmailAddressAndNotId(String emailAddress,Long id)
    {
        return userRepo.countByEmailAddressAndNotId(emailAddress,id);
    }
    public  long countByPhoneNum(String phoneNumber)
    {
        return userRepo.countByPhoneNum(phoneNumber);
    }
    public  long countByPhoneNum(String phoneNumber,Long id)
    {
        return userRepo.countByPhoneNumAndNotId(phoneNumber,id);
    }
    public List<String> getAllEmailAddress()
    {
        return userRepo.getAllEmailAddress();
    }
}
