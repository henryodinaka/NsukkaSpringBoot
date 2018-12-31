package nsk.cath.com.nsukka.model.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nsk.cath.com.nsukka.enums.Constants;
import nsk.cath.com.nsukka.model.SuperModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Privilege", schema = Constants.SCHEMA_NAME, uniqueConstraints = @UniqueConstraint(columnNames = {"name","module"}))
public class Privilege extends SuperModel implements Serializable {

    @Column(name = "Id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NameRequest")
    @NotNull
    private String name;

    @Column(name = "Url")
    @NotNull
    private String url;

    @Column(name = "Description")
    private String description;

    @Column(name = "Activated")
    private boolean activated = true;
}
