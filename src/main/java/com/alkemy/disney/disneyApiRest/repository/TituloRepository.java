package com.alkemy.disney.disneyApiRest.repository;

import com.alkemy.disney.disneyApiRest.model.TituloModel;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TituloRepository extends JpaRepository<TituloModel,Integer> {
    List<TituloModel> findAll(Specification<TituloModel> specification);
}
