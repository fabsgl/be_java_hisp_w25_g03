package com.socialMeli.socialMeli.controller;

import com.socialMeli.controller.UserController;
import com.socialMeli.dto.response.MessageDto;
import com.socialMeli.entity.User;
import com.socialMeli.service.IUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    IUserService userService;
    @InjectMocks
    UserController userController;

    @Test
    void followTest_OK() {
        //ARRANGE
        String okMessage = "Comenzaste a seguir al usuario Victoria Acosta";
        when(userService.newFollow(2,10)).thenReturn(new MessageDto(okMessage));
        //ACT
        ResponseEntity<MessageDto> response = userController.follow(2,10);
        //ASSERT
        assertEquals(Objects.requireNonNull(response.getBody()).getMessage(), okMessage);
    }

//    @Test
//    void followTest_ValidData() {
//        //ARRANGE
//        //ACT
//        //ASSERT
//        //assertThrows(MethodArgumentNotValidException.class, () -> userController.follow(-1, 2));
//
//        String okMessage = "Comenzaste a seguir al usuario Victoria Acosta";
//        when(userService.newFollow(2,10)).thenReturn(new MessageDto(okMessage));
//        //ACT
//        ResponseEntity<MessageDto> response = userController.follow(2,10);
//        //ASSERT
//        assertEquals(Objects.requireNonNull(response.getBody()).getMessage(), okMessage);
//    }
}
