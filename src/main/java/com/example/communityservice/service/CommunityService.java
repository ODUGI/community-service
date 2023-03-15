package com.example.communityservice.service;

import com.example.communityservice.dto.CommunityRequestDto;
import com.example.communityservice.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final FileUploadService fileUploadService;
    private final CommunityRepository communityRepository;

    public String createCommunity(Long userId, MultipartFile file, CommunityRequestDto communityRequestDto) {

        return "OK";
    }
}
