package nsk.cath.com.nsukka.model.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nsk.cath.com.nsukka.enums.Constants;
import nsk.cath.com.nsukka.enums.RoleName;
import nsk.cath.com.nsukka.enums.RoleType;
import nsk.cath.com.nsukka.model.SuperModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "RoleRequest", schema = Constants.SCHEMA_NAME)
public class Role extends SuperModel implements Serializable{

    @Column(name = "Id")
    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("name")
    @NotNull(message = "RoleRequest nameRequest cannot be null")
    @Column(name = "NameRequest", unique = true)
    @Enumerated(EnumType.STRING)
    private RoleName name;

    @Column(name = "RoleType")
    @NotNull(message = "RoleRequest Type cannot be null")
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Column(name = "Description")
    @JsonProperty("description")
    private String description;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "RolePrivileges", joinColumns = @JoinColumn(name = "RoleId", referencedColumnName = "Id"), inverseJoinColumns = @JoinColumn(name = "PrivilegeId", referencedColumnName = "Id"))
    private Set<Privilege> privileges = new HashSet<>();


    public Role(Long id, RoleName name) {
        this.id = id;
        this.name = name;
    }

    public Role(RoleName name, RoleType roleType){
        this.name = name;
    }
}
