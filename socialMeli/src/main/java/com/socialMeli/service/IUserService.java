package com.socialMeli.service;

import com.socialMeli.dto.response.VendorFollowCountDto;

public interface IUserService {
    VendorFollowCountDto getFollowerCount(Integer userId);
}
