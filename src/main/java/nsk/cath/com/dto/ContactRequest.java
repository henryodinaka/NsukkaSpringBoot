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
    private Long lgaId;
    private boolean isHomeAddress;
    private boolean isNigeria;
    private boolean isUpdate;
}
