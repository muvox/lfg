package com.a1704471.lookingforgamer.repository;

import com.a1704471.lookingforgamer.model.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
