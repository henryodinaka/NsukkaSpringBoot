package nsk.cath.com.nsukka.model.Article;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany; 
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nsk.cath.com.nsukka.model.SuperModel;
import nsk.cath.com.nsukka.model.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author LEOGOLD
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "ArticleRepo")
public class Article extends SuperModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,unique = true)
    private Long id;

    private String title;

    private String content;
    
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,targetEntity = User.class)
    @JoinColumn(foreignKey = @ForeignKey(name="FK_Articles_Users"))
    private User postedBy;
    
    @OneToMany(targetEntity=Comment.class,
            mappedBy="id", cascade=CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Comment> commentId;

    public Article(String title, String content, User postedBy) {
        this.title = title;
        this.content = content;
        this.postedBy = postedBy;
    }
 

    public Article(String title, String movieDes) {
        this.title = title;
        this.content = movieDes; 
    }

}
