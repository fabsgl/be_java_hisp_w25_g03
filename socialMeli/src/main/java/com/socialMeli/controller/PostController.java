package com.socialMeli.controller;

import com.socialMeli.dto.request.PostDTO;
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
        return ResponseEntity.ok().body(postService.obtainLastPublicationsByTheFollowedVendors(userId, order));
    }

    //Obtener la cantidad de productos en promoción de un determinado vendedor
    @GetMapping("/products/promo-post/count")
    public ResponseEntity<PromotedVendorDto> obtainTheQuantityOfPromotedPostForOneVendor(
                                                            @RequestParam Integer user_id) {
        return ResponseEntity.ok().body(postService.obtainTheQuantityOfPromotedPostForOneVendor(user_id));
    }

    @PostMapping("/products/promo-post")
    public ResponseEntity<MessageDTO> addNewPromotionalPost(@RequestBody PostDTO postDto){
        postService.addPost(postDto);
        return new ResponseEntity<>(new MessageDTO("Post creado con éxito"), HttpStatus.OK);
    }

    @PostMapping("/products/post")
    public ResponseEntity<MessageDTO> addNewPost(@RequestBody PostDTO postDto){
        postService.addPost(postDto);
        return new ResponseEntity<>(new MessageDTO("Post creado con éxito"), HttpStatus.OK);
    }

}
