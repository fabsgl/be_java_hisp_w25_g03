package com.socialMeli.service;

import com.socialMeli.dto.response.FollowedListDto;
import com.socialMeli.dto.response.VendorDto;
import com.socialMeli.dto.response.VendorFollowCountDto;
import com.socialMeli.entity.User;
import com.socialMeli.exception.NotFoundException;
import com.socialMeli.exception.UserIsNotVendorException;
import com.socialMeli.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.socialMeli.entity.UserType.VENDOR;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private final IUserRepository userRepository;

    @Override
    public VendorFollowCountDto getFollowerCount(Integer userId) {
        User user = userRepository.findUserByUserId(userId);
        if (user == null) {
            throw new NotFoundException("No se encontro al usuario");
        }
        if (!VENDOR.equals(user.getType())) {
            throw new UserIsNotVendorException("El usuario no es un vendedor");
        }
        return new VendorFollowCountDto(user);
    }
    @Override
    public FollowedListDto getFollowedList(Integer userId) {
        User user = userRepository.findUserByUserId(userId);
        if (user == null) {
            throw new NotFoundException("No se encontró al usuario");
        }

        List<Integer> followedIds = userRepository.findFollowedVendorsByUserId(userId);
        List<User> followedUsers = followedIds.stream()
                .map(id -> userRepository.findUserByUserId(id))
                .collect(Collectors.toList());

        List<VendorDto> followedVendors = followedUsers.stream()
                .map(u -> new VendorDto(u.getId(), u.getName()))
                .collect(Collectors.toList());

        return new FollowedListDto(userId, user.getName(), followedVendors);
    }
}
