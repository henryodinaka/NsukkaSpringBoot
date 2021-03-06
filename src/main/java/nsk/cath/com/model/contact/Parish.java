package nsk.cath.com.model.contact;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Parish implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String parishCode;

    @JsonIgnore
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "StateId", referencedColumnName = "Id")
    private Deanery deanery;

    @ManyToOne
    @JoinColumn(name = "CreatedBy",referencedColumnName = "Id")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "updatedBy",referencedColumnName = "Id")
    private User updatedBy;
}
