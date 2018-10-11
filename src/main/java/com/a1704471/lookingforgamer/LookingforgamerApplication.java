package com.a1704471.lookingforgamer;

import com.a1704471.lookingforgamer.domain.Game;
import com.a1704471.lookingforgamer.domain.Poster;
import com.a1704471.lookingforgamer.domain.PosterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;


@SpringBootApplication
public class LookingforgamerApplication {
	private static Logger log = LoggerFactory.getLogger(LookingforgamerApplication.class);
    private static IGDBAccess data = new IGDBAccess();

	public static void main(String[] args) {
		SpringApplication.run(LookingforgamerApplication.class, args);

        data.startingGames();

/*
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

        RestTemplate restTemplate = new RestTemplate();

        final HttpHeaders headers = new HttpHeaders();
        headers.set("user-key", "252bbc66273907c6a81ad3cf92a8c00c");
        headers.set("Accept","application/json");

        final HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<String> response =  restTemplate.exchange("https://api-2445582011268.apicast.io/games/?fields=name,popularity&order=popularity:desc?filter[game_modes][2]", HttpMethod.GET, entity, String.class);

        System.out.println("Rest fetch :"+response.getBody());
*/

	}

	@Bean
	public CommandLineRunner insertTestData(PosterRepository repo){
		return (args) -> {
			log.info("Inserting couple posters");

			// Create dates to be inserted
			Date date1 = new Date();
			Date date2 = new Date();
			Date date3 = new Date();

			//create users(string) to populate list of users
			String user1 = "MikeMalloy";
			String user2 = "Rube";
			String user3 = "ActionJackson";

			//create posters
			Poster poster1 = new Poster("World of Warcraft", date1,"LFG Mount Hyjal","Im lv 69 tank, fully geared. looking for a healer and 2 dps to mount hyjal","LeetTank69","PC", 4);
            Poster poster2 = new Poster("PlayerUnknowns Battlegrounds",date3,"Looking for some kickass teammates","Im tired of doing solos and am looking for couple of capable shooters to frag some noobs with, hit me up!","NastyKillah_GER","PC",3);
            Poster poster3 = new Poster("Dark Souls 3",date2, "Need help with Vordt","Im currently stuck on the first boss and would really appreciate some help with him.","DidNotGitGud","PS4",1);

            //create lists for users
			ArrayList<String> lista1 = new ArrayList<String>();
			ArrayList<String> lista2 = new ArrayList<String>();
			ArrayList<String> lista3 = new ArrayList<String>();

			//add users to list
			lista1.add(user1);
			lista1.add(user2);

			lista2.add(user2);
			lista2.add(user3);

			lista3.add(user1);

			//add list of users to poster
			poster1.setUsersSigned(lista1);
            poster2.setUsersSigned(lista2);
            poster3.setUsersSigned(lista3);

            //save posters to repo
			repo.save(poster1);
			repo.save(poster2);
			repo.save(poster3);

			log.info("Fetching poster info");

			for(Poster poster : repo.findAll()){
				log.info(poster.toString());
			}
		};
	}
}
