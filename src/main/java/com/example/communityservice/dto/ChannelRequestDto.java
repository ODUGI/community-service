package com.example.communityservice.dto;

import com.example.communityservice.domain.ChannelType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelRequestDto {

    private String name;
    private Long categoryId;
    private ChannelType type;

}