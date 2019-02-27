package nsk.cath.com.model.art;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nsk.cath.com.enums.Constants;
import nsk.cath.com.model.SuperModel;
import nsk.cath.com.model.User;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author LEOGOLD
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (schema = Constants.SCHEMA_NAME)
@ToString
public class Comment extends SuperModel implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long Id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="article_id")
    private Article article;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Id")
    private User userId;
    
    @Column
    private String comment ;

    @Column
    private Long like;

    @Column
    private Long dislike;

    public Comment(Article article, User userId, String comment) {
        this.article = article;
        this.userId = userId;
        this.comment = comment;
    }



}
