package com.a1704471.lookingforgamer.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GameRepository extends CrudRepository<Game, Long> {
    List<Game> findGamesById(Long id);
    Game findGameById(Long id);
}
