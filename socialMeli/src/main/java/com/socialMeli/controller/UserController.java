package com.socialMeli.controller;

import com.socialMeli.dto.response.VendorFollowerListDTO;
import com.socialMeli.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    private final IUserService userService;

    //US 0003: Obtener un listado de todos los usuarios que siguen a un determinado vendedor (¿Quién me sigue?)
    @GetMapping("/users/{userId}/followers/list")
    public ResponseEntity<VendorFollowerListDTO> getFollowers(@PathVariable Integer userId){
        return new ResponseEntity<>(userService.getVendorFollowers(userId), HttpStatus.OK);
    }
}
