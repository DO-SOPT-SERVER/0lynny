package com.example.server.dto.post;

import com.example.server.domain.Category;
import com.example.server.domain.Post;
public record PostGetResponse<Category>(
        Long id,
        String title,
        String content,
        String category
) {
    public static PostGetResponse of(Post post, com.example.server.domain.Category category) {
        return new PostGetResponse(
                post.getPostId(),
                post.getTitle(),
                post.getContent(),
                category.getContent()
        );
    }
}
