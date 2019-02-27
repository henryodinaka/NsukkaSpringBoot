package nsk.cath.com.service.article;

import lombok.extern.slf4j.Slf4j;
import nsk.cath.com.model.art.Comment;
import nsk.cath.com.repo.article.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CommentService {
    private CommentRepo commentRepo;

    @Autowired
    public void setCommentRepo(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    public Page<Comment> getAllByArticle(Long articleId, Pageable pageable)
    {
        return commentRepo.getAllByArticle(articleId,pageable);
    }
}
