package nsk.cath.com.service;

import nsk.cath.com.dto.AssignRoleRequest;
import nsk.cath.com.dto.UserDetail;
import nsk.cath.com.dto.UserRequest;
import nsk.cath.com.embaddable.Name;
import nsk.cath.com.enums.Errors;
import nsk.cath.com.enums.RoleName;
import nsk.cath.com.enums.Status;
import nsk.cath.com.errorHandler.NSKException;
import nsk.cath.com.model.User;
import nsk.cath.com.model.auth.Role;
import nsk.cath.com.model.contact.Contact;
import nsk.cath.com.model.contact.Lga;
import nsk.cath.com.repo.UserRepo;
import nsk.cath.com.repo.contact.ContactRepo;
import nsk.cath.com.service.auth.RoleService;
import nsk.cath.com.service.contact.LgaService;
import nsk.cath.com.utils.Validator;
import nsk.cath.com.utils.encryption.EncyptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

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
    @Autowired
    private LgaService lgaService;

    public User save(User user)
    {
        return userRepo.save(user);
    }
    public ResponseEntity<?> setup(UserRequest request,boolean isNigeria,boolean isUpdate) throws NSKException {
        userRequestValidator.validateUserRequest(request,isNigeria,isUpdate);

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
       return user;
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
    public  Page<User> getAllUsersByParish(Long id, Pageable pageable)
    {
        return userRepo.getAllUsersByParish(id,pageable);
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
    public long countUserByParishId(Long parishId) {
            return userRepo.countByParish(parishId);
    }
    public User assignRole(UserDetail userDetail , AssignRoleRequest roleRequest) throws NSKException {
        User operator = userRepo.getOne(userDetail.getUserId());
        if (operator ==null)
            throw new NSKException("Please login and try again","404","404");
        if (!RoleName.SEC.equals(operator.getRole().getName()))
            throw new NSKException("You are not authorized to assign role to users","401","401");

        return null;
    }
//    public long countUserByDeaneryId(Long deaneryId) {
//            return userRepo.countByDeanery(deaneryId);
//    }
//    public long countUserByDioceseId(Long dioceseId) {
//            return userRepo.countByDiocese(dioceseId);
//    }
//
//    public Page<User> getAllUsers(Status status,Pageable pageable) {
//        return userRepo.getAllUsers(pageable);
//    }
//
//
//    public List<User> getUsersByUserType(Long userType, boolean activated, RoleName roleName) {
//        return userRepo.getUsersByUserType(roleName, userType, activated);
//    }

    public Contact getContactDetails(UserRequest userReq) {
        Contact contact = new Contact();
        Lga lga = null;
        if (userReq.getContact().getLgaId() !=null && userReq.getContact().getLgaId() !=0)
        {
            lga= this.lgaService.get(userReq.getContact().getLgaId());
            contact.setLga(Optional.ofNullable(lga).orElse(null));
        }
        contact.setHouseAddress(Optional.ofNullable(userReq.getContact().getHouseAddress()).orElse(null));
        contact.setCity(Optional.ofNullable(userReq.getContact().getCity()).orElse(null));
        contact.setHomeAddress(userReq.getContact().isHomeAddress());
        return contact;
    }
}
