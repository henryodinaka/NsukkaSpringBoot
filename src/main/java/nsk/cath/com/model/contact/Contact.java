package nsk.cath.com.model.contact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nsk.cath.com.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Contact implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String houseAddress;

    @Column
    private String city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Lga",referencedColumnName = "Id")
    private Lga lga;

    @Column
    private boolean homeAddress;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User",referencedColumnName = "Id")
    private User user;


}
