package nsk.cath.com.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactRequest {
    private Long id;
    private String houseAddress;
    private String town;
    private Long parishId;
    private boolean isHomeAddress;
}
