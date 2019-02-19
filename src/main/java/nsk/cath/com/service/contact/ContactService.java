package nsk.cath.com.service.contact;

import nsk.cath.com.model.contact.Contact;
import nsk.cath.com.repo.contact.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private ContactRepo contactRepo;

    @Autowired
    public void setContactRepo(ContactRepo contactRepo) {
        this.contactRepo = contactRepo;
    }

    public List<Contact> findByUser(Long userId)
    {
        return contactRepo.findByUser(userId);
    }
    public Contact findByUserAndHomeAddress(Long userId,boolean homeAddress)
    {
        return contactRepo.findByUserAndHomeAddress(userId,homeAddress);
    }
}
