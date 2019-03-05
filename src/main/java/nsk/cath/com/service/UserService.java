package nsk.cath.com.service;

import nsk.cath.com.dto.UserRequest;
import nsk.cath.com.embaddable.Name;
import nsk.cath.com.enums.Errors;
import nsk.cath.com.enums.Status;
import nsk.cath.com.errorHandler.NSKException;
import nsk.cath.com.model.User;
import nsk.cath.com.model.auth.Role;
import nsk.cath.com.repo.UserRepo;
import nsk.cath.com.repo.contact.ContactRepo;
import nsk.cath.com.service.auth.RoleService;
import nsk.cath.com.utils.Validator;
import nsk.cath.com.utils.encryption.EncyptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Value("${encryption.salt}")
    private String salt;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ContactRepo contactRepo;
    @Autowired
    private RoleService roleService;
    @Autowired
    private Validator<UserRequest> userRequestValidator;

    public User save(User user)
    {
        return userRepo.save(user);
    }
    public ResponseEntity<?> setup(UserRequest request,boolean isNigeria,boolean isUpdate) throws NSKException {
        userRequestValidator.validateForm(request,isNigeria);

        userRequestValidator.validate(request, isUpdate, request.getId());
        User user = new User();

        if (isUpdate) {
             user = findById(request.getId());
            if (user == null) {
                throw new NSKException("The user to be updated is null", "404", "404");
            }
            user = generate(user, request, isUpdate);
            user = save(user);
//            //send a mail to all the authorizers for awareness
//            sendAwarenessMail(existingUser, null, isUpdate, fromEmail);
            return ResponseEntity.ok(user);
        } else {
            user = generate(user,request, isUpdate);
            user = save(user);

//            //send a mail to all the authorizers for awareness
//            sendAwarenessMail(setUpUser, password, isUpdate, fromEmail);

            return ResponseEntity.ok(user);
        }
    }
    public User generate( User user,UserRequest request,boolean isUpdate) throws NSKException {
        user.setDateOfBirth(request.getDateOfBirth());
        user.setEmail(request.getEmail());
        user.setGender(request.getGender());
        user.setName(new Name(request.getNameRequest().getFirstName(),request.getNameRequest().getMiddleName(),request.getNameRequest().getLastName()));
        user.setPassword(EncyptionUtil.doSHA512Encryption(request.getPassword(), salt));
        user.setPhoneNumber(request.getPhoneNumber());
        user.setStatus(Status.INACTIVE);
        user.setTitle(request.getTitle());
        Role newUserRole = roleService.getById(request.getRole());
        if (newUserRole !=null)
            user.setRole(newUserRole);
        else throw new NSKException(Errors.UNKNOWN_ROLE_ID.getValue().replace("{}",String.valueOf(request.getRole())),"404","404");
       return null;
    }
    public User findById (Long id)
    {
        return userRepo.getOne(id);
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
    public List<String> getAllEmailAddress()
    {
        return userRepo.getAllEmailAddress();
    }
}
