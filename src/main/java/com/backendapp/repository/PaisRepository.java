package com.backendapp.repository;

import com.backendapp.model.Pais;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaisRepository extends PagingAndSortingRepository<Pais, Long> {
    @Query("FROM Pais p " +
            "WHERE LOWER(p.nome) like %:searchTerm% ")
    Page<Pais> search(
            @Param("searchTerm") String searchTerm,
            Pageable pageable);


}
