package com.socialMeli.controller;

import com.socialMeli.entity.Post;
import com.socialMeli.entity.Product;
import com.socialMeli.repository.IPostRepository;
import com.socialMeli.repository.IProductRepository;
import com.socialMeli.repository.IUserRepository;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.Period;


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
        Period oneWeeksPeriod = Period.ofWeeks(1);
        postRepository.add(
                new Post(1, LocalDate.now(), 1, 1, 12000.0, 8)
        );
        postRepository.add(
                new Post(2, LocalDate.now().minus(oneWeeksPeriod), 2, 1, 12000.0, 8)
        );
        productRepository.add(
                new Product(
                        1, "Silla Gamer", "Gamer", "Racer",
                        "Negra", "Completa"
                )
        );
        productRepository.add(
                new Product(
                        2, "Gabinete 3x", "Gamer", "Negro",
                        "Bequiet", "Completa"
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
    void obtainLastPublicationsByTheFollowedVendorsTest_ok_orderAsc() throws Exception {

        MvcResult mvcResult = this.mockMvc.perform(
                        get("/products/followed/1/list").param("order", "date_asc")
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        JsonPath jsonPath = JsonPath.from(responseBody);

        List<LocalDate> postDates = jsonPath.getList("$.posts[*].date", LocalDate.class);

        // Verificar el orden ascendente
        for (int i = 1; i < postDates.size(); i++) {
            assertTrue(postDates.get(i).isAfter(postDates.get(i - 1)));
        }
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

    @Test
    void addPost_throws() throws Exception {
        String request = "{\n" +
                "    \"user_id\": -1,\n" +
                "    \"date\": \"14-02-2024\",\n" +
                "    \"product\": {\n" +
                "        \"product_id\": 10,\n" +
                "        \"product_name\": \"Silla Gamer\",\n" +
                "        \"type\": \"Gamer\",\n" +
                "        \"brand\": \"Racer\",\n" +
                "        \"color\": \"Black&White\",\n" +
                "        \"notes\": \"Special Edition\"\n" +
                "    },\n" +
                "    \"category\": 100,\n" +
                "    \"price\": 1500.50\n" +
                "}";

        this.mockMvc.perform(
                        post("/products/post")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(request))
                .andDo(print()).andExpect(status().isBadRequest());

    }
}
