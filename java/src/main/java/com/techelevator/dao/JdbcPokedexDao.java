package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPokedexDao implements PokedexDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcPokedexDao(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate;}

    @Override
    public List<String> similarPokemonNames(String prompt) {
        List<String> returnList = new ArrayList<>();
        String sql = "SELECT name FROM pokedex WHERE name LIKE ?;";
        for(int i = prompt.length(); i>2; i--){
            int firstIndex = 0;
            int secondIndex = i;

            while(secondIndex<=prompt.length()){
                SqlRowSet results = jdbcTemplate.queryForRowSet(sql, "%"+prompt.substring(firstIndex,secondIndex)+"%");
                while(results.next()){
                    returnList.add(results.getString("name"));
                }

                firstIndex++;
                secondIndex++;
            }

            if(returnList.size()>0){
                return returnList;
            }
        }
        returnList.add("No Suggestions");
        return returnList;
    }
}
