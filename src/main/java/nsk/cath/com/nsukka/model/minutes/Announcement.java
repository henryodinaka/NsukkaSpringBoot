package nsk.cath.com.nsukka.model.minutes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nsk.cath.com.nsukka.enums.Constants;
import nsk.cath.com.nsukka.enums.Status;
import nsk.cath.com.nsukka.model.SuperModel;
import nsk.cath.com.nsukka.model.User;

import javax.persistence.*;
import java.util.Date;

@Data
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = Constants.SCHEMA_NAME)
public class Announcement  extends SuperModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private String topic;

    @Column
    private String content;

    @Column
    private Date eventDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postBy", referencedColumnName = "Id")
    private User postedBy;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approvedBy", referencedColumnName = "Id")
    private User approvedBy;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updatedBy", referencedColumnName = "Id")
    private User updatedBy;

}
