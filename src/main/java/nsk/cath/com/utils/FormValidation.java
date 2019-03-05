package nsk.cath.com.utils;

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
    public String validateDemography(Date dateOfBirth, String firstName, Object gender, String middleName,String surname, Object title) {

        if (!validData(title)) {
            return "title is required";
        }

        if (!validData(surname)) {
            return "surname is required";
        }
        if (!validName(surname)) {
            return "surname is invalid, Name must be between 2 and 255 non-digit characters";
        }

        if (!validData(firstName)) {
            return "first name is required";
        }
        if (!validName(firstName)) {
            return "first name is invalid, Name must be between 2 and 255 non-digit characters";
        }

        if (validData(middleName)) {
            if (!validName(middleName)) {
                return "middle name is invalid, Name must be between 2 and 255 non-digit characters";
            }
        }

        if (!validData(dateOfBirth)) {
            return "date of birth is required";
        }
        if (!validDateOfBirth(dateOfBirth)) {
            return "Date of birth is invalid. Age must be between 16 - 150 years";
        }

        if (!validData(gender)) {
            return "gender is required";
        }

        return null;
    }

    public String validateContactDetails(String email, String residentialAddress, boolean isNigeria, Long state,
                                         Long lga, String phoneNumber ,boolean isHomeAddress) {
        String address;
        String stateOf;
        if (isHomeAddress)
        {
            address ="home";
            stateOf ="origin";
        }
        else
        {
            address ="residential";
            stateOf ="residence";
        }

        if (validData(email)) {
            if(email.contains(" ")){
                return "Please ensure you remove all whitespaces in the emailAddress address provided";
            }

            if (!validEmail(email)){
                return "Email address is invalid. Please provide a valid emailAddress address";
            }
        }

        if (!validData(residentialAddress)) {
            return address+" address is required";
        }
        if (!validLength(residentialAddress, 20, 255)) {
            return "Invalid "+address+" Address, "+address+" Address must be between 20 and 255 characters";
        }

        if (isNigeria) {
            if (!validData(state)) {
                return "state Of "+stateOf+" is required";
            }
            if (!validData(lga)) {
                return "local government area Of  "+stateOf+"  is required";
            }
        }

        if (!validData(phoneNumber)) {
            return "phoneNumber is required";
        }
        if (!validPhoneNumber(phoneNumber)) {
            return "Invalid phoneNumber, Phone number must be between 7 and 15 digit characters";
        }

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

    public boolean validData(Object object) {

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

    public boolean validPhoneNumber(String phoneNumber) {

        if (phoneNumber.matches("^\\d{7,15}$")) {
            return true;
        }

//        if (phoneNumber.matches("^+\\d{13}$")){
//            return true;
//        }

        return false;
    }

    public boolean validName(String name) {

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

    public boolean validNumber(String numbers) {

        //pure number
        if (!(Pattern.compile("[0-9]+").matcher(numbers).matches())) {
            return false;
        }

        return true;
    }

    public boolean validLength(String string, int min, int max) {

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

    public boolean validLength(String string, int digits) {

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

    public boolean validDateOfBirth(Date dob) {

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

    public boolean validEmail(String emailAddress) {

        if (emailAddress.contains("^")) {
            return false;
        }

        if (Pattern.compile("[^a-zA-Z0-9@._]").matcher(emailAddress.replace("-", "")).find()) {
            return false;
        }

        return EmailValidator.getInstance().isValid(emailAddress);
    }

}
