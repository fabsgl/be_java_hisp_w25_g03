package com.socialMeli.service;
import com.socialMeli.dto.response.UserUnfollowedDto;
import com.socialMeli.dto.response.VendorFollowerListDTO;
import com.socialMeli.dto.response.VendorFollowCountDto;


public interface IUserService {
    VendorFollowerListDTO getVendorFollowers(Integer userId);
    VendorFollowCountDto getFollowerCount(Integer userId);

    UserUnfollowedDto unfollowUser(Integer userId, Integer userIdToUnfollow);
}
