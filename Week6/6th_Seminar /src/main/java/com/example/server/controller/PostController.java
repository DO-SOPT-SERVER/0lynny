package com.example.server.controller;

import com.example.server.dto.post.PostCreateRequest;
import com.example.server.dto.post.PostGetResponse;
import com.example.server.dto.post.PostUpdateRequest;
import com.example.server.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private static final String CUSTOM_AUTH_ID = "X-Auth-Id";
    private final PostService postService;

    //게시글 조회
    @GetMapping("{postId}")
    public ResponseEntity<PostGetResponse> getPostById(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getById(postId));

    }

    //멤버가 작성한 게시글 목록 조회
    @GetMapping
    public ResponseEntity<List<PostGetResponse>> getPosts(@RequestHeader(CUSTOM_AUTH_ID) Long memberId) {
        return ResponseEntity.ok(postService.getPosts(memberId));
    }

    //    @PostMapping
//    public ResponseEntity<Void> createPost(@RequestHeader(CUSTOM_AUTH_ID) Long memberId, @RequestBody PostCreateRequest request) {
//        URI location = URI.create("/api/post/" + postService.create(request, memberId));
//        return ResponseEntity.created(location).build();
//    }
    @PostMapping
    public ResponseEntity<Void> createPost(
            @RequestBody PostCreateRequest request,
            Principal principal) {

        Long memberId = Long.valueOf(principal.getName());
        URI location = URI.create("/api/posts/" + postService.create(request, memberId));

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("{postId}")
    public ResponseEntity<Void> updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequest request) {
        postService.editContent(postId, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deleteById(postId);
        return ResponseEntity.noContent().build();
    }
}
