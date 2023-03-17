package com.example.communityservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SuccessMessages {

    COMMUNITY_VIEW_SUCCESS("커뮤니티 조회 성공"),
    COMMUNITY_CREATE_SUCCESS("커뮤니티 생성 완료"),
    CATEGORY_CREATE_SUCCESS("카테고리 생성 완료"),
    CHANNEL_CREATE_SUCCESS("채널 생성 완료");

    private String message;
}
