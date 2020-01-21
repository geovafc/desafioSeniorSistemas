package com.backendapp.service;

import com.backendapp.model.Estado;
import com.backendapp.model.Pais;
import com.backendapp.repository.EstadoRepository;
import com.backendapp.repository.PaisRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class EstadoService {
    private EstadoRepository repository;
    private PaisRepository paisRepository;

    EstadoService(EstadoRepository estadoRepository, PaisRepository paisRepository) {
        this.repository = estadoRepository;
        this.paisRepository = paisRepository;
    }


    public Estado saveEstado(long ibgePais, Estado estado){
        Optional<Pais> p = paisRepository.findById(ibgePais);
        p.get().setIbge(ibgePais);
        estado.setPais(p.get());
        return repository.save(estado);
    }

    public Optional<Estado> update( long id,
                                  Estado estado) {
        return repository.findById(id)
                .map(u -> {
                    u.setNome(estado.getNome());
                    Estado updated = repository.save(u);
                    return updated;
                });
    }

    public void delete(@PathVariable long id) {
        repository.deleteById(id);
    }

    public Optional<Estado> estadosById( long id){
        return repository.findById(id);
    }

    public Page<Estado> search(
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

    public Iterable<Estado> findAll(){
        return repository.findAll();
    }

}
