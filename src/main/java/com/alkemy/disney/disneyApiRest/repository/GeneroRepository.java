package com.alkemy.disney.disneyApiRest.repository;


import com.alkemy.disney.disneyApiRest.model.GeneroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends JpaRepository<GeneroModel,Integer> {



}
