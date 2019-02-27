package nsk.cath.com.repo.article;

import nsk.cath.com.model.art.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

//@Transactional
//@Repository
public interface LikesRepo {//extends JpaRepository<Likes, Long> {
//    @Query("select l from  Likes l where l.article.id =:articleId and l.liked =:isLiked")
//    List<Likes> getLikes(@Param("articleId")Long articleId ,@Param("isLiked")boolean liked);
}
