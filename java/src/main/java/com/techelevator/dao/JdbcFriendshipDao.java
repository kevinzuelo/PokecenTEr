package com.techelevator.dao;

import com.techelevator.model.Friendship;
import com.techelevator.model.Pokemon;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class JdbcFriendshipDao implements FriendshipDao{

    private JdbcTemplate jdbcTemplate;
    private JdbcUserDao userDao;

    public JdbcFriendshipDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = new JdbcUserDao(jdbcTemplate);
    }


    public List<User> getFriendsListByUserId(int userId){
        List<User> friendslist = new ArrayList<>();

        String sql =    "SELECT user_id, username, password_hash, role, email, continent, icon " +
                        "FROM users " +
                        "JOIN friends ON user_id = friend_id " +
                        "WHERE owner_id = ? ";


        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);


        while(results.next()) {
            User user = mapRowToUser(results);
            friendslist.add(user);
        }

        return friendslist;

    };

    public void addFriend (Friendship friendship){
        String sql = "INSERT INTO friends (owner_id, friend_id) " +
                    "VALUES ( ? , ? );";

        jdbcTemplate.update(sql, friendship.getOwnerId(), friendship.getFriendId());


    }



    public void unfriend(Friendship friendship){
        String sql = "DELETE FROM friends WHERE owner_id = ? AND friend_id = ? ";

        jdbcTemplate.update(sql, friendship.getOwnerId(), friendship.getFriendId());
    };



    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setAuthorities(Objects.requireNonNull(rs.getString("role")));
        user.setActivated(true);
        user.setEmail(rs.getString("email"));
        user.setContinent(rs.getString("continent"));
        user.setIconUrl(rs.getString("icon"));
        return user;
    }




}
