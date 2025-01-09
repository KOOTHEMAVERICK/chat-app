package com.ll.chatApp.domain.article.article.entity;

import com.ll.chatApp.domain.article.articleComment.entity.ArticleComment;
import com.ll.chatApp.domain.article.articleTag.entity.ArticleTag;
import com.ll.chatApp.domain.member.entity.Member;
import com.ll.chatApp.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static jakarta.persistence.CascadeType.ALL;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Setter
@Getter
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@SuperBuilder
@ToString(callSuper = true)
public class Article extends BaseEntity {
    private String title;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY) // fetch-FetchTpe.EAGER(즉시 불러와도 부담 없음)
    private Member author;

    @OneToMany(mappedBy = "article", cascade = ALL, orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private List<ArticleComment> comments = new ArrayList<>();

    public void addComment(Member memberAuthor, String commentBody) {
        ArticleComment articleComment = ArticleComment.builder()
                .article(this)
                .author(memberAuthor)
                .body(commentBody)
                .build();


        comments.add(articleComment);
    }

    public void removeComment(ArticleComment articleComment) {
        comments.remove(articleComment);
    }

    @OneToMany(mappedBy = "article", cascade = ALL, orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude // 순환 참조 방지
    private List<ArticleTag> tags = new ArrayList<>();

    public void addTags(String... tagContents){
        for(String tagContent: tagContents){
            addTag(tagContent);
        }
    }

    public void addTag(String tagContent) {
        ArticleTag tag = ArticleTag.builder()
                .article(this)
                .content(tagContent)
                .build();


        tags.add(tag);
    }

    public String getTagsStr(){
        String tagsStr = tags.stream()
                .map(ArticleTag::getContent)
                .collect(Collectors.joining(" #"));


        if(tagsStr.isBlank()){
            return "";
        }
        return "#" + tagsStr;
    }
}
