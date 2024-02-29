package com.socialMeli.controller;

import com.socialMeli.entity.Post;
import com.socialMeli.entity.Product;
import com.socialMeli.repository.IPostRepository;
import com.socialMeli.repository.IProductRepository;
import com.socialMeli.repository.IUserRepository;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;


@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    IPostRepository postRepository;

    @Autowired
    IProductRepository productRepository;

    @BeforeEach
    void setUp() {
        postRepository.add(
                new Post(1, LocalDate.now(), 1, 1, 12000.0, 8)
        );
        productRepository.add(
                new Product(
                        1, "Silla Gamer", "Gamer", "Racer",
                        "Negra", "Completa"
                )
        );

    }

    @Test
    void obtainLastPublicationsByTheFollowedVendorsTest_ok() throws Exception {

        this.mockMvc.perform(
                        get("/products/followed/1/list")
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Silla Gamer")));
    }

    @Test
    void obtainLastPublicationsByTheFollowedVendorsTest_throwsNotFound() throws Exception {

        this.mockMvc.perform(
                        get("/products/followed/12/list")
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString("No se encontro al usuario")));
    }

    @Test
    void obtainLastPublicationsByTheFollowedVendorsTest_throwsBadReq() throws Exception {

        this.mockMvc.perform(
                        get("/products/followed/aaa/list")
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().is4xxClientError());
    }

    @Test
    void obtainLastPublicationsByTheFollowedVendorsTest_throws() throws Exception {

        this.mockMvc.perform(
                        get("/products/followed/-1/list")
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().is4xxClientError())
                ;
        ;
    }

    @Test
    void addPost_ok() throws Exception {
        String request = "{\n" +
                "    \"user_id\": 8,\n" +
                "    \"date\": \"14-02-2024\",\n" +
                "    \"product\": {\n" +
                "        \"product_id\": 10,\n" +
                "        \"product_name\": \"Silla Gamer\",\n" +
                "        \"type\": \"Gamer\",\n" +
                "        \"brand\": \"Racer\",\n" +
                "        \"color\": \"Black\",\n" +
                "        \"notes\": \"Special Edition\"\n" +
                "    },\n" +
                "    \"category\": 100,\n" +
                "    \"price\": 1500.50\n" +
                "}";

        this.mockMvc.perform(
                        post("/products/post")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(request))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Post creado")));
    }


}
