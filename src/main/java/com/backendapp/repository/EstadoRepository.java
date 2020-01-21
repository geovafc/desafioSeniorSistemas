package com.backendapp.repository;

import com.backendapp.model.Cidade;
import com.backendapp.model.Estado;
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
public interface EstadoRepository extends PagingAndSortingRepository<Estado, Long> {
    @Query("FROM Estado p " +
            "WHERE LOWER(p.nome) like %:searchTerm% "+
            "OR LOWER(p.uf) like %:searchTerm% "
    )
    Page<Estado> search(
            @Param("searchTerm") String searchTerm,
            Pageable pageable);

    Optional<Estado> findByUf(String uf);


}
