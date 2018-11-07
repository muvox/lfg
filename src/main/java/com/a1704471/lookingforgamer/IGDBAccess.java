package com.a1704471.lookingforgamer;

import callback.OnSuccessCallback;
import com.a1704471.lookingforgamer.domain.Game;

import com.a1704471.lookingforgamer.domain.GameRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import wrapper.IGDBWrapper;
import wrapper.Parameters;
import wrapper.Version;

import java.util.List;

@Component
public class IGDBAccess {
    IGDBWrapper wrapper = new IGDBWrapper("252bbc66273907c6a81ad3cf92a8c00c", Version.STANDARD, true);

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private GameRepository gameRepo;

    String jsonia;

    List<Game> games;


    @Bean
    public List<Game> listAllStartingGames(){
        getPCGames();//TODO litanja metodeja jolla täytetään lista
        return games;
    }
    @Bean
    public List<Game> getPCGames(){

        Parameters params = new Parameters()
                .addFields("name,cover")
                .addFilter("[release_dates.platform][eq]=6")
                .addFilter("[release_dates.date][gt]=2015-01-01")
                .addOffset("0");

        wrapper.games(params, new OnSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {

                jsonia = result.toString();

                System.out.println("Starting games Haku tulokset onSuccess :"+jsonia);

                listofgames();
            }

            @Override
            public void onError(Exception error) {
                System.out.println(error);
            }
        });
        return games;
    }

    public List<Game> startingGames(){
        Parameters params = new Parameters()
                .addFields("*");

        wrapper.games(params, new OnSuccessCallback() {
            @Override
            public void onSuccess(JSONArray result) {

                jsonia = result.toString();

                System.out.println("Starting games Haku tulokset onSuccess :"+jsonia);

                listofgames();
            }

            @Override
            public void onError(Exception error) {
                System.out.println(error);
            }
        });

        return games;

    }


    public List<Game> greatGameList(){
        Parameters params = new Parameters()
                .addFields("name,cover")
                .addOffset("0")
                .addExpand("game,game_modes");

        wrapper.games(params, new OnSuccessCallback(){
            @Override
            public void onSuccess(JSONArray result) {

                jsonia = result.toString();

                System.out.println("Starting games Haku tulokset onSuccess :"+jsonia);

                listofgames();
            }

            @Override
            public void onError(Exception error) {
                System.out.println(error);
            }
        });
        return games;
    }

    public void listofgames() {

        try {
            games = objectMapper.readValue(jsonia, new TypeReference<List<Game>>(){});

            System.out.println("Size of games "+games.size());
            System.out.print("Lists first games cover url"+games.get(0).getCoverUrl());

            saveGames();
        } catch (Exception e) {
            System.out.println("Error occurred listing games "+e);


        }

    }

    public void saveGames() {
        for(Game g : games){
            gameRepo.save(g);
        }
    }

}

