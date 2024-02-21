package com.socialMeli.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.socialMeli.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class PromotedVendorDto extends UserVendorDTO{
    @JsonProperty("promo_products_count")
    Integer promoProductsCount;


    public PromotedVendorDto(Integer userId, String userName, Integer promoProductsCount) {
        super(userId, userName);
        this.promoProductsCount = promoProductsCount;
    }

    public PromotedVendorDto(Integer promoProductsCount) {
        this.promoProductsCount = promoProductsCount;
    }

    public PromotedVendorDto(User user, Integer promoProductsCount) {
        super(user);
        this.promoProductsCount = promoProductsCount;
    }
}
