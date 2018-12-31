package nsk.cath.com.nsukka.model.career;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nsk.cath.com.nsukka.enums.Qualification;
import nsk.cath.com.nsukka.model.SuperModel;
import nsk.cath.com.nsukka.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@ToString
public class Education extends SuperModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Qualification qualification;

    @Column
    private String school;

    @Column
    private String course;

    @Column
    private Date year;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId", referencedColumnName = "Id")
    private User user;
}
