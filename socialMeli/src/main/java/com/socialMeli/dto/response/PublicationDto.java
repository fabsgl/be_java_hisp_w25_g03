package com.socialMeli.dto.response;

import com.socialMeli.dto.request.PostDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class PublicationDto {
    Integer user_id;
    List<PostDto> postDTOList;
}
