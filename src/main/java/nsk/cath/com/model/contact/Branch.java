package nsk.cath.com.model.contact;

import lombok.Data;
import lombok.NoArgsConstructor;
import nsk.cath.com.model.SuperModel;
import nsk.cath.com.model.User;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table
public class Branch extends SuperModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String city;
    @ManyToOne
    @JoinColumn(name = "BranchState",referencedColumnName = "id")
    private State state;

    @ManyToOne
    @JoinColumn(name = "CreatedBy",referencedColumnName = "Id")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "updatedBy",referencedColumnName = "Id")
    private User updatedBy;
}
