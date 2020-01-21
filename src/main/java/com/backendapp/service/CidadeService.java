package com.backendapp.service;

import com.backendapp.model.Cidade;
import com.backendapp.model.Estado;
import com.backendapp.repository.CidadeRepository;
import com.backendapp.repository.EstadoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class CidadeService {
    private CidadeRepository repository;
    private EstadoRepository EstadoRepository;

    CidadeService(CidadeRepository CidadeRepository, EstadoRepository EstadoRepository) {
        this.repository = CidadeRepository;
        this.EstadoRepository = EstadoRepository;
    }


    public Cidade saveCidade(long ibgeEstado, Cidade Cidade){
        Optional<Estado> e = EstadoRepository.findById(ibgeEstado);
        e.get().setIbge(ibgeEstado);
        Cidade.setEstado(e.get());
        return repository.save(Cidade);
    }

    public Optional<Cidade> update( long id,
                                  Cidade Cidade) {
        return repository.findById(id)
                .map(u -> {
                    u.setNome(Cidade.getNome());
                    Cidade updated = repository.save(u);
                    return updated;
                });
    }

    public void delete(@PathVariable long id) {
        repository.deleteById(id);
    }

    public Optional<Cidade> CidadesById( long id){
        return repository.findById(id);
    }

    public Page<Cidade> search(
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

    public Iterable<Cidade> findAll(){
        return repository.findAll();
    }

}
