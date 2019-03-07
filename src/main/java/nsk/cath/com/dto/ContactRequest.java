package nsk.cath.com.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactRequest {
    private Long id;
    private String houseAddress;
    private String city;
    private Long parishId;
    private Long stateId;
    private Long lgaId;
    private Long countryId;
    private boolean isHomeAddress;
    private boolean isNigeria;
}
