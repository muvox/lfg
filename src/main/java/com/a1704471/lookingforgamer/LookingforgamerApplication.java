package com.a1704471.lookingforgamer;

import com.a1704471.lookingforgamer.misc.IGDBAccess;
import com.a1704471.lookingforgamer.model.AppUser;
import com.a1704471.lookingforgamer.repository.AppUserRepository;
import com.a1704471.lookingforgamer.repository.GameRepository;
import com.a1704471.lookingforgamer.model.Poster;
import com.a1704471.lookingforgamer.repository.PosterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Date;


@SpringBootApplication
public class LookingforgamerApplication {
	private static Logger log = LoggerFactory.getLogger(LookingforgamerApplication.class);
    private static IGDBAccess data = new IGDBAccess();

	public static void main(String[] args) {
		SpringApplication.run(LookingforgamerApplication.class, args);

	}
    @Bean
    public BCryptPasswordEncoder bencoder() {
        return new BCryptPasswordEncoder();
    }

	@Bean
	public CommandLineRunner insertTestData(PosterRepository repo, GameRepository gameRepo){
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
			Poster poster1 = new Poster(445L,"Rome: Total War - Barbarian Invasion", date1,"LFG Mount Hyjal","Im lv 69 tank, fully geared. looking for a healer and 2 dps to mount hyjal","LeetTank69","PC", 4);
            Poster poster2 = new Poster(445L,"Rome: Total War - Barbarian Invasion",date3,"Looking for some kickass teammates","Im tired of doing solos and am looking for couple of capable shooters to frag some noobs with, hit me up! Looking for some kickass teammates Im tired of doing solos and am looking for couple of capable shooters to frag some noobs with, hit me up! Lo","NastyKillah_GER","PC",3);
            Poster poster3 = new Poster(445L,"Rome: Total War - Barbarian Invasion",date2, "Need help with Vordt","Im currently stuck on the first boss and would really appreciate some help with him.","DidNotGitGud","PS4",1);

			Poster poster4 = new Poster(2352L,"Bloodline Champions", date1,"LFG Mount Hyjal","Im lv 69 tank, fully geared. looking for a healer and 2 dps to mount hyjal","LeetTank69","PC", 4);
			Poster poster5 = new Poster(2352L,"Bloodline Champions",date3,"Looking for some kickass teammates","Im tired of doing solos and am looking for couple of capable shooters to frag some noobs with, hit me up! Looking for some kickass teammates Im tired of doing solos and am looking for couple of capable shooters to frag some noobs with, hit me up! Lo","NastyKillah_GER","PC",3);
			Poster poster6 = new Poster(2352L,"Bloodline Champions",date2, "Need help with Vordt","Im currently stuck on the first boss and would really appreciate some help with him.","DidNotGitGud","PS4",1);

			Poster poster7 = new Poster(9047L,"Vanguard", date1,"LFG Mount Hyjal","Im lv 69 tank, fully geared. looking for a healer and 2 dps to mount hyjal","LeetTank69","PC", 4);
			Poster poster8 = new Poster(9047L,"Vanguard",date3,"Looking for some kickass teammates","Im tired of doing solos and am looking for couple of capable shooters to frag some noobs with, hit me up! Looking for some kickass teammates Im tired of doing solos and am looking for couple of capable shooters to frag some noobs with, hit me up! Lo","NastyKillah_GER","PC",3);
			Poster poster9 = new Poster(9047L,"Vanguard",date2, "Need help with Vordt","Im currently stuck on the first boss and would really appreciate some help with him.","DidNotGitGud","PS4",1);

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
			repo.save(poster4);
			repo.save(poster5);
			repo.save(poster6);
			repo.save(poster7);
			repo.save(poster8);
			repo.save(poster9);


			log.info("Fetching poster info");

			for(Poster poster : repo.findAll()){
				log.info(poster.toString());
			}



		};
	}
}