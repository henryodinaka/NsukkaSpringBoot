package nsk.cath.com.nsukka.repo.article;

import nsk.cath.com.nsukka.model.Article.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
    @Query("select c from Comment c where c.article.id =:articleId")
    Page<Comment> getAllByArticle(@Param("articleId")Long articleId);
}
