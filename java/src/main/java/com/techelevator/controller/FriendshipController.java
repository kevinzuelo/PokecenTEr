package com.techelevator.controller;

import com.techelevator.dao.FriendshipDao;
import com.techelevator.dao.JdbcFriendshipDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Friendship;
import com.techelevator.model.Pokemon;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class FriendshipController {

    private FriendshipDao friendshipDao;

    public FriendshipController( FriendshipDao friendshipDao) {
        this.friendshipDao = friendshipDao;
    }

    @RequestMapping(path = "/users/{id}/friends", method = RequestMethod.GET)
    public List<User> getFriendslistByUserId(@PathVariable("id") int userId) {

        return friendshipDao.getFriendsListByUserId(userId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/friends", method = RequestMethod.POST)
    public void addFriend(@RequestBody Friendship friendship){
        friendshipDao.addFriend(friendship);
    }

    @RequestMapping(path = "/friends", method = RequestMethod.DELETE)
    public void unFriend(@RequestBody Friendship friendship){
        friendshipDao.unfriend(friendship);
    }
}
