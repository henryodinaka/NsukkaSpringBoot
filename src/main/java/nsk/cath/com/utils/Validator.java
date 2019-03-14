package nsk.cath.com.utils;

import nsk.cath.com.dto.ContactRequest;
import nsk.cath.com.dto.UserRequest;
import nsk.cath.com.errorHandler.ErrorDetails;
import nsk.cath.com.errorHandler.NSKException;
import nsk.cath.com.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class Validator<T> {
    FormValidation formValidation;
    private UserRepo userRepo;

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Autowired
    public void setFormValidation(FormValidation formValidation) {
        this.formValidation = formValidation;
    }

    private static final Logger logger = LoggerFactory.getLogger(Validator.class);

    public String validate(T target) throws NSKException {
        logger.info("Doing validation...");
        UserRequest mandateReq = (UserRequest) target;


        return null;
    }
//    public void validateName(T request) throws NSKException{
//        AccountResponse response = null;
//        response = AccountValidationService.request(new AccountRequest(request.getBankCode(),request.getAccountNumber()));
//        Optional<String> accName = Optional.ofNullable(response.getAccountName());
//        if (!accName.isPresent())
//        {
//            throw new NSKException("Account number is not valid","400","400");
//        }
//        return response;
//    }
//    public EmandateResponse validateUserRequest(EmandateRequest request)
//    {
//
//        String e1 = formValidation.validateBankInformation(request.getAccountNumber(), request.getBankCode());
//        String e2 = formValidation.validateContactDetails(request.getPhoneNumber(), request.getEmailAddress(), request.getPayerName(), request.getPayerAddress());
//        String e3 = formValidation.validateRate(request.isFixedAmountMandate(), request.getAmount(), request.getFrequency(),request.getNarration(), request.getProductId());
//        String e4 = formValidation.validateRequestCodes(request.getChannelCode(), request.getSubscriberCode(), request.getSubscriberPassCode(), request.isFixedAmountMandate());
//        List<String> errors = Arrays.asList(e1,e2,e3,e4);
//        if (errors.isEmpty())
//            return null;
//        else
//            return new EmandateResponse(EmandateResponseCode.INVALID_REQUEST.getCode(),null,errors.toString());
//    }

    public void validateUserRequest(T  req, boolean isNigeria, boolean isUpdate) throws NSKException {
        Optional<String> e1 =null;
        Optional<String> e2 =null;
        Optional<String> e3;
        Optional<String> e4;
        if (req instanceof UserRequest)
        {
            UserRequest request  = ((UserRequest) req);
            e1 = Optional.ofNullable(formValidation.validateDemography(request));
            validate(request, isUpdate, request.getId());
        }
//            e3 = Optional.ofNullable(formValidation.validateRate(request.isFixedAmountMandate(), request.getAmount(), request.getFrequency(),request.getNarration(), request.getProductId()));
//            e4 = Optional.ofNullable(formValidation.validateRequestCodes(request.getChannelCode(), request.getSubscriberCode(), request.getSubscriberPassCode(), request.isFixedAmountMandate()));
//        List<String> errors = Arrays.asList(
//                Optional.ofNullable(e1),
//                Optional.ofNullable(e2),
//                Optional.ofNullable(e3),
//                Optional.ofNullable(e4));
//        List<String> errors = new ArrayList<>();
//        e1.ifPresent(e->errors.add(e1.get()));
//        e2.ifPresent(e->errors.add(e2.get()));
//        e3.ifPresent(e->errors.add(e3.get()));
//        e4.ifPresent(e->errors.add(e4.get()));

//        String s1 = e1.isPresent() ? e1.get() : null;

        List<String> errors = new ArrayList<>();


        if (e1.isPresent()) {
            errors.add(e1.get());
        }
//        if (e3.isPresent()) {
//            errors.add(e3.get());
//        }
//        if (e4.isPresent()) {
//            errors.add(e4.get());
//        }
        if (errors.isEmpty())
            throw new NSKException(errors.toString(),"400","400");

    }

    public void validateContactRequest(ContactRequest contactRequest) throws NSKException {
        Optional<String> e2;
        e2 = Optional.ofNullable(formValidation.validateContactDetails(contactRequest));
        List<String> errors = new ArrayList<>();
        if (e2.isPresent()) {
            errors.add(e2.get());
        }
        if (errors.isEmpty())

            throw new NSKException(errors.toString(),"400","400");
    }

    public void validate(UserRequest request, boolean isUpdate, Long id) throws NSKException {

        long userCountEmail = 0;
        long userCountPhone = 0;
        if (isUpdate) {
            if (id == null)

            {
                throw new NSKException("User id was not provided!", "404", "404");
            }

            userCountEmail = userRepo.countByEmailAddress(request.getEmail(), id);
            userCountPhone = userRepo.countByPhoneNum(request.getPhoneNumber(), id);
        } else {

            //validate the emailAddress
            if (request.getEmail() == null || request.getEmail().isEmpty()) {
                throw new NSKException("emailAddress is invalid!", "404", "404");
            }
            userCountEmail = userRepo.countByEmailAddress(request.getEmail());
            userCountPhone = userRepo.countByPhoneNum(request.getPhoneNumber());
        }

        if (userCountEmail > 0) {
            throw new NSKException("emailAddress address '" + request.getEmail() + "' already exists!", "400", "400");
        }

        if (userCountPhone > 0) {
            throw new NSKException("Phone Number '" + request.getPhoneNumber() + "' already exists!", "404", "404");
        }
    }
}
