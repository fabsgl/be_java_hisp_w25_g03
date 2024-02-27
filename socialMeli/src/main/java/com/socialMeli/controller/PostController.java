package com.socialMeli.controller;

import com.socialMeli.dto.request.PostDTO;
import com.socialMeli.dto.request.PromotionalPostDTO;
import com.socialMeli.dto.response.PublicationDto;
import com.socialMeli.dto.response.*;
import com.socialMeli.service.IPostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class PostController {

    private final IPostService postService;

    @GetMapping("/products/followed/{userId}/list")

    public ResponseEntity<PublicationDto> obtainLastPublicationsByTheFollowedVendors(
                                                            @PathVariable Integer userId,
                                                            @RequestParam(required = false) String order) {
        return ResponseEntity.ok().body(postService
                .obtainLastPublicationsByTheFollowedVendors(userId, order));
    }

    @GetMapping("/products/promo-post/list")
    public ResponseEntity<?> getAllPromotedPostFromAVendor(@RequestParam Integer user_id) {
        return ResponseEntity.ok().body(postService.getAllPromotedPostFromAVendor(user_id));
    }


    @GetMapping("/products/promo-post/count")
    public ResponseEntity<PromotedVendorDto> obtainTheQuantityOfPromotedPostForOneVendor(
                                                            @RequestParam Integer user_id) {
        return ResponseEntity.ok().body(postService.obtainTheQuantityOfPromotedPostForOneVendor(user_id));
    }

    @PostMapping("/products/promo-post")
    public ResponseEntity<MessageDto> addNewPromotionalPost(@RequestBody PromotionalPostDTO postDto){
        postService.addPost(postDto);
        return new ResponseEntity<>(new MessageDto("Post creado con éxito"), HttpStatus.OK);
    }

    @PostMapping("/products/post")
    public ResponseEntity<MessageDto> addNewPost(@RequestBody PostDTO postDto){
        postService.addPost(postDto);
        return new ResponseEntity<>(new MessageDto("Post creado con éxito"), HttpStatus.OK);
    }

}
