package com.socialMeli.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.socialMeli.entity.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class PostDescDTO extends PostDTO {
    Boolean has_promo;
    Double discount;
}
