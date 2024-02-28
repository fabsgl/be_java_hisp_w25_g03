package com.socialMeli.service;

import com.socialMeli.controller.PostController;
import com.socialMeli.dto.request.PostDTO;
import com.socialMeli.dto.response.ProductDto;
import com.socialMeli.dto.response.PublicationDto;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

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

    @BeforeEach
    void setUp() {
        User recoveredUser = new User(1, "Luciano Gonzalez", new ArrayList<>(), List.of(8, 10), UserType.CLIENT);
        Period twoWeeksPeriod1W = Period.ofWeeks(1);
        Post recoveredPost1 = new Post(
                1, LocalDate.now(),1,1,120000.0,8
        );
        Post recoveredPost2 = new Post(
                2, LocalDate.now().minus(twoWeeksPeriod1W),1,1,120000.0,8
        );
        Product recoveredProduct = new Product(1,"Silla Gamer","Silla de PC","Negra","HyperX","Sin observaciones");
        when(userRepository.findUserByUserId(1)).thenReturn(Optional.of(recoveredUser));
        when(postRepository.getPostFromTheLastTwoWeeksByUserId(8)).thenReturn(Optional.of(List.of(recoveredPost1,recoveredPost2)));
        when((productRepository.getProductById(anyInt()))).thenReturn(Optional.of(recoveredProduct));
    }

    @Test
    void obtainLastPublicationsByTheFollowedVendorsTest_Ok_defaultOrder(){
        // Arrange

        // Act
        PublicationDto obtainedPublication = postService.obtainLastPublicationsByTheFollowedVendors(1,null);

        // Assert
        verify(userRepository, atLeastOnce()).findUserByUserId(1);
        assertEquals(1, obtainedPublication.getUserId());
    }
    @Test
    void obtainLastPublicationsByTheFollowedVendorsTest_Ok_ascOrder(){
        // Act
        PublicationDto obtainedPublication = postService.obtainLastPublicationsByTheFollowedVendors(1,"date_asc");

        // Assert
        verify(userRepository, atLeastOnce()).findUserByUserId(1);

        assertTrue(obtainedPublication.getPostDTOList().get(0).getDate().isBefore(obtainedPublication.getPostDTOList().get(1).getDate()));
    }
    @Test
    void obtainLastPublicationsByTheFollowedVendorsTest_Ok_descOrder(){
        // Act
        PublicationDto obtainedPublication = postService.obtainLastPublicationsByTheFollowedVendors(1,"date_desc");

        // Assert
        verify(userRepository, atLeastOnce()).findUserByUserId(1);

        assertTrue(obtainedPublication.getPostDTOList().get(0).getDate().isAfter(obtainedPublication.getPostDTOList().get(1).getDate()));
    }
    @Test
    void obtainLastPublicationsByTheFollowedVendorsTest_Throws_InvalidData(){

        assertThrows(InvalidDataException.class, () -> postService.obtainLastPublicationsByTheFollowedVendors(1,"asdas"));
    }

    @Test
    void addPostOK(){
        //Arrange
        ProductDto product = new ProductDto(2, "productoTest", "test", "beekepers", "black", "-");
        PostDTO postDTO = new PostDTO(1,LocalDate.now(),product,1,1000D);
        Post finalPost = new Post(1, postDTO);
        User user = new User(1, "pepe", List.of(1),List.of(1), UserType.VENDOR);
        Optional<User> optional = Optional.of(user);

        //Act
        lenient().when(userRepository.findUserByUserId(1)).thenReturn(optional);
        lenient().doNothing().when(productRepository).add(any(Product.class));
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
        lenient().when(userRepository.findUserByUserId(1)).thenReturn(optional);

        //Assert
        assertThrows(InvalidDataException.class, () -> postService.addPost(postDTO));
    }
}
