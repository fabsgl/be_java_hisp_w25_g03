package com.socialMeli.service;
import com.socialMeli.dto.response.UserVendorDTO;
import com.socialMeli.dto.response.VendorFollowerListDTO;
import com.socialMeli.dto.response.VendorFollowCountDto;

import java.util.List;

public interface IUserService {
    VendorFollowerListDTO getVendorFollowers(Integer userId);
    VendorFollowCountDto getFollowerCount(Integer userId);
}
