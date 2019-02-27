package nsk.cath.com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NameRequest {

    @NotNull(message = "First nameRequest is required")
    private String firstName;

    private String middleName;

    @NotNull(message = "Last nameRequest is required")
    private String lastName;
}