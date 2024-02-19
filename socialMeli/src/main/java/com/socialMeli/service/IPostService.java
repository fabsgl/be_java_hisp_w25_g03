package com.socialMeli.service;

import com.socialMeli.dto.response.PublicationDto;

public interface IPostService {

    PublicationDto obtainLastPublicationsByTheFollowedVendors(Integer userId);
}
