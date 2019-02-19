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

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ToString
@Table(name="PasswordPolicySetting",schema = Constants.SCHEMA_NAME)
public class PasswordPolicySetting extends SuperModel implements Serializable {
    
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "Id")
    private Long id;
    
    @Basic(optional=false)
    @Column(name ="MinimumLength")
    private int minimumLength;
    
    @Basic(optional=false)
    @Column(name ="MaximumLength")
    private int maximumLength;
    
    @Basic(optional=false)
    @Column(name ="UpdatePeriod")
    private int updatePeriod;
    
    @Basic(optional=false)
    @Column(name ="MaxAllowedPassword")
    private int maxAllowedPassword;
}
