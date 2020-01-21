package com.backendapp.service;

import com.backendapp.model.Pais;
import com.backendapp.repository.PaisRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class PaisService {
    private PaisRepository repository;

    PaisService(PaisRepository repository) {
        this.repository = repository;
    }


    public Pais savePais(Pais pais){
        return repository.save(pais);
    }

    public Optional<Pais> update( long id,
                                  Pais pais) {
        return repository.findById(id)
                .map(u -> {
                    u.setNome(pais.getNome());
                    Pais updated = repository.save(u);
                    return updated;
                });
    }

    public void delete(@PathVariable long id) {
        repository.deleteById(id);
    }

    public Optional<Pais> paisesById( long id){
        return repository.findById(id);
    }

    public Page<Pais> search(
            String searchTerm,
            int page,
            int size) {
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "nome");

        return repository.search(
                searchTerm.toLowerCase(),
                pageRequest);
    }

    public Iterable<Pais> findAll(){
        return repository.findAll();
    }

}
