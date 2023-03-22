package com.example.communityservice.controller;

import com.example.communityservice.domain.CommunityRole;
import com.example.communityservice.dto.request.CategoryRequestDto;
import com.example.communityservice.dto.request.ChannelRequestDto;
import com.example.communityservice.dto.request.CommunityModifyRequestDto;
import com.example.communityservice.dto.request.DeleteRequestDto;
import com.example.communityservice.response.CommonResponse;
import com.example.communityservice.service.CommunityService;
import com.example.communityservice.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import static com.example.communityservice.domain.SuccessMessages.*;
import static com.example.communityservice.domain.CommunityRole.*;

@RestController
@RequestMapping("/community")
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;
    private final ResponseService responseService;

    @GetMapping("/test")
    public String testMethod(){
        return "커뮤니티 서버 테스트 완료!";
    }

    @PostMapping("/createcommunity")
    public CommonResponse<Object> createCommunity(HttpServletRequest request, @RequestPart MultipartFile file,
                                                  @RequestParam String communityName){
        Long userId = Long.parseLong(request.getHeader("id"));
        return responseService.getSuccessResponse(COMMUNITY_CREATE_SUCCESS, communityService.createCommunity(userId, file, communityName));
    }

    @PostMapping("/createcategory")
    public CommonResponse<Object> createCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        return responseService.getSuccessResponse(CATEGORY_CREATE_SUCCESS, communityService.createCategory(categoryRequestDto));
    }

    @PostMapping("/createchannel")
    public CommonResponse<Object> createChannel(@RequestBody ChannelRequestDto channelRequestDto) {
        return responseService.getSuccessResponse(CHANNEL_CREATE_SUCCESS, communityService.createChannel(channelRequestDto));
    }

    @GetMapping("/showcommunitys")
    public CommonResponse<Object> showCommunity(HttpServletRequest request){
        Long userId = Long.parseLong(request.getHeader("id"));
        return responseService.getSuccessResponse(COMMUNITY_VIEW_SUCCESS, communityService.showCommunity(userId));
    }

    // 커뮤니티 프로필 조회 (userId, Community Id)
    @GetMapping("/personal/{communityId}")
    public CommonResponse<Object> getCommunityPersonalProfile(HttpServletRequest request, @PathVariable("communityId") Long communityId ){
        Long userId = Long.parseLong(request.getHeader("id"));
        return responseService.getSuccessResponse(COMMUNITY_PERSONAL_SUCCESS, communityService.getCommunityPersonalProfile(userId, communityId));
    }

    // 커뮤니티 멤버들 조회 (Community Id)
    @GetMapping("/members/{communityId}")
    public CommonResponse<Object> getCommunityMembers(@PathVariable("communityId") Long communityId){
        return responseService.getSuccessResponse(COMMUNITY_MEMBERS_VIEW_SUCCESS, communityService.getCommunityMembers(communityId));
    }

    // 카테고리 조회 (커뮤니티 아이디)
    @GetMapping("/categorys/{communityId}")
    public CommonResponse<Object> getCommunityCategorys(@PathVariable("communityId") Long communityId){
        return responseService.getSuccessResponse(COMMUNITY_CATEGORY_VIEW_SUCCESS, communityService.getCommunityCategory(communityId));
    }

    // 채널 조회 (커뮤니티 아이디)
    @GetMapping("/channels/{communityId}")
    public CommonResponse<Object> getCommunityChannels(@PathVariable("communityId") Long communityId){
        return responseService.getSuccessResponse(COMMUNITY_CHANNEL_VIEW_SUCCESS, communityService.getCommunityChannel(communityId));

    }

    @DeleteMapping("/community")
    public CommonResponse<Object> deleteCommunity(@RequestBody DeleteRequestDto deleteRequestDto) {
        return responseService.getSuccessResponse(COMMUNITY_DELETE_SUCCESS, communityService.deleteCommunity(deleteRequestDto));

    }

    @DeleteMapping("/category")
    public CommonResponse<Object> deleteCategory(@RequestBody DeleteRequestDto deleteRequestDto) {
        return responseService.getSuccessResponse(CATEGORY_DELETE_SUCCESS, communityService.deleteCommunityCategory(deleteRequestDto));

    }

    @DeleteMapping("/channel")
    public CommonResponse<Object> deleteChannel(@RequestBody DeleteRequestDto deleteRequestDto) {
        return responseService.getSuccessResponse(CHANNEL_DELETE_SUCCESS, communityService.deleteCommunityChannel(deleteRequestDto));

    }

    @PatchMapping("/community")
    public CommonResponse<Object> modifyCommunity(@RequestBody CommunityModifyRequestDto modifyRequestDto) {
        return responseService.getSuccessResponse(COMMUNITY_MODIFY_SUCCESS,communityService.modifyCommunity(modifyRequestDto));

    }


    @PatchMapping("/category")
    public CommonResponse<Object> modifyCategory(@RequestBody CommunityModifyRequestDto modifyRequestDto) {
        return responseService.getSuccessResponse(CATEGORY_MODIFY_SUCCESS,communityService.modifyCommunityCategory(modifyRequestDto));

    }
    @PatchMapping("/channel")
    public CommonResponse<Object> modifyChannel(@RequestBody CommunityModifyRequestDto modifyRequestDto) {
        return responseService.getSuccessResponse(CHANNEL_MODIFY_SUCCESS,communityService.modifyCommunityChannel(modifyRequestDto));

    }

    @PostMapping("/joincommunity")
    public CommonResponse<Object> joinCommunity(HttpServletRequest request, @RequestParam Long communityId){
        Long userId = Long.parseLong(request.getHeader("id"));
        return responseService.getSuccessResponse(COMMUNITY_JOIN_SUCCESS, communityService.addCommunityMember(userId, communityId, USER));
    }



}
