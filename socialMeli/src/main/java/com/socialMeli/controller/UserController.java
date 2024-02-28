package com.socialMeli.controller;

import com.socialMeli.dto.response.*;

import com.socialMeli.service.IUserService;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RestController
@AllArgsConstructor
@Validated
public class UserController {
    private final IUserService userService;

    @GetMapping("/users/{userId}/followers/list")
    public ResponseEntity<FollowedListDto> getVendorFollowers(
            @Positive(message = "El id debe ser un valor positivo")
            @NotNull(message = "El id de usuario no puede estar vacio")
            @PathVariable Integer userId,
            @RequestParam(required = false) String order) {
        return new ResponseEntity<>(userService.getVendorFollowers(userId, order), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/followers/count")
    public ResponseEntity<VendorFollowCountDto> getFollowerCount(
            @NotNull(message = "El id de usuario no puede estar vacio")
            @Positive(message = "El id debe ser un valor positivo")
            @PathVariable Integer userId) {
        return ResponseEntity.ok().body(userService.getFollowerCount(userId));
    }

    @GetMapping("/users/{userId}/followed/list")
    public ResponseEntity<FollowedListDto> getFollowedList(
            @NotNull(message = "El id de usuario no puede estar vacio")
            @Positive(message = "El id debe ser un valor positivo")
            @PathVariable Integer userId,
            @RequestParam(required = false) String order) {
        return ResponseEntity.ok().body(userService.getFollowedList(userId, order));
    }

    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<MessageDto> follow(@PathVariable("userId")
            @NotNull(message = "El id de usuario no puede estar vacio")
            @Positive(message = "El id debe ser un valor positivo") Integer userId,
            @NotNull(message = "El id de usuario no puede estar vacio")
            @Positive(message = "El id debe ser un valor positivo")
            @PathVariable Integer userIdToFollow) {
        return new ResponseEntity<>(userService.newFollow(userId, userIdToFollow), HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<UserUnfollowedDto> unfollowUser(
            @NotNull(message = "El id de usuario no puede estar vacio")
            @Positive(message = "El id debe ser un valor positivo")
            @PathVariable Integer userId,
            @NotNull(message = "El id de usuario no puede estar vacio")
            @Positive(message = "El id debe ser un valor positivo")
            @PathVariable Integer userIdToUnfollow) {
        return ResponseEntity.ok().body(userService.unfollowUser(userId, userIdToUnfollow));
    }
}
