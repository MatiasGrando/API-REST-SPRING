package com.alkemy.disney.disneyApiRest.repository;

import com.alkemy.disney.disneyApiRest.model.PersonajeModel;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonajeRepository extends JpaRepository<PersonajeModel,Integer> {

    List<PersonajeModel> findAll(Specification<PersonajeModel> specification);
}
