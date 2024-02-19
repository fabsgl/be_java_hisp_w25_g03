package com.socialMeli.controller;

import com.socialMeli.dto.response.FollowedListDto;
import com.socialMeli.dto.response.VendorFollowCountDto;
import com.socialMeli.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {
    private final IUserService userService;

    @GetMapping("/users/{userId}/followers/count")
    public ResponseEntity<VendorFollowCountDto> getFollowerCount(@PathVariable Integer userId) {
        return ResponseEntity.ok().body(userService.getFollowerCount(userId));
    }
    @GetMapping("/users/{userId}/followed/list")
    public ResponseEntity<FollowedListDto> getFollowedList(@PathVariable Integer userId) {
        FollowedListDto followedListResponse = userService.getFollowedList(userId);
        return ResponseEntity.ok().body(followedListResponse);
    }
}
