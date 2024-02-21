package com.socialMeli.service;

import com.socialMeli.dto.request.PostDescDTO;
import com.socialMeli.dto.response.PublicationDto;

import com.socialMeli.dto.request.PostDTO;
import com.socialMeli.dto.response.QTYofDiscountProdByVendorIdDto;


public interface IPostService {

    PublicationDto obtainLastPublicationsByTheFollowedVendors(Integer userId, String order);
    void addPost(PostDTO post);
    void addPromoPost(PostDescDTO post);
}
