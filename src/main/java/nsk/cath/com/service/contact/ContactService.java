package nsk.cath.com.service.contact;

import nsk.cath.com.dto.ContactRequest;
import nsk.cath.com.dto.UserDetail;
import nsk.cath.com.enums.Errors;
import nsk.cath.com.errorHandler.NSKException;
import nsk.cath.com.model.User;
import nsk.cath.com.model.contact.Contact;
import nsk.cath.com.model.contact.Lga;
import nsk.cath.com.repo.contact.ContactRepo;
import nsk.cath.com.service.UserService;
import nsk.cath.com.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private ContactRepo contactRepo;
    @Autowired
    private LgaService lgaService;
    @Autowired
    private UserService userService;
    @Autowired
    private Validator<ContactRequest> contactRequestValidator;

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
    public ResponseEntity<?> setUp(ContactRequest request, UserDetail userDetail, boolean isUpdate) throws NSKException {
        User user = userService.findById(userDetail.getUserId());
        if (user==null)
            throw new NSKException("Please login and try again","401","401");
        Contact contact = new Contact();
        contactRequestValidator.validateContactRequest(request);
        if (isUpdate) {
            contact = findByUserAndHomeAddress(request.getId(), request.isHomeAddress());
            if (contact == null) {
                throw new NSKException("The user to be updated is null", "404", "404");
            }
            contact = generate(contact, null, request, isUpdate,request.isHomeAddress());
            contact = contactRepo.save(contact);
            return ResponseEntity.ok(contact);
        } else {
            contact = generate(contact, user,request, isUpdate,request.isHomeAddress());
            contact =contactRepo.save(contact);
            return ResponseEntity.ok(contact);

        }
    }
    public Contact generate( Contact contact,User user,ContactRequest request,boolean isUpdate,boolean isHomeAddress) throws NSKException {
        Lga lga =null;
        if (request.getLgaId() !=null) {
            lga = lgaService.get(request.getLgaId());
            if (lga == null) {
                throw new NSKException("LGA not found", "404", "404");
            }
            contact.setLga(lga);
        }
        contact.setHouseAddress(request.getHouseAddress());
        contact.setCity(request.getCity());
        contact.setHomeAddress(isHomeAddress);
       if (!isUpdate)
           contact.setUser(user);

        return contact;
    }

    public Page<Contact> findByCity(String city, Pageable pageable)
    {
     return contactRepo.findByCity(city,pageable);
    }
    public Page<Contact> findByHomeAddress(boolean isHomeAddress,Pageable pageable)
    {
        return findByHomeAddress(isHomeAddress,pageable);
    }
    public Page<Contact> findByHouseAddress(String houseAddress, Pageable pageable)
    {
        return contactRepo.findByHouseAddress(houseAddress,pageable);
    }
}
