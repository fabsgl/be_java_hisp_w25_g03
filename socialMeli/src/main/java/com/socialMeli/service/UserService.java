package com.socialMeli.service;

import com.socialMeli.dto.response.UserVendorDTO;
import com.socialMeli.dto.response.VendorFollowerListDTO;

import com.socialMeli.dto.response.MessageDTO;
import com.socialMeli.dto.response.VendorFollowCountDto;
import com.socialMeli.entity.User;
import com.socialMeli.exception.NotFoundException;
import com.socialMeli.exception.UserIsNotVendorException;
import com.socialMeli.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

import java.util.List;

import java.util.Optional;

import static com.socialMeli.entity.UserType.VENDOR;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    private final ModelMapper mapper;

    @Override
    public MessageDTO newFollow(Integer userId, Integer userIdToFollow) {

        User user = userRepository.findUserByUserId(userId).orElseThrow(() -> new NotFoundException("No se encontro al usuario"));
        userRepository.findUserByUserId(userIdToFollow).orElseThrow(() -> new NotFoundException("No se encontro el usuario a seguir"));

        boolean userIsMatch = user.getFollowersId().stream().noneMatch(id -> id.equals(userIdToFollow));

        if (userIsMatch) {
            String messageSuccess = "Comenzaste a seguir al usuario: ";
            return new MessageDTO(messageSuccess);
        } else {
            throw new NotFoundException("Ya sigues a este usuario");
        }
    }

    @Override
    public VendorFollowerListDTO getVendorFollowers(Integer userId) {
        Optional<User> userFound = userRepository.findUserByUserId(userId);

        //Excepciones
        if (userFound.isEmpty()) throw new NotFoundException("Vendedor no encontrado");
        if (!VENDOR.equals(userFound.get().getType()))
            throw new UserIsNotVendorException("El usuario no es un vendedor");

        //User es válido
        List<User> followers = userRepository.getAllFollowers(userFound.get().getFollowersId());
        List<UserVendorDTO> followersListDTO = followers
                .stream()
                .map(user -> new UserVendorDTO(user.getId(), user.getName()))
                .toList();
        return new VendorFollowerListDTO(userId, userFound.get().getName(), followersListDTO);
    }


    public VendorFollowCountDto getFollowerCount(Integer userId) {
        User user = userRepository.findUserByUserId(userId)
                .orElseThrow(() -> new NotFoundException("No se encontro al usuario"));
        if (!VENDOR.equals(user.getType())) {
            throw new UserIsNotVendorException("El usuario no es un vendedor");
        }
        return new VendorFollowCountDto(user);
    }
}
