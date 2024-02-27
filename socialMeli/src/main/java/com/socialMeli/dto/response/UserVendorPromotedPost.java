package com.socialMeli.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class UserVendorPromotedPost extends UserVendorDto{
    List<PromotionalPostDto> posts;

    public UserVendorPromotedPost(Integer userId, String userName, List<PromotionalPostDto> posts) {
        super(userId, userName);
        this.posts = posts;
    }
}
