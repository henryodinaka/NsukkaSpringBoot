package nsk.cath.com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nsk.cath.com.enums.Continent;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryRequest {
    private Long id;
    private String name;

    private Continent continent;
}
