package com.backendapp.repository;

import com.backendapp.model.Cidade;
import com.backendapp.model.Estado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CidadeRepository extends PagingAndSortingRepository<Cidade, Long> {

    @Query("FROM Cidade c " +
            "WHERE LOWER(c.nome) like %:searchTerm% ")
    Page<Cidade> search(
            @Param("searchTerm") String searchTerm,
            Pageable pageable);

    @Query(value ="SELECT cd.* FROM Cidade cd, Cep cp where cd.id_ibge=cp.ibge_cidade and cp.cep=:cep", nativeQuery = true)
    Cidade cidadeByCep(
            @Param("cep") String cep);

    Optional<Cidade> findByNome(String nome);



}
