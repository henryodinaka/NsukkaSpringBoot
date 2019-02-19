package nsk.cath.com.service.article;

import nsk.cath.com.model.Article.Article;
import nsk.cath.com.model.Article.Comment;
import nsk.cath.com.model.Article.Likes;
import nsk.cath.com.repo.article.ArticleRepo;
import nsk.cath.com.repo.article.CommentRepo;
import nsk.cath.com.repo.article.LikesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticleService {
    private ArticleRepo articleRepo;
    private CommentRepo commentRepo;
    private LikesRepo likesRepo;

    @Autowired
    public void setCommentRepo(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    @Autowired
    public void setLikesRepo(LikesRepo likesRepo) {
        this.likesRepo = likesRepo;
    }

    @Autowired
    public void setArticleRepo(ArticleRepo articleRepo) {
        this.articleRepo = articleRepo;
    }
    public Page<Article> getAllByTitle(String title)
    {
        return articleRepo.getAllByTitle(title);
    }
    public Page<Article> getAllByUser(Long userId)
    {
        return articleRepo.getAllByUser(userId);
    }
    public Article findById(Long id)
    {
        return articleRepo.findByArticleId(id);
    }
    public Page<Article> getAllByDate(Date createdAt)
    {
        return articleRepo.getAllByDate(createdAt);
    }
    public List<Likes> getLikes(Long articleId ,boolean liked)
    {
        return likesRepo.getLikes(articleId,liked);
    }
    public Page<Comment> getAllByArticle(Long articleId)
    {
        return commentRepo.getAllByArticle(articleId);
    }
}
