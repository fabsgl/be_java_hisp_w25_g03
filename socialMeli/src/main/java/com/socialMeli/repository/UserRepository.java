package com.socialMeli.repository;

import com.socialMeli.entity.User;
import com.socialMeli.entity.UserType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository implements IUserRepository{

    List<User> userBd;

    public UserRepository() {
        this.userBd = new ArrayList<>(
                List.of(
                        new User(1,namesList[0],new ArrayList<>(),List.of(8,9,10), UserType.CLIENT),
                        new User(2,namesList[1],new ArrayList<>(),List.of(0), UserType.CLIENT),
                        new User(3,namesList[2],new ArrayList<>(),List.of(0), UserType.CLIENT),
                        new User(4,namesList[3],new ArrayList<>(),List.of(0), UserType.CLIENT),
                        new User(5,namesList[4],new ArrayList<>(),List.of(0), UserType.CLIENT),
                        new User(6,namesList[5],new ArrayList<>(),List.of(0), UserType.CLIENT),
                        new User(7,namesList[6],new ArrayList<>(),List.of(0), UserType.CLIENT),
                        new User(8,namesList[7],List.of(1),List.of(0), UserType.VENDOR),
                        new User(9,namesList[8],List.of(1),List.of(0), UserType.VENDOR),
                        new User(10,namesList[9],List.of(1),List.of(0), UserType.VENDOR)
                )
        );
    }
    String[] namesList = {
            "Luciano Gonzalez",
            "Sofia Fernandez",
            "Mateo Rodriguez",
            "Valentina Lopez",
            "Nicolas Martinez",
            "Camila Garcia",
            "Dante Silva",
            "Valeria Ramirez",
            "Tomas Castro",
            "Victoria Acosta"
    };

    @Override
    public User findUserByUserId(Integer userId) {
        return userBd.stream().filter(user -> user.getId().equals(userId)).findFirst().orElse(null);
    }
    @Override
    public List<Integer> findFollowedVendorsByUserId(Integer userId) {
        User user = findUserByUserId(userId);
        if (user != null) {
            return user.getFollowedId();
        } else {
            return new ArrayList<>();
        }
    }
}
