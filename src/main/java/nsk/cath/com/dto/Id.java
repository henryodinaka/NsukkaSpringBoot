package nsk.cath.com.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Id {

    @NotNull(message = "id is required")
    private Long id;

}
