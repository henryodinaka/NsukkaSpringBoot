package nsk.cath.com.nsukka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nsk.cath.com.nsukka.enums.Title;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NameRequest {

    @NotBlank(message = "First nameRequest is required")
    private String firstName;

    private String middleName;

    @NotBlank(message = "Last nameRequest is required")
    private String lastName;
}