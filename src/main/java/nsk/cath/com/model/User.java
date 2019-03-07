package nsk.cath.com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nsk.cath.com.embaddable.Name;
import nsk.cath.com.enums.Constants;
import nsk.cath.com.enums.Gender;
import nsk.cath.com.enums.Status;
import nsk.cath.com.enums.Title;
import nsk.cath.com.model.auth.Role;
import nsk.cath.com.model.contact.Parish;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "User",schema = Constants.SCHEMA_NAME)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private Title title;

    @Column
    private String password;

    @Embedded
    private Name name;

    @NotNull
    @Column
    private String email;

    @Column
    private String phoneNumber;

    @Temporal(TemporalType.DATE)
    @Column
    private Date dateOfBirth;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

    @Column
    private String picture;

    @Column
    private String backgroundPicture;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ParishId", referencedColumnName = "Id")
    private Parish parish;
}
