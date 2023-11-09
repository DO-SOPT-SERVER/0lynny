package com.example.server.dto.post;

public record PostCreateRequest(
    String title,
    String content
) {}