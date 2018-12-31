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
import lombok.NoArgsConstructor;
import nsk.cath.com.nsukka.model.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author LEOGOLD
 */

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "Article")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,unique = true)
    private Long articleId ;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String content;
    
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,targetEntity = User.class)
    @JoinColumn( nullable=false,foreignKey = @ForeignKey(name="FK_Articles_Users"))
    private User username;
    
    @OneToMany(targetEntity=Comment.class,
            mappedBy="articleId", cascade=CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Comment> commentId;
    
    @CreationTimestamp 
    @Column 
    private Date created;
     
    @UpdateTimestamp 
    @Column 
    private Date updated;
    
    @Column
    private String logo;

    public Article(String title, String content, User username) {
        this.title = title;
        this.content = content;
        this.username = username;
    }
 

    public Article(String movieTitle, String movieDes) {
        this.title = movieTitle;
        this.content = movieDes; 
    }

}
