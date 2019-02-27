package nsk.cath.com.model.art;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Table(schema = Constants.SCHEMA_NAME)
@AllArgsConstructor
@NoArgsConstructor
public class Article extends SuperModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "author_Id",referencedColumnName = "Id")
    private User author;

    @Column
    private Long like;

    @Column
    private Long dislike;
    public Article(String title, String content, User author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

}
