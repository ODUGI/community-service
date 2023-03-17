package com.example.communityservice.service;

import com.example.communityservice.domain.CommunityRole;
import com.example.communityservice.dto.CategoryRequestDto;
import com.example.communityservice.dto.ChannelRequestDto;
import com.example.communityservice.dto.CommunityRequestDto;
import com.example.communityservice.entity.*;
import com.example.communityservice.exception.ApiException;
import com.example.communityservice.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static com.example.communityservice.domain.CommunityRole.*;
import static com.example.communityservice.exception.ErrorCode.*;
import static com.example.communityservice.domain.ChannelType.*;


@Service
@RequiredArgsConstructor
public class CommunityService {

    private final FileUploadService fileUploadService;
    private final CommunityRepository communityRepository;
    private final MemberRepository memberRepository;
    private final CommunityMemberRepository communityMemberRepository;
    private final ChannelRepository channelRepository;
    private final CategoryRepository categoryRepository;

    public Long createCommunity(Long userId, MultipartFile file, String communityName) {

        // file 유무에 따라 다르게

        String filePath = "";

        if (file != null) {
            filePath = fileUploadService.uploadFile(file);
        }

        CommunityRequestDto communityRequestDto = CommunityRequestDto.builder()
                .name(communityName)
                .profileImage(filePath)
                .build();

        Community community = communityRequestDto.toCommunity();
        Community newCommunity = communityRepository.save(community);

        //생성과 동시에 멤버 추가
        addCommunityMember(userId, newCommunity.getId(), ADMIN);

        // 기본 카테고리 생성
        CategoryRequestDto chatCategoryDto = CategoryRequestDto.builder()
                .communityId(newCommunity.getId())
                .name("채팅 채널")
                .build();

        CategoryRequestDto voiceCategoryDto = CategoryRequestDto.builder()
                .communityId(newCommunity.getId())
                .name("음성 채널")
                .build();


        Long chatCategoryId = createCategory(chatCategoryDto);
        Long voiceCategoryId = createCategory(voiceCategoryDto);

        // 기본 채널 생성

        ChannelRequestDto chatChannelDto = ChannelRequestDto.builder()
                .categoryId(chatCategoryId)
                .name("일반")
                .type(CHAT)
                .build();

        ChannelRequestDto voiceChannelDto = ChannelRequestDto.builder()
                .categoryId(voiceCategoryId)
                .name("일반")
                .type(VOICE)
                .build();

        createChannel(chatChannelDto);
        createChannel(voiceChannelDto);

        return newCommunity.getId();
    }

    public Long addCommunityMember(Long userId, Long communityId, CommunityRole role) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new ApiException(NO_MEMBER_ERROR));

        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new ApiException(NO_COMMUNITY_ERROR));

        CommunityMember communityMember = CommunityMember.builder()
                .community(community)
                .member(member)
                .role(role)
                .email(member.getEmail())
                .profileImage(member.getProfileImagePath())
                .introduction(member.getIntroduction())
                .name(member.getName())
                .build();

        CommunityMember newCommunityMember = communityMemberRepository.save(communityMember);

        return newCommunityMember.getId();
    }

    public Long createCategory(CategoryRequestDto categoryRequestDto) {
        Community community = communityRepository.findById(categoryRequestDto.getCommunityId())
                .orElseThrow(() -> new ApiException(NO_COMMUNITY_ERROR));

        Category category = Category.builder()
                .community(community)
                .name(categoryRequestDto.getName())
                .build();

        Category newCategory = categoryRepository.save(category);

        return newCategory.getId();
    }

    public Long createChannel(ChannelRequestDto channelRequestDto) {
        Category category = categoryRepository.findById(channelRequestDto.getCategoryId())
                .orElseThrow(() -> new ApiException(NO_CATEGORY_ERROR));

        Channel channel = Channel.builder()
                .category(category)
                .type(channelRequestDto.getType())
                .name(channelRequestDto.getName())
                .build();

        Channel newChannel = channelRepository.save(channel);

        return newChannel.getId();
    }
}
