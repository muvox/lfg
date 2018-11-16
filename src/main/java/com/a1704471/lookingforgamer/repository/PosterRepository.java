package com.a1704471.lookingforgamer.repository;

import com.a1704471.lookingforgamer.model.Poster;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PosterRepository extends CrudRepository<Poster, Long> {
    List<Poster> findByTitle(String title);
    List<Poster> findByGameId(Long gameId);
}
