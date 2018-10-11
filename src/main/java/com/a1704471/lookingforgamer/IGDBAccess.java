package com.a1704471.lookingforgamer;

import callback.OnSuccessCallback;
import com.a1704471.lookingforgamer.domain.Game;
import org.json.JSONArray;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import wrapper.IGDBWrapper;
import wrapper.Parameters;
import wrapper.Version;

import java.util.ArrayList;
import java.util.List;

public class IGDBAccess {
    IGDBWrapper wrapper = new IGDBWrapper("252bbc66273907c6a81ad3cf92a8c00c", Version.STANDARD, true);

    public void startingGames(){
    Parameters params = new Parameters().addIds("18472,18228").addFields("name,cover");

    wrapper.games(params, new OnSuccessCallback(){
       @Override
       public void onSuccess(JSONArray result){
           for(int i = 0; i < result.length(); i++){
               System.out.println("Tulos :"+result.get(i).toString());

           }

        }

        @Override
        public void onError(Exception error){
           System.out.println(error);
        }
    });


    }
}

