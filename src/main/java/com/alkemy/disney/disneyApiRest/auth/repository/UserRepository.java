package com.alkemy.disney.disneyApiRest.auth.repository;

import com.alkemy.disney.disneyApiRest.auth.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Integer> {

    UserModel findByUsername(String username);
}
