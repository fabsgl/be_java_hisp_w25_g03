package com.socialMeli.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.socialMeli.dto.response.ProductDto;
import com.socialMeli.entity.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostDescDTO extends PostDTO {
    Boolean hasPromo;
    Double discount;

//    public PostDescDTO(Integer id, PostDTO postDTO) {
//        super(id ,postDTO.getDate(), postDTO.getProduct().getId(), postDTO.getCategory(), postDTO.getPrice(),postDTO.getUserId());
//        this.hasPromo = postDTO.getHasPromo();
//        this.discount = postDTO.getDiscount();
//    }
//
//    public PostDescDTO(Boolean has_promo, Double discount) {
//        this.hasPromo = has_promo;
//        this.discount = discount;
//    }
}
