package com.a1704471.lookingforgamer.misc;

import callback.OnSuccessCallback;
import com.a1704471.lookingforgamer.model.Game;

import com.a1704471.lookingforgamer.repository.GameRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import wrapper.IGDBWrapper;
import wrapper.Parameters;
import wrapper.Version;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class IGDBAccess {
    IGDBWrapper wrapper = new IGDBWrapper("252bbc66273907c6a81ad3cf92a8c00c", Version.STANDARD, true);

    ObjectMapper objectMapper = new ObjectMapper();

    private static int paginationCount = 2;

    @Autowired
    private GameRepository gameRepo;

    String jsonia;

    List<Game> games = new ArrayList<Game>();

    Date date = new Date();
    String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);

    @Bean
    public List<Game> getPCGames() {
            Parameters params = new Parameters()
                    .addFields("name,cover") //From api we need a name and url for cover image
                    .addFilter("[release_dates.platform][eq]=6") //Platform 6 = PC games windows
                    .addFilter("[version_parent][not_exists]=1") //exlcude special editions of a game (to recieve less of the same game)
                    .addFilter("[release_dates.date][gt]=2010-01-01") //release date after 2010
                    //.addFilter("[release_dates.date][lt]="+modifiedDate+"") //games that have been released until this date || does not seem to work
                    .addFilter("[rating][gt]=75") //games with rating of over 75/100
                    .addFilter("[game_modes][eq]=2") //includes game mode 2 = multiplayer
                    .addLimit("50");

            wrapper.games(params, new OnSuccessCallback() {
                @Override
                public void onSuccess(JSONArray result) {

                    jsonia = result.toString();

                    System.out.println("Starting games Haku tulokset onSuccess :" + jsonia);

                    List<Game> uusiLista = listGames();
                    games.addAll(uusiLista);
                    System.out.println("Size of 'games' list: "+games.size());

                    for(Game g : games){
                        if(g.getCoverUrl() == null) {
                            g.setCoverUrl("/image/placeholder.png");
                            System.out.println("Replaced cover url for game:  "+g.getName());
                        }
                    }

                    saveGames(games);
                    printGames(games);


                }

                @Override
                public void onError(Exception error) {
                    System.out.println(error);
                }
            });

        return games;
    }

     public List<Game> listGames() {
        List<Game> peliLista = new ArrayList<Game>();
        try {
            peliLista = objectMapper.readValue(jsonia, new TypeReference<List<Game>>(){});
            System.out.println("Size of games "+peliLista.size());
        } catch (Exception e) {
            System.out.println("Error occurred listing games "+e);
        }
        //printGames(peliLista);

        jsonia = "";
        return peliLista;
    }

    public void saveGames(List<Game> gamesList) {
        for(Game g : gamesList){
            gameRepo.save(g);
        }
        System.out.println(gamesList.size()+" is the number of games saved to the repo.");
    }

    public void printGames(List<Game> listOfPrintableGames){
        for(Game g: listOfPrintableGames){
            System.out.println("pelei lista - Games ID: "+g.getId()+" Image url: "+g.getCoverUrl());
        }
    }

}

