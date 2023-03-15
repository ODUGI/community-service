package com.example.communityservice.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.time.LocalDateTime;

public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_id")
    private Long id;

    private String name;

    private String profileImage;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void createdAt(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = createdAt;
    }

    @PreUpdate
    public void updatedAt(){
        this.updatedAt = LocalDateTime.now();
    }
}
