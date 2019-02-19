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

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "User",schema = Constants.SCHEMA_NAME)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private Title title;

    @Column
    private String password;

    @Embedded
    private Name name;

    @NotBlank(message = "Email can not be blank")
    @Column
    private String email;

    @Column
    private String phoneNumber;

    @Temporal(TemporalType.DATE)
    @Column
    private Data dateOfBirth;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "UserRole",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Role roles ;
    @Column
    private String picture;

    @Column
    private String backgroundPicture;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

}
