package com.socialMeli.repository;

import com.socialMeli.entity.User;
import com.socialMeli.exception.NotFoundException;
import com.socialMeli.entity.UserType;
import com.socialMeli.entity.UserType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository {

    List<User> userBd;

    @Override
    public Optional<User> findUserByUserId(Integer userId) {
        return userBd.stream().filter(user -> user.getId().equals(userId)).findFirst();
    }

    @Override
    public List<User> getAllFollowers(List<Integer> followersIds) {
        return followersIds.stream().map(u -> findUserByUserId(u).get()).toList();
    }

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
    public void unfollowUser(User user, User userToUnfollow) {
        userBd.remove(user);
        userBd.remove(userToUnfollow);
        // Remove userToUnfollow from user's followed list
        List<Integer> followedList = new ArrayList<>(user.getFollowedId());
        followedList.remove(userToUnfollow.getId());
        user.setFollowedId(followedList);

        // Remove user from userToUnfollow's followers list
        List<Integer> followerList = new ArrayList<>(userToUnfollow.getFollowersId());
        followerList.remove(user.getId());
        userToUnfollow.setFollowersId(followerList);

        updateUser(user);
        updateUser(userToUnfollow);
    }

    private void updateUser(User user) {
        userBd.add(user);
    }
}
