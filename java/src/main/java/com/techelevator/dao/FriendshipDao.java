package com.techelevator.dao;

import com.techelevator.model.Friendship;
import com.techelevator.model.User;

import java.util.List;

public interface FriendshipDao {

    List<User> getFriendsListByUserId(int userId);

    void addFriend(Friendship friendship);

    void unfriend(Friendship friendship);
}
