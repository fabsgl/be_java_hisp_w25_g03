package com.socialMeli.service;

import com.socialMeli.dto.response.MessageDTO;
import com.socialMeli.dto.response.VendorFollowerListDTO;
import com.socialMeli.dto.response.VendorFollowCountDto;

import java.util.List;

public interface IUserService {
    MessageDTO newFollow(Integer userId, Integer userIdToFollow);
    VendorFollowerListDTO getVendorFollowers(Integer userId);
    VendorFollowCountDto getFollowerCount(Integer userId);
}
