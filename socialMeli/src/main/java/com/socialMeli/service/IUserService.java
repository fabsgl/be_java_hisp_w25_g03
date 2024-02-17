package com.socialMeli.service;

import com.socialMeli.dto.response.MessageDTO;
import com.socialMeli.dto.response.VendorFollowCountDto;

public interface IUserService {
    MessageDTO newFollow(Integer userId, Integer userIdToFollow);
    VendorFollowCountDto getFollowerCount(Integer userId);
}
