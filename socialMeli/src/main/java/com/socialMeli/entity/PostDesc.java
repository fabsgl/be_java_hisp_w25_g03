package com.socialMeli.entity;

import com.socialMeli.dto.request.PostDTO;
import com.socialMeli.dto.request.PostDescDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class PostDesc extends Post{
    Boolean hasPromo;
    Double discount;
    public PostDesc(Integer id, LocalDate date, Integer productId, Integer category, Double price, Integer userId, Boolean hasPromo, Double discount) {
        super(id, date, productId, category, price, userId);
        this.hasPromo = hasPromo;
        this.discount = discount;
    }

    public PostDesc(Boolean hasPromo, Double discount) {
        this.hasPromo = hasPromo;
        this.discount = discount;
    }

    public PostDesc(Integer id, PostDTO postDto, Boolean hasPromo, Double discount) {
        super(id, postDto);
        this.hasPromo = hasPromo;
        this.discount = discount;
    }
    public PostDesc(Integer id, PostDescDTO postDescDto) {
        super(id, postDescDto);
        this.discount = postDescDto.getDiscount();
        this.hasPromo = postDescDto.getHas_promo();
    }
}
