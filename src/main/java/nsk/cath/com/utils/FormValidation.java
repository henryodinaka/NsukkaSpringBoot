package nsk.cath.com.utils;

import nsk.cath.com.dto.ContactRequest;
import nsk.cath.com.dto.UserRequest;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Pattern;

@Service
public class FormValidation {


    //validate Demographics here
    public static String validateDemography(UserRequest request) {

        if (!validData(request.getTitle())) {
            return "title is required";
        }

        if (!validData(request.getNameRequest().getLastName())) {
            return "surname is required";
        }
        if (!validName(request.getNameRequest().getLastName())) {
            return "surname is invalid, Name must be between 2 and 255 non-digit characters";
        }

        if (!validData(request.getNameRequest().getFirstName())) {
            return "first name is required";
        }
        if (!validName(request.getNameRequest().getFirstName())) {
            return "first name is invalid, Name must be between 2 and 255 non-digit characters";
        }

        if (validData(request.getNameRequest().getMiddleName())) {
            if (!validName(request.getNameRequest().getMiddleName())) {
                return "middle name is invalid, Name must be between 2 and 255 non-digit characters";
            }
        }


        if (validData(request.getEmail())) {
            if(request.getEmail().contains(" ")){
                return "Please ensure you remove all whitespaces in the emailAddress address provided";
            }

            if (!validEmail(request.getEmail())){
                return "Email address is invalid. Please provide a valid emailAddress address";
            }
        }

        if (!validData(request.getPhoneNumber())) {
            return "phoneNumber is required";
        }
        if (!validPhoneNumber(request.getPhoneNumber())) {
            return "Invalid phoneNumber, Phone number must be between 7 and 15 digit characters";
        }

        if (!validData(request.getDateOfBirth())) {
            return "date of birth is required";
        }
        if (!validDateOfBirth(request.getDateOfBirth())) {
            return "Date of birth is invalid. Age must be between 16 - 150 years";
        }

        if (!validData(request.getGender())) {
            return "gender is required";
        }
        if (!validData(request.getBranchId()))
            return "Invalid Branch provide";

        if (!validData(request.getParishId()))
            return "Invalid Parish provide";

        return null;
    }

    public static String validateContactDetails(ContactRequest request) {
        String address;
        String stateOf;
        if (request.isHomeAddress())
        {
            address ="home";
            stateOf ="origin";
        }
        else
        {
            address ="residential";
            stateOf ="residence";
        }
        if (!validData(request.getHouseAddress()))
            return address+" address is required";

        if (!validLength(request.getHouseAddress(), 20, 255))
            return "Invalid "+address+" Address, "+address+" Address must be between 20 and 255 characters";

        if (request.isNigeria()) {
            if (!validData(request.getLgaId())) {
                return "local government area Of  "+stateOf+"  is required";
            }
        }
        if (!validLength(request.getHouseAddress(),5,50))
            return "Address must be between 5 and 50 characters";
        if (!validLength(request.getCity(),3,20))
            return "City must be between 3 and 20";
        return null;
    }

    public String validateSupplementaryInformation(String addInfo1, String addInfo2, String addInfo3, String addInfo4) {

        if (validData(addInfo1)) {
            if (!validLength(addInfo1, 20, 180)) {
                return "Invalid addition information 1, addition information must be between 20 and 180 characters";
            }
        }

        if (validData(addInfo2)) {
            if (!validLength(addInfo2, 20, 180)) {
                return "Invalid addition information 2, addition information must be between 20 and 180 characters";
            }
        }

        if (validData(addInfo3)) {
            if (!validLength(addInfo3, 20, 180)) {
                return "Invalid addition information 3, addition information must be between 20 and 180 characters";
            }
        }

        if (validData(addInfo4)) {
            if (!validLength(addInfo4, 20, 180)) {
                return "Invalid addition information 4, addition information must be between 20 and 180 characters";
            }
        }

        return null;
    }
    public void displayErrorMessage(String message) {

//        JOptionPane.showMessageDialog(null,message,"Validation Error", JOptionPane.ERROR_MESSAGE);
    }

    public static boolean validData(Object object) {

        if (object == null || String.valueOf(object).isEmpty()) {
            return false;
        }

        return true;
    }

//    public static void main(String[] args){
//        System.out.println(validBVN("1234567890"));
//    }

    public static boolean validBVN(String bvn) {

        if (bvn.matches("^\\d{11}$")) {
            return true;
        }

        return false;
    }

    public  static boolean validPhoneNumber(String phoneNumber) {

        if (phoneNumber.matches("^\\d{7,15}$")) {
            return true;
        }

//        if (phoneNumber.matches("^+\\d{13}$")){
//            return true;
//        }

        return false;
    }

    public  static boolean validName(String name) {

        if (name.length() < 2) {
            return false;
        }

        if (name.length() > 255) {
            return false;
        }

        //pure alphabets
        if (!(Pattern.compile("[a-zA-Z]+").matcher(name).matches())) {
            return false;
        }

        return true;
    }

    public static  boolean validNumber(String numbers) {

        //pure number
        if (!(Pattern.compile("[0-9]+").matcher(numbers).matches())) {
            return false;
        }

        return true;
    }

    public static  boolean validLength(String string, int min, int max) {

        if (string.length() < min) {
            return false;
        }

        if (max != 0) {
            if (string.length() > max) {
                return false;
            }
        }

        return true;
    }

    public  static boolean validLength(String string, int digits) {

        if (string.length() < digits) {
            return false;
        }

        if (digits != 0) {
            if (string.length() > digits) {
                return false;
            }
        }
        return true;
    }

    public static  boolean validDateOfBirth(Date dob) {

        Date today = new Date();

        if (dob.after(today)) {
            return false;
        }


        LocalDate dt = dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(dt, currentDate);

        if (period.getYears() < 16) { //must not be less than 16 years
            return false;
        }

        if (period.getYears() > 150) { //must be greater than 150 years
            return false;
        }
        return true;
    }

    public static  boolean validEmail(String emailAddress) {

        if (emailAddress.contains("^")) {
            return false;
        }

        if (Pattern.compile("[^a-zA-Z0-9@._]").matcher(emailAddress.replace("-", "")).find()) {
            return false;
        }

        return EmailValidator.getInstance().isValid(emailAddress);
    }

}
