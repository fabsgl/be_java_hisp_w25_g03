package com.socialMeli.socialMeli.service;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.socialMeli.dto.request.PostDTO;
import com.socialMeli.dto.response.ProductDto;
import com.socialMeli.entity.Post;
import com.socialMeli.entity.Product;
import com.socialMeli.entity.User;
import com.socialMeli.entity.UserType;
import com.socialMeli.exception.InvalidDataException;
import com.socialMeli.exception.NotFoundException;
import com.socialMeli.repository.IPostRepository;
import com.socialMeli.repository.IProductRepository;
import com.socialMeli.repository.IUserRepository;
import com.socialMeli.service.IPostService;
import com.socialMeli.service.PostService;
import net.bytebuddy.dynamic.DynamicType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {
    @Mock
    IPostRepository postRepository;
    @Mock
    IUserRepository userRepository;
    @Mock
    IProductRepository productRepository;
    @InjectMocks
    PostService postService;

    @Test
    void addPostOK(){
        //Arrange
        ProductDto product = new ProductDto(2, "productoTest", "test", "beekepers", "black", "-");
        PostDTO postDTO = new PostDTO(1,LocalDate.now(),product,1,1000D);
        Post finalPost = new Post(1, postDTO);
        User user = new User(1, "pepe", List.of(1),List.of(1), UserType.VENDOR);
        Optional<User> optional = Optional.of(user);

        //Act
        when(userRepository.findUserByUserId(1)).thenReturn(optional);
        doNothing().when(productRepository).add(any(Product.class));
        postService.addPost(postDTO);

        //Assert
        Mockito.verify(postRepository, Mockito.times(1)).add(finalPost);
    }

    @Test
    void addPostUserNotFound(){
        ProductDto product = new ProductDto(1, "productoTest", "test", "beekepers", "black", "-");
        PostDTO postDTO = new PostDTO(1,LocalDate.now(),product,1, 1D);

        assertThrows(NotFoundException.class, () -> postService.addPost(postDTO));
    }
    @Test
    void addPostUserNotVendor(){
        //Arrange
        ProductDto product = new ProductDto(2, "productoTest", "test", "beekepers", "black", "-");
        PostDTO postDTO = new PostDTO(1,LocalDate.now(),product,1,1000D);
        User user = new User(1, "pepe", List.of(1),List.of(1), UserType.CLIENT);
        Optional<User> optional = Optional.of(user);

        //Act
        when(userRepository.findUserByUserId(1)).thenReturn(optional);

        //Assert
        assertThrows(InvalidDataException.class, () -> postService.addPost(postDTO));
    }


}
