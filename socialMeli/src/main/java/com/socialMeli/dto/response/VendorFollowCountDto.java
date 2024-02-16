package com.socialMeli.dto.response;

import com.socialMeli.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@Getter
@Setter
public class VendorFollowCountDto {
    Integer user_id;
    String user_name;
    Integer followers_count;

    public VendorFollowCountDto(User user) {
        this.user_id = user.getId();
        this.user_name = user.getName();
        this.followers_count = user.getFollowersId().size();

    }

}
