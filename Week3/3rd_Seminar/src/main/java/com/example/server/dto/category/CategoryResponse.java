package com.example.server.dto.category;

import com.example.server.domain.Category;
import com.example.server.domain.Post;
import com.example.server.dto.post.PostGetResponse;

public record CategoryResponse(
        String category
) {
    public static CategoryResponse of(Category category) {
        return new CategoryResponse(
                category.getContent()
        );
    }
}
