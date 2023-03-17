package com.example.communityservice.controller;

import com.example.communityservice.domain.SuccessMessages;
import com.example.communityservice.dto.CategoryRequestDto;
import com.example.communityservice.dto.ChannelRequestDto;
import com.example.communityservice.dto.CommunityRequestDto;
import com.example.communityservice.response.CommonResponse;
import com.example.communityservice.service.CommunityService;
import com.example.communityservice.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import static com.example.communityservice.domain.SuccessMessages.*;

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


}
