package com.example.server.service;

import com.example.server.domain.Member;
import com.example.server.domain.Post;
import com.example.server.dto.post.PostCreateRequest;
import com.example.server.exception.BusinessException;
import com.example.server.external.S3Service;
import com.example.server.repository.MemberJpaRepository;
import com.example.server.repository.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServiceV2 {

    private static final String POST_IMAGE_FOLDER_NAME = "posts/";

    //버킷 전략!
    //"posts/1member/1.jpg" -> 폴더링을 하는게 조회 속도 및 성능이 더 빠름

    /*
    "posts/1member/1.jpg"
    "posts/2member/1.jpg"
    "posts/3member/1.jpg"
    "posts/4member/1.jpg"
    "posts/5member/1.jpg" -> 이렇게 앞에 숫자를 붙여주게 되면 조회속도가 더 빠르다고 함
     */

    private final MemberJpaRepository memberJpaRepository;
    private final PostJpaRepository postJpaRepository;
    private final S3Service s3Service;

    @Transactional
    public String createV2(PostCreateRequest request, MultipartFile image, Long memberId) {
        try {
            String imageUrl = s3Service.uploadImage(POST_IMAGE_FOLDER_NAME, image);
            Member member = memberJpaRepository.findByIdOrThrow(memberId);
            Post post = postJpaRepository.save(
                    Post.builder()
                    .title(request.title())
                    .content(request.content())
                    .imageUrl(imageUrl)
                    .member(member)
                    .build());
            return post.getPostId().toString();
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void deleteByIdV2(Long postId) {
        try {
            Post post = postJpaRepository.findById(postId)
                    .orElseThrow(() -> new BusinessException("해당하는 게시글이 없습니다."));
            s3Service.deleteImage(post.getImageUrl());
            postJpaRepository.deleteById(postId);
        } catch (IOException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}