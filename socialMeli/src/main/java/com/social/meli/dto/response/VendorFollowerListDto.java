package com.social.meli.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VendorFollowerListDto {
    @JsonProperty("user_id")
    Integer userId;
    @JsonProperty("user_name")
    String userName;
    @JsonProperty("followers")
    List<UserVendorDto> followers;
}