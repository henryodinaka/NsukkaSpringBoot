/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsk.cath.com.nsukka.model.Article;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import nsk.cath.com.nsukka.model.Article.Article;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author LEOGOLD
 */

//@Entity
//@Table(nameRequest ="Rating")//, uniqueConstraints ={@UniqueConstraint(columnNames ={"userId", "articleId"})})

public class Rating implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rate_Id")
    private int Id;
      
    @ManyToOne (targetEntity = Article.class)
    @JoinColumn(name = "articleId",foreignKey = @ForeignKey(name="FK_Rate_Article")) 
    private Article articleId;
  
    @Column(name ="star") 
    private int star;

    @CreationTimestamp
    @Temporal (TemporalType.TIMESTAMP)
    @Column (name = "date_created")
    private Date created;
    
    public Rating() {
    }

    public Rating(int Id, Article articleId, int star) {
        this.Id = Id;
        this.articleId = articleId; 
        this.star = star;
    }

    public Rating(Article movieId, int star) {
        this.articleId = movieId; 
        this.star = star;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
    
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Article getArticleId() {
        return articleId;
    }

    public void setArticleId(Article articleId) {
        this.articleId = articleId;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
 
    
}
