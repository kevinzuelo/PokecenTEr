package com.techelevator.utility;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.techelevator.model.Pokemon;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PokeAPICaller {
    private static RestTemplate restTemplate = new RestTemplate();
    private static String API_URL = "https://pokeapi.co/api/v2/";

    public static String getPokemonType(Pokemon pokemon){
        try {
            ResponseEntity response = restTemplate.getForEntity(API_URL + "/pokemon/" + pokemon.getSpecies(), String.class);
            String[] splitString = ((String) response.getBody()).split(":");
            String types = "";
            for(int i = 0; i< splitString.length; i++){
                if(splitString[i].contains("\"types\"")){
                    for(int j = i; j< splitString.length; j++){
                        if(splitString[j].contains("\"type\"")){
                            String typeString = splitString[j+2];
                            types += typeString.split(",")[0].replace("\"","")+" ";
                        }
                    }
                    break;
                }
            }
            return types;
        }catch(Exception e){
            System.out.println("Invalid Pokemon");
            return "Invalid Pokemon";
        }
    }

    public static String getPokemonImageUrl(Pokemon pokemon){
        String spriteString;
        if(pokemon.isShiny()){
            spriteString = "\"front_shiny\"";
        }else{
            spriteString = "\"front_default\"";
        }

        try{
            ResponseEntity response = restTemplate.getForEntity(API_URL+"/pokemon/"+pokemon.getSpecies(),String.class);
            String[] splitString = ((String) response.getBody()).split(":");

            for(int i = 0; i<splitString.length; i++){
                if(splitString[i].contains("\"sprites\"")){
                    for(int j = i; j<splitString.length; j++){
                        if(splitString[j].contains("\"official-artwork\"")){
                            return ("https:"+ splitString[j+3].split(",")[0].replace("\"","").replace("}",""));
                        }
                    }
                }
            }
        }catch(Exception e){
            return ("No Image Url");
        }
        return ("No Image Url");
    }

    public static String getPokemonSpriteUrl(Pokemon pokemon){
        String spriteString;
        if(pokemon.isShiny()){
            spriteString = "\"front_shiny\"";
        }else{
            spriteString = "\"front_default\"";
        }

        try{
            ResponseEntity response = restTemplate.getForEntity(API_URL+"/pokemon/"+pokemon.getSpecies(),String.class);
            String[] splitString = ((String) response.getBody()).split(":");

            for(int i = 0; i<splitString.length; i++){
                if(splitString[i].contains("\"sprites\"")){
                    for(int j = i; j<splitString.length; j++){
                        if(splitString[j].contains(spriteString)){
                            return ("https:"+ splitString[j+2].split(",")[0].replace("\"",""));
                        }
                    }
                }
            }
        }catch(Exception e){
            return "No Image Url";
        }
        return ("No Image Url");
    }

    public static void main(String[] args){
        Pokemon bulba = new Pokemon();
        bulba.setSpecies("bulbasaur");
        bulba.setShiny(true);
        System.out.println(getPokemonImageUrl(bulba));
        System.out.println(getPokemonSpriteUrl(bulba));
        System.out.println(getPokemonType(bulba));
    }
}
