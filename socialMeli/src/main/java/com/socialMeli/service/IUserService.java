package com.socialMeli.service;

import com.socialMeli.dto.response.UserVendorDTO;
import com.socialMeli.dto.response.VendorFollowerListDTO;

import java.util.List;

public interface IUserService {
    VendorFollowerListDTO getVendorFollowers(Integer userId);
}
