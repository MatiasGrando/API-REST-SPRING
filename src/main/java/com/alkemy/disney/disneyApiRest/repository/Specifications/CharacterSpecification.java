package com.alkemy.disney.disneyApiRest.repository.Specifications;

import com.alkemy.disney.disneyApiRest.dto.CharacterFiltersDTO;
import com.alkemy.disney.disneyApiRest.model.PersonajeModel;
import com.alkemy.disney.disneyApiRest.model.TituloModel;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.sql.Types.NULL;

@Component
public class CharacterSpecification {
    public Specification<PersonajeModel> getByFilters(CharacterFiltersDTO characterFiltersDTO){
        return (root,query,criteriaBuilder) ->{

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(characterFiltersDTO.getName())) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("nombre")),
                        "%" + characterFiltersDTO.getName().toLowerCase() + "%"));
            }
            if (StringUtils.hasLength(characterFiltersDTO.getAge())) {
                predicates.add(criteriaBuilder.equal(
                        root.get("edad"),characterFiltersDTO.getAge()));
            }
            if (StringUtils.hasLength(characterFiltersDTO.getWeight())) {
                predicates.add(criteriaBuilder.equal(
                        root.get("peso"),characterFiltersDTO.getWeight()));
            }

            if (!CollectionUtils.isEmpty(characterFiltersDTO.getTitles())) {
                Join<TituloModel, PersonajeModel> join = root.join("titulos", JoinType.INNER);
                Expression<String> titleId = join.get("tituloId");
                predicates.add(titleId.in(characterFiltersDTO.getTitles()));
            }

            // Remove duplucates
            query.distinct(true);

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
