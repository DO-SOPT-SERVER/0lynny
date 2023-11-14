package com.example.server.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)

public abstract class BaseTimeEntity {
    @CreatedDate
    private LocalDateTime createdAt; //생성시각
    @LastModifiedDate
    private LocalDateTime updatedAt; //수정시각

}
