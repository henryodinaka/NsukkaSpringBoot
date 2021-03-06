package nsk.cath.com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nsk.cath.com.enums.Gender;
import nsk.cath.com.enums.MaritalStatus;
import nsk.cath.com.enums.Title;

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
    private Long branchId;
    private Long parishId;
    private Date dateOfBirth;
    private Long role;
    private MaritalStatus maritalStatus;
    private String uploadImage;
}