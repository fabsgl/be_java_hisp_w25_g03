package com.socialMeli.dto.response;

import com.socialMeli.dto.request.PostDescDTO;
import com.socialMeli.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QTYofDiscountProdByVendorIdDto extends UserVendorDTO {
    List<PostDescDTO> prodsWithDesc;
}
