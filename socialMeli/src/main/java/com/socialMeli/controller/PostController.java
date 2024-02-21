package com.socialMeli.controller;

import com.socialMeli.dto.request.PostDTO;
import com.socialMeli.dto.request.PostDescDTO;
import com.socialMeli.dto.response.MessageDTO;
import com.socialMeli.dto.response.PublicationDto;
import com.socialMeli.service.IPostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class PostController {

    private final IPostService postService;


    @GetMapping("/products/followed/{userId}/list")
    public ResponseEntity<PublicationDto> obtainLastPublicationsByTheFollowedVendors(
                                                            @PathVariable Integer userId,
                                                            @RequestParam String order) {
        return ResponseEntity.ok().body(postService.obtainLastPublicationsByTheFollowedVendors(userId, order));
    }

    @PostMapping("/products/post")
    public ResponseEntity<MessageDTO> addNewPost(@RequestBody PostDTO postDto){
        postService.addPost(postDto);
        return new ResponseEntity<>(new MessageDTO("Post creado con éxito"), HttpStatus.OK);
    }
    // US 0010: Da de alta una nueva publicacion/producto con descuento ddiazrodas
    @PostMapping("/products/promo-post")
    public ResponseEntity<?> addNewPromotionPost(@RequestBody PostDescDTO postDescDTO) {
        postService.addPromoPost(postDescDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //US 0011: trae el listado de los productos en promo de un determinado vendedor
//    @GetMapping("/products/promo-post/list?user_id={userId}")
//    public ResponseEntity<?> getPromoPostsByVendor(@RequestParam("user_id") String userId) {
//        return ResponseEntity.ok().body(postService.getPromotionPostsById(userId));
//    }


}
