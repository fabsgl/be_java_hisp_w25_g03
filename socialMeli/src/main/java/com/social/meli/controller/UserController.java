package com.social.meli.controller;

import com.social.meli.dto.response.MessageDto;
import com.social.meli.dto.response.VendorFollowCountDto;
import com.social.meli.dto.response.FollowedListDto;
import com.social.meli.service.IUserService;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;


@RestController
@AllArgsConstructor
public final class UserController {
    private final IUserService userService;

    @GetMapping("/users/{userId}/followers/list")
    public ResponseEntity<FollowedListDto> getVendorFollowers(@PathVariable Integer userId,
                                                              @RequestParam(required = false) String order) {
        return new ResponseEntity<>(userService.getVendorFollowers(userId, order), OK);
    }

    @GetMapping("/users/{userId}/followers/count")
    public ResponseEntity<VendorFollowCountDto> getVendorFollowerCount(@PathVariable Integer userId) {
        return ResponseEntity.ok().body(userService.getFollowerCount(userId));
    }

    @GetMapping("/users/{userId}/followed/list")
    public ResponseEntity<FollowedListDto> getFollowedList(@PathVariable Integer userId, @RequestParam(required = false) String order) {
        return ResponseEntity.ok().body(userService.getFollowedList(userId, order));
    }

    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<MessageDto> follow(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) {
        return new ResponseEntity<>(userService.newFollow(userId, userIdToFollow), OK);
    }

    @PostMapping("/users/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<MessageDto> unfollowUser(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow) {
        return ResponseEntity.ok().body(userService.unfollowUser(userId, userIdToUnfollow));
    }
}
