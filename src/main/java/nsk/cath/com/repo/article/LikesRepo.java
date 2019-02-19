package nsk.cath.com.repo.article;

import nsk.cath.com.model.Article.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface LikesRepo extends JpaRepository<Likes, Long> {
    @Query("select l from  Likes l where l.article.id =:articleId and l.liked =:isLiked")
    List<Likes> getLikes(@Param("articleId")Long articleId ,@Param("isLiked")boolean liked);
}
