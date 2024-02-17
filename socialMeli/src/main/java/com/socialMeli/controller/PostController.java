package com.socialMeli.controller;

import com.socialMeli.service.IPostService;
import com.socialMeli.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PostController {

    private final IUserService userService;
    private final IPostService postService;

//    US 0006: Obtener un listado de las publicaciones realizadas por los vendedores que un
//    usuario sigue en las últimas dos semanas (para esto tener en cuenta ordenamiento por fecha,
//    publicaciones más recientes primero).
    @GetMapping("/products/followed/{userId}/list")
    public void obtainLastPublicationsByTheFollowedVendors(){

    }

    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<?> follow(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) {
        return new ResponseEntity<>(userService.newFollow(userId, userIdToFollow), HttpStatus.OK);
    }
}

