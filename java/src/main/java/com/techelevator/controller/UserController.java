package com.techelevator.controller;


import com.techelevator.dao.CollectionDao;
import com.techelevator.dao.PokemonDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Collection;
import com.techelevator.model.Pokemon;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class UserController {

    private UserDao userDao;


    public UserController( UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(path = "/users/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable("id") int id){
        return userDao.getUserById(id);
    }

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<User> getAllUsers(){
        return userDao.findAll();
    }

}
