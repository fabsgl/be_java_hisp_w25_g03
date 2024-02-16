package com.socialMeli.controller;

import com.socialMeli.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {
    private final IUserService userService;
}
