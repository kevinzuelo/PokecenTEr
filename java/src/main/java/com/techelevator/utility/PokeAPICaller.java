package com.techelevator.utility;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.techelevator.model.Collection;
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
        if(pokemon.getIsShiny()){
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
        if(pokemon.getIsShiny()){
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

    public static String getCollectionImageUrl(Collection collection){
        String collectionGame = collection.getGame();

        if(collectionGame.equalsIgnoreCase("red")){
            return "https://archives.bulbagarden.net/media/upload/8/80/Red_EN_boxart.png";
        }else if(collectionGame.equalsIgnoreCase("blue")){
            return "https://www.mobygames.com/images/covers/l/14426-pokemon-blue-version-game-boy-front-cover.jpg";
        }else if(collectionGame.equalsIgnoreCase("yellow")){
            return "https://archives.bulbagarden.net/media/upload/thumb/9/95/Yellow_EN_boxart.png/598px-Yellow_EN_boxart.png";
        }else if(collectionGame.equalsIgnoreCase("gold")){
            return "https://www.gamespot.com/a/uploads/scale_medium/box/6/1/6/2/1628-6162.jpg";
        }else if(collectionGame.equalsIgnoreCase("silver")){
            return "https://m.media-amazon.com/images/I/81UQlso+frL._SL1500_.jpg";
        }else if(collectionGame.equalsIgnoreCase("crystal")){
            return "https://archives.bulbagarden.net/media/upload/6/60/Crystal_EN_boxart.png";
        }else if(collectionGame.equalsIgnoreCase("ruby")){
            return "https://static.wikia.nocookie.net/leonhartimvu/images/d/d7/Ruby_Beta_EN_boxart.jpg/revision/latest?cb=20180911213958";
        }else if(collectionGame.equalsIgnoreCase("sapphire")){
            return "https://m.media-amazon.com/images/I/61RDD36KWPL._SX342_.jpg";
        }else if(collectionGame.equalsIgnoreCase("firered")){
            return "https://www.mobygames.com/images/covers/l/47702-pokemon-firered-version-game-boy-advance-front-cover.jpg";
        }else if(collectionGame.equalsIgnoreCase("leafgreen")){
            return "https://gamefaqs.gamespot.com/a/box/3/8/7/57387_front.jpg";
        }else if(collectionGame.equalsIgnoreCase("emerald")){
            return "https://archives.bulbagarden.net/media/upload/6/65/Emerald_EN_boxart.jpg";
        }else if(collectionGame.equalsIgnoreCase("diamond")){
            return "https://www.mobygames.com/images/covers/l/341699-pokemon-diamond-version-nintendo-ds-front-cover.jpg";
        }else if(collectionGame.equalsIgnoreCase("pearl")){
            return "https://www.mobygames.com/images/covers/l/223220-pokemon-pearl-version-nintendo-ds-front-cover.jpg";
        }else if(collectionGame.equalsIgnoreCase("platinum")){
            return "https://upload.wikimedia.org/wikipedia/en/c/ca/Pokemon_Platinum.png";
        }else if(collectionGame.equalsIgnoreCase("heartgold")){
            return "https://www.mobygames.com/images/covers/l/465044-pokemon-heartgold-version-nintendo-ds-front-cover.jpg";
        }else if(collectionGame.equalsIgnoreCase("soulsilver")){
            return "https://m.media-amazon.com/images/I/517ZggTk5PL._AC_SY1000_.jpg";
        }else if(collectionGame.equalsIgnoreCase("black")){
            return "https://upload.wikimedia.org/wikipedia/en/e/e1/Pokemon_Black_Box_Artwork.jpg";
        }else if(collectionGame.equalsIgnoreCase("white")){
            return "https://m.media-amazon.com/images/I/51I1tS+6vnL.jpg";
        }else if(collectionGame.equalsIgnoreCase("black 2")){
            return "https://archives.bulbagarden.net/media/upload/3/34/Black_2_EN_boxart.png";
        }else if(collectionGame.equalsIgnoreCase("white 2")){
            return "https://www.mobygames.com/images/covers/l/252043-pokemon-white-version-2-nintendo-ds-front-cover.jpg";
        }else if(collectionGame.equalsIgnoreCase("x")){
            return "https://archives.bulbagarden.net/media/upload/thumb/2/27/X_EN_boxart.png/250px-X_EN_boxart.png";
        }else if(collectionGame.equalsIgnoreCase("y")){
            return "https://m.media-amazon.com/images/I/81ILHAC3VYL._SL1500_.jpg";
        }else if(collectionGame.equalsIgnoreCase("omega ruby")){
            return "https://archives.bulbagarden.net/media/upload/thumb/5/5b/Omega_Ruby_EN_boxart.png/250px-Omega_Ruby_EN_boxart.png";
        }else if(collectionGame.equalsIgnoreCase("alpha sapphire")){
            return "https://archives.bulbagarden.net/media/upload/thumb/f/f2/Alpha_Sapphire_EN_boxart.png/250px-Alpha_Sapphire_EN_boxart.png";
        }else if(collectionGame.equalsIgnoreCase("sun")){
            return "https://archives.bulbagarden.net/media/upload/thumb/0/06/Sun_EN_boxart.png/250px-Sun_EN_boxart.png";
        }else if(collectionGame.equalsIgnoreCase("moon")){
            return "https://gamefaqs.gamespot.com/a/box/7/7/3/595773_front.jpg";
        }else if(collectionGame.equalsIgnoreCase("ultra sun")){
            return "https://m.media-amazon.com/images/I/81YmI-A27RL._SL1500_.jpg";
        }else if(collectionGame.equalsIgnoreCase("ultra moon")){
            return "https://m.media-amazon.com/images/I/813LRbp3EkL._SL1500_.jpg";
        }else if(collectionGame.equalsIgnoreCase("let's go pikachu")){
            return "https://archives.bulbagarden.net/media/upload/a/ae/Lets_Go_Pikachu_EN_boxart.png";
        }else if(collectionGame.equalsIgnoreCase("let's go eevee")){
            return "https://img.pokemondb.net/boxes/lg/lets-go-eevee-large.jpg";
        }else if(collectionGame.equalsIgnoreCase("sword")){
            return "https://archives.bulbagarden.net/media/upload/thumb/4/47/Sword_EN_boxart.png/250px-Sword_EN_boxart.png";
        }else if(collectionGame.equalsIgnoreCase("shield")){
            return "https://archives.bulbagarden.net/media/upload/thumb/8/8d/Shield_EN_boxart.png/250px-Shield_EN_boxart.png";
        }else if(collectionGame.equalsIgnoreCase("brilliant diamond")){
            return "https://gamingbolt.com/wp-content/uploads/2021/05/pokemon-brilliant-diamond-box-image.png";
        }else if(collectionGame.equalsIgnoreCase("shining pearl")){
            return "https://images.nintendolife.com/cbbf113a4ebb7/pokemon-brilliant-diamond-and-shining-pearl-cover.cover_large.jpg";
        }else if(collectionGame.equalsIgnoreCase("legends: arceus")){
            return "https://archives.bulbagarden.net/media/upload/3/3a/Legends_Arceus_EN_boxart.png";
        }else{
            return "https://static.wikia.nocookie.net/pokemon/images/8/87/Pok%C3%A9_Ball.png/revision/latest?cb=20200918005128";
        }
    }

    public static void main(String[] args){
//        Pokemon bulba = new Pokemon();
//        bulba.setSpecies("bulbasaur");
//        bulba.setIsShiny(true);
//        System.out.println(getPokemonImageUrl(bulba));
//        System.out.println(getPokemonSpriteUrl(bulba));
//        System.out.println(getPokemonType(bulba));

        Collection collection = new Collection();
        collection.setGame("brilliant diamond");
        System.out.println(getCollectionImageUrl(collection));
    }
}
