package com.board.basic.article;

import com.board.basic.DataNotFoundException;
import com.board.basic.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public void create(String title, String content, SiteUser siteUser) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setCreateDate(LocalDateTime.now());
        article.setUser(siteUser);

        this.articleRepository.save(article);

    }

    public Article getArticle(Integer id) {
        Optional<Article> article = this.articleRepository.findById(id);
        if(article.isPresent()) {
            return article.get();
        } else{
            throw new DataNotFoundException("article not found");
        }
    }

    public List<Article> getList() {
        return this.articleRepository.findAll();
    }

    public Article modify(Article article,String title, String content) {
        article.setTitle(title);
        article.setContent(content);
        article.setCreateDate(LocalDateTime.now());

        this.articleRepository.save(article);
        return article;
    }

    public void delete(Article article) {
        this.articleRepository.delete(article);
    }
}
