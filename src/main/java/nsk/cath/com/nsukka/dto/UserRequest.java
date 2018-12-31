package nsk.cath.com.nsukka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nsk.cath.com.nsukka.enums.Gender;
import nsk.cath.com.nsukka.enums.Title;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private Long id;
    private Title title;
    private String password;
    private String email;
    private NameRequest nameRequest;
    private Gender gender;
    private String phoneNumber;
    private ContactRequest contact;
    private Date dateOfBirth;
    private Long role;

}