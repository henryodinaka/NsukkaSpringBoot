package nsk.cath.com.nsukka.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrivilegeRequest {

    private Long id;

    @NotNull(message = "task name is required")
    private String name;

    @NotNull(message = "task url is required")
    private String url;

    private String description;

}
