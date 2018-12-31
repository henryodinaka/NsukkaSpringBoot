/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsk.cath.com.nsukka.model.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nsk.cath.com.nsukka.enums.Constants;
import nsk.cath.com.nsukka.model.SuperModel;
import nsk.cath.com.nsukka.model.User;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ToString
@Table(name="PasswordLog",schema = Constants.SCHEMA_NAME)
public class PasswordLog extends SuperModel implements Serializable{
    
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "Id")
    private Long id;
    
    @Basic(optional = false)
    @JoinColumn(name="UserId", referencedColumnName = "Id")
    @ManyToOne(optional = false)
    private User user;
    
    @Basic(optional=false)
    @Column(name ="Password")
    private String password;
}
