package nsk.cath.com.embaddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Name {
    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "MiddleName")
    private String middleName;

    @Column(name = "LastName")
    private String lastName;

    public Name(Name name) {
        this.setFirstName(name.getFirstName());
        this.setLastName(name.getLastName());
        this.setMiddleName(name.getMiddleName());

    }

}
