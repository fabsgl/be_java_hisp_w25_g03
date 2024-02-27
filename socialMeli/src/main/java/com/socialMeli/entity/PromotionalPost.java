package com.socialMeli.entity;

import com.socialMeli.dto.request.PostDTO;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class PromotionalPost extends Post{
    Boolean hasPromo;
    Double discount;

    public PromotionalPost(Integer id, PostDTO postDTO) {
        super(id ,postDTO.getDate(), postDTO.getProduct().getId(), postDTO.getCategory(), postDTO.getPrice(),postDTO.getUserId());
        this.hasPromo = postDTO.getHasPromo();
        this.discount = postDTO.getDiscount();
    }
}