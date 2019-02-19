package nsk.cath.com.repo.article;

import nsk.cath.com.model.Article.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.Date;

@Transactional
@Repository
public interface ArticleRepo extends JpaRepository<Article, Long> {
    @Query("select a from Article a where a.title=:title")
    Page<Article> getAllByTitle(@Param("title") String title);
    @Query("select a from Article a where a.postedBy.id=:userId")
    Page<Article> getAllByUser(@Param("userId") Long userId);
    @Query("select a from Article a where a.id =:id")
    Article findByArticleId(@Param("id")Long id);
    @Query("select a from Article a where a.createdAt=:createdAt")
    Page<Article> getAllByDate(@Param("createdAt") Date createdAt);
}
