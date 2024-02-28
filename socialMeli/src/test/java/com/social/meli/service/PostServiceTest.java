package com.social.meli.service;


import com.social.meli.dto.response.PublicationDto;
import com.social.meli.entity.Post;
import com.social.meli.entity.Product;
import com.social.meli.entity.User;
import com.social.meli.entity.UserType;
import com.social.meli.exception.InvalidDataException;
import com.social.meli.repository.IPostRepository;
import com.social.meli.repository.IProductRepository;
import com.social.meli.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {
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
                1, LocalDate.now(), 1, 1, 120000.0, 8
        );
        Post recoveredPost2 = new Post(
                2, LocalDate.now().minus(twoWeeksPeriod1W), 1, 1, 120000.0, 8
        );
        Product recoveredProduct = new Product(1, "Silla Gamer", "Silla de PC", "Negra", "HyperX", "Sin observaciones");
        when(userRepository.findUserByUserId(1)).thenReturn(Optional.of(recoveredUser));
        when(postRepository.getPostFromTheLastTwoWeeksByUserId(8)).thenReturn(Optional.of(List.of(recoveredPost1, recoveredPost2)));
        when((productRepository.getProductById(anyInt()))).thenReturn(Optional.of(recoveredProduct));
    }

    @Test
    void obtainLastPublicationsByTheFollowedVendorsTest_Ok_defaultOrder() {
        // Arrange

        // Act
        PublicationDto obtainedPublication = postService.obtainLastPublicationsByTheFollowedVendors(1, null);

        // Assert
        verify(userRepository, atLeastOnce()).findUserByUserId(1);
        assertEquals(1, obtainedPublication.getUserId());
    }

    @Test
    void obtainLastPublicationsByTheFollowedVendorsTest_Ok_ascOrder() {
        // Act
        PublicationDto obtainedPublication = postService.obtainLastPublicationsByTheFollowedVendors(1, "date_asc");

        // Assert
        verify(userRepository, atLeastOnce()).findUserByUserId(1);

        assertTrue(obtainedPublication.getPostDTOList().get(0).getDate().isBefore(obtainedPublication.getPostDTOList().get(1).getDate()));
    }

    @Test
    void obtainLastPublicationsByTheFollowedVendorsTest_Ok_descOrder() {
        // Act
        PublicationDto obtainedPublication = postService.obtainLastPublicationsByTheFollowedVendors(1, "date_desc");

        // Assert
        verify(userRepository, atLeastOnce()).findUserByUserId(1);

        assertTrue(obtainedPublication.getPostDTOList().get(0).getDate().isAfter(obtainedPublication.getPostDTOList().get(1).getDate()));
    }

    @Test
    void obtainLastPublicationsByTheFollowedVendorsTest_Throws_InvalidData() {

        assertThrows(InvalidDataException.class, () -> postService.obtainLastPublicationsByTheFollowedVendors(1, "asdas"));
    }
}
