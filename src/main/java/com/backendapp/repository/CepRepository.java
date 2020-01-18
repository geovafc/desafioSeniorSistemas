package com.backendapp.repository;

import com.backendapp.model.Cep;
import com.backendapp.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CepRepository extends JpaRepository<Cep, Long> {


}
