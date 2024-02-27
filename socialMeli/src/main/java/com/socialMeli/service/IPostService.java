package com.socialMeli.service;

import com.socialMeli.dto.response.PromotedVendorDto;
import com.socialMeli.dto.response.PublicationDto;

import com.socialMeli.dto.request.PostDTO;
import com.socialMeli.dto.response.UserVendorPromotedPost;


public interface IPostService {

    PublicationDto obtainLastPublicationsByTheFollowedVendors(Integer userId, String order);
    void addPost(PostDTO post);

    PromotedVendorDto obtainTheQuantityOfPromotedPostForOneVendor(Integer userId);

    UserVendorPromotedPost getAllPromotedPostFromAVendor(Integer userId);
}
