package com.project.eatTogether.infrastructure;

import com.project.eatTogether.domain.RsRestaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface HeaderSearchRepository extends JpaRepository<RsRestaurant, Long> {

    @Query("select lc.rsLocationName, r.rsName, cc.rsCuisineCategoryName " +
            "from RsRestaurant r " +
            "join RsLocationCategories lc on r.rsId = lc.rsRestaurant.rsId " +
            "join RsCuisineCategories cc on r.rsId = cc.rsRestaurant.rsId " +
            "where r.rsId = :rsId " +
            "and (lc.rsLocationName LIKE %:question% " +
            "or cc.rsCuisineCategoryName LIKE %:question%)")
    List<Object[]> custom(@Param("rsId") long rsId, @Param("question") String question);
}
