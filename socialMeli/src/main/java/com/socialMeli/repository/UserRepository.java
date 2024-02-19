package com.socialMeli.repository;

import com.socialMeli.entity.User;
import com.socialMeli.entity.UserType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements IUserRepository {

    private List<User> userBd;

    public UserRepository() {
        this.userBd = new ArrayList<>();
        userBd.add(new User(1,"agustin",List.of(2),List.of(2), UserType.VENDOR));
        userBd.add(new User(2,"agustin",List.of(1),List.of(1), UserType.VENDOR));
    }

    @Override
    public Optional<User> findUserByUserId(Integer userId) {
        return userBd.stream().filter(user -> user.getId().equals(userId)).findFirst();
    }

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
