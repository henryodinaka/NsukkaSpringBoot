/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsk.cath.com.model.Article;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author LEOGOLD
 */
@Data
@Entity
@Table
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Likes implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long Id;
      
    @ManyToOne (fetch =FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name="FK_Rate_Article"))
    private Article article;

    @Column
    private boolean isLiked;

    public Likes(Article article, boolean isLiked) {
        this.article= article;
        this.isLiked = isLiked;
    }

}
