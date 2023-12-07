package com.example.server.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "post")
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String title;
    @Column(columnDefinition = "TEXT") // 어떤 값이 들어올지 정의할 수 있음
    private String content;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "category_id")
    private CategoryId categoryId;

    @Builder
    public Post(String title, String content, String imageUrl, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.imageUrl = imageUrl;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void addCategory(CategoryId categoryId) {
        this.categoryId = categoryId;
    }
}
