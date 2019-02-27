package nsk.cath.com.service.article;

import nsk.cath.com.model.art.Article;
import nsk.cath.com.model.art.Comment;
import nsk.cath.com.repo.article.ArticleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ArticleService {
    private ArticleRepo articleRepo;
    private CommentService commentService;
//    private LikesRepo likesRepo;

    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }


//    @Autowired
//    public void setLikesRepo(LikesRepo likesRepo) {
//        this.likesRepo = likesRepo;
//    }

    @Autowired
    public void setArticleRepo(ArticleRepo articleRepo) {
        this.articleRepo = articleRepo;
    }
    public Page<Article> getAllByTitle(String title,Pageable pageable)
    {
        return articleRepo.getAllByTitle(title,pageable);
    }
    public Page<Article> getAllByUser(Long userId,Pageable pageable)
    {
        return articleRepo.getAllByUser(userId,pageable);
    }
    public Article findById(Long id)
    {
        return articleRepo.findByArticleId(id);
    }
    public Page<Article> getAllByDate(Date createdAt,Pageable pageable)
    {
        return articleRepo.getAllByDate(createdAt,pageable);
    }
    public Long getLikes(Article article)
    {
        return article.getLike();
    }
    public Long getDisLikes(Article article)
    {
        return article.getDislike();
    }
    public Page<Comment> getAllByArticle(Long articleId, Pageable pageable)
    {
        return commentService.getAllByArticle(articleId,pageable);
    }
}
