package com.ll.chatApp.domain.article.article.service;

import com.ll.chatApp.domain.article.article.entity.Article;
import com.ll.chatApp.domain.article.article.repository.ArticleRepository;
import com.ll.chatApp.domain.article.articleComment.entity.ArticleComment;
import com.ll.chatApp.domain.article.articleComment.repository.ArticleCommentRepository;
import com.ll.chatApp.domain.member.entity.Member;
import com.ll.chatApp.global.rsData.RsData;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    @Transactional
    public RsData<Article> write(Long memberId, String title, String content){
        Article article = Article.builder()
                .author(Member.builder().id(memberId).build())
                .title(title)
                .content(content)
                .build();

        articleRepository.save(article);

        return RsData.of("200","%s번 게시글 작성을 완료했습니다.".formatted(memberId), article);
    }

    public Optional<Article> findById(Long id){
        return articleRepository.findById(id);
    }

    @Transactional
    public void modify(Article article, String title, String content) {
        article.setTitle(title);
        article.setContent(content);

//        articleRepository.save(article); // 변경 사항 저장 - Transctional 사용으로 불필요
    }

    public void modifyComment(ArticleComment articleComment, String body) {
        articleComment.setBody(body);

        articleCommentRepository.save(articleComment);
    }

    public List<Article> findAll() {
        return  articleRepository.findAll();
    }
}
