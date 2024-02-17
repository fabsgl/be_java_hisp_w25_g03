package com.socialMeli.service;

import com.socialMeli.dto.response.MessageDTO;
import com.socialMeli.dto.response.VendorFollowCountDto;
import com.socialMeli.entity.User;
import com.socialMeli.exception.NotFoundException;
import com.socialMeli.exception.UserIsNotVendorException;
import com.socialMeli.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.socialMeli.entity.UserType.VENDOR;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private final IUserRepository userRepository;

    @Override
    public MessageDTO newFollow(Integer userId, Integer userIdToFollow) {

        User user = userRepository.findUserByUserId(userId);
        User userToFollow = userRepository.findUserToFollowById(userIdToFollow);

        if (user != null) {
            if(userToFollow != null) {
                String messageSuccess = "You have followed " + userToFollow;
                return new MessageDTO(messageSuccess);
            }
            else {
                throw new NotFoundException("No se encontró el usuario a seguir");
            }
        }
        else {
            throw new NotFoundException("No se encontró el usuario");
        }
    }

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
}
