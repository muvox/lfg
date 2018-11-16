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

import java.util.ArrayList;
import java.util.List;

@Component
public class IGDBAccess {
    IGDBWrapper wrapper = new IGDBWrapper("252bbc66273907c6a81ad3cf92a8c00c", Version.STANDARD, true);

    ObjectMapper objectMapper = new ObjectMapper();

    private static int paginationCount = 10;

    @Autowired
    private GameRepository gameRepo;

    String jsonia;

    List<Game> games = new ArrayList<Game>();



    @Bean
    public List<Game> getPCGames() {
        for (int i = 0; i < paginationCount; i++) {
            int offset = i*50;
            Parameters params = new Parameters()
                    .addFields("name,cover")
                    .addFilter("[release_dates.platform][eq]=6")
                    .addFilter("[release_dates.date][gt]=2010-01-01")
                    .addFilter("[rating][gt]=75")
                    .addFilter("[game_modes][eq]=1")
                    .addLimit("50")
                    .addOffset(Integer.toString(offset));

            wrapper.games(params, new OnSuccessCallback() {
                @Override
                public void onSuccess(JSONArray result) {

                    jsonia = result.toString();

                    System.out.println("Starting games Haku tulokset onSuccess :" + jsonia);

                    List<Game> uusiLista = listGames();
                    games.addAll(uusiLista);
                    System.out.println("Size of 'games' list: "+games.size());

                    if(games.size()>=250){
                        printGames(games);
                        saveGames(games);
                    }

                }

                @Override
                public void onError(Exception error) {
                    System.out.println(error);
                }
            });
        }
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
        System.out.println(games.size()+" is the number of games saved to the repo.");
    }

    public void printGames(List<Game> listOfPrintableGames){
        for(Game g: listOfPrintableGames){
            System.out.println("pelei lista - Games ID: "+g.getId());
        }
    }

}

