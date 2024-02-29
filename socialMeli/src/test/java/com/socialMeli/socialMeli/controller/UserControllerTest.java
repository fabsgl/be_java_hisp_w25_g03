package com.socialMeli.socialMeli.controller;

import com.socialMeli.controller.UserController;
import com.socialMeli.dto.response.MessageDto;
import com.socialMeli.entity.User;
import com.socialMeli.exception.NotFoundException;
import com.socialMeli.repository.IUserRepository;
import com.socialMeli.service.IUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    IUserService userService;

    @InjectMocks
    UserController userController;
    IUserRepository userRepository;

    @Test
    void followTestOK() {
        //ARRANGE
        MessageDto expectedMessage = new MessageDto("Comenzaste a seguir al usuario Victoria Acosta");
        when(userService.newFollow(2,10)).thenReturn(expectedMessage);
        //ACT
        ResponseEntity<MessageDto> response = userController.follow(2,10);
        //ASSERT
        assertEquals((Objects.requireNonNull(response.getBody())).getMessage(), expectedMessage.getMessage());
    }

    @Test
    void followTestUserNotFound() {
        //Arrange
        NotFoundException expectedMessage = new NotFoundException("No se encontro al usuario");
        when(userRepository.findUserByUserId(1)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.newFollow(1, 11));
    }

    @Test
    void followTestUserVendorNotFound() {
        NotFoundException expectedMessage = new NotFoundException("El usuario no es un vendedor");
//        when(userService.get)
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
