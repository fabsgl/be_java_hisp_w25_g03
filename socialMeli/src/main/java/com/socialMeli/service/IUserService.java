package com.socialMeli.service;
import com.socialMeli.dto.response.UserUnfollowedDto;

import com.socialMeli.dto.response.MessageDto;
import com.socialMeli.dto.response.VendorFollowerListDto;
import com.socialMeli.dto.response.FollowedListDto;
import com.socialMeli.dto.response.VendorFollowCountDto;

public interface IUserService {
    MessageDto newFollow(Integer userId, Integer userIdToFollow);
    VendorFollowerListDto getVendorFollowers(Integer userId, String order);
    VendorFollowCountDto getFollowerCount(Integer userId);

    FollowedListDto getFollowedList(Integer userId, String order);

    UserUnfollowedDto unfollowUser(Integer userId, Integer userIdToUnfollow);
}
