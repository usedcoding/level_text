package com.board.basic.article;

import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ArticleRepository extends JpaRepository<Article, Integer> {

    @Query("select "
            + "distinct a "
            + "from Article a "
            + "left outer join SiteUser u1 on a.user=u1 "
            + "where "
            + "   a.title like %:keyword% "
            + "   or a.content like %:keyword% "
            + "   or u1.username like %:keyword% ")
    List<Article> findAllByKeyword(@Param("keyword") String keyword);
}
