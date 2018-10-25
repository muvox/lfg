package com.a1704471.lookingforgamer;

import callback.OnSuccessCallback;
import com.a1704471.lookingforgamer.domain.Game;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;

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

    String jsonia;

    List<Game> games;

    @Bean
    public List<Game> startingGames(){
        Parameters params = new Parameters().addIds("18472,18228").addFields("name,cover");



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


    public void listofgames() {



        try {
            games = objectMapper.readValue(jsonia, new TypeReference<List<Game>>(){});

            System.out.println("Size of games "+games.size());
            System.out.print("Lists first games cover url"+games.get(0).getCoverUrl());

        } catch (Exception e) {
            System.out.println("Error occurred listing games "+e);


        }

    }

}

