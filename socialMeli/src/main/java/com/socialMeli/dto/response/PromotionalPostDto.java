package com.socialMeli.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.socialMeli.entity.Post;
import com.socialMeli.entity.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@JsonPropertyOrder({ "id", "userId", "date", "product", "category", "price", "has_promo","discount" })
public class PromotionalPostDto extends PostDto {
     @JsonProperty("has_promo")
     Boolean hasPromo;
     Double discount;

     public PromotionalPostDto(Integer id, Integer userId, LocalDate date, ProductDto product, Integer category, Double price, Boolean has_promo, Double discount) {
          super(id, userId, date, product, category, price);
          this.hasPromo = has_promo;
          this.discount = discount;
     }

     public PromotionalPostDto(Post post, ProductDto productDto, Boolean has_promo, Double discount) {
          super(post.getId(), post.getUserId(), post.getDate(), productDto, post.getCategory(), post.getPrice());
          this.hasPromo = has_promo;
          this.discount = discount;
     }
}
