package com.socialMeli.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.socialMeli.dto.response.ProductDto;
import com.socialMeli.entity.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PromotionalPostDTO extends PostDTO {
    Boolean hasPromo;
    Double discount;
}