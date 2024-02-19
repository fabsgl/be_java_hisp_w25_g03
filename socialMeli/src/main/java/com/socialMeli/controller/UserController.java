package com.socialMeli.controller;
import com.socialMeli.dto.response.VendorFollowerListDTO;

import com.socialMeli.dto.response.UserUnfollowedDto;
import com.socialMeli.dto.response.VendorFollowCountDto;
import com.socialMeli.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class UserController {
    private final IUserService userService;


    @GetMapping("/users/{userId}/followers/list")
    public ResponseEntity<VendorFollowerListDTO> getVendorFollowers(@PathVariable Integer userId) {
        return new ResponseEntity<>(userService.getVendorFollowers(userId), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/followers/count")
    public ResponseEntity<VendorFollowCountDto> getFollowerCount(@PathVariable Integer userId) {
        return ResponseEntity.ok().body(userService.getFollowerCount(userId));
    }
    @PostMapping("/users/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<UserUnfollowedDto> unfollowUser(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow) {
        return ResponseEntity.ok().body(userService.unfollowUser(userId,userIdToUnfollow));
    }
}
