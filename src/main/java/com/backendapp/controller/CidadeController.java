package com.backendapp.controller;

import com.backendapp.dto.CidadeDTO;
import com.backendapp.model.Cidade;
import com.backendapp.model.Estado;
import com.backendapp.repository.CidadeRepository;
import com.backendapp.repository.EstadoRepository;
import com.backendapp.repository.PaisRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/cidades"})
public class CidadeController {
    private CidadeRepository repository;
    private EstadoRepository estadoRepository;



    CidadeController(CidadeRepository cidadeRepository, EstadoRepository estadoRepository) {
        this.repository = cidadeRepository;
        this.estadoRepository = estadoRepository;

    }


    @GetMapping
    public List findAll(){
        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity   cidadeById(@PathVariable long id){
        return repository.findById(id)
                .map(record ->ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = {"/{ibgeEstado}"})
    public Cidade create(@PathVariable long ibgeEstado, @RequestBody Cidade cidade){

        Optional<Estado> e = estadoRepository.findById(ibgeEstado);
        e.get().setIbge(ibgeEstado);
        cidade.setEstado(e.get());
        return repository.save(cidade);
    }


    @PutMapping(path ="/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Cidade cidade) {
        return repository.findById(id)
                .map(u -> {
                    u.setNome(cidade.getNome());
                    Cidade updated = repository.save(u);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity<?> delete(@PathVariable long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
