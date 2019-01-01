package nsk.cath.com.nsukka.model.Article;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nsk.cath.com.nsukka.model.Article.Article;
import nsk.cath.com.nsukka.model.SuperModel;
import nsk.cath.com.nsukka.model.User;

/**
 *
 * @author LEOGOLD
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "Comment")
@ToString
public class Comment extends SuperModel implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long Id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="id",nullable=false,foreignKey = @ForeignKey(name="FK_Comment_Article"))
    private Article article;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Id", nullable=false,foreignKey = @ForeignKey(name="FK_Comment_Users"))
    private User userId;
    
    @Column
    private String comment ;


    public Comment(Article article, User userId, String comment) {
        this.article = article;
        this.userId = userId;
        this.comment = comment;
    }

     
   
}
