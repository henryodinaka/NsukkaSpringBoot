/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsk.cath.com.model.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nsk.cath.com.enums.Constants;
import nsk.cath.com.model.SuperModel;
import nsk.cath.com.model.User;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ToString
@Table(name="PasswordRecoveryCode",schema = Constants.SCHEMA_NAME)
public class PasswordRecoveryCode extends SuperModel implements Serializable {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "Id")
    private Long id;
    
    @Basic(optional = false)
    @JoinColumn(name="UserId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private User user;
    
    @Basic(optional=false)
    @Column(name ="RecoveryCode")
    private String recoveryCode;
    
    @Basic(optional=false)
    @Column(name ="Status")
    private boolean status = false;
}
