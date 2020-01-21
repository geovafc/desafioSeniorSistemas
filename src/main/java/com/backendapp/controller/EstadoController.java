package com.backendapp.controller;

import com.backendapp.model.Estado;
import com.backendapp.model.Pais;
import com.backendapp.repository.EstadoRepository;
import com.backendapp.repository.PaisRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/estados"})
public class EstadoController {
    private EstadoRepository repository;
    private PaisRepository paisRepository;

    EstadoController(EstadoRepository estadoRepository, PaisRepository paisRepository) {
        this.repository = estadoRepository;
        this.paisRepository = paisRepository;
    }

    @GetMapping
    public List findAll(){
        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity   estadoById(@PathVariable long id){
        return repository.findById(id)
                .map(record ->ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = {"/{ibgePais}"})
    public Estado create(@PathVariable long ibgePais, @RequestBody Estado estado){
        Optional<Pais> p = paisRepository.findById(ibgePais);
        p.get().setIbge(ibgePais);
        estado.setPais(p.get());
        return repository.save(estado);
    }


    @PutMapping(path ="/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Estado estado) {
        return repository.findById(id)
                .map(u -> {
                    u.setNome(estado.getNome());
                    u.setUf(estado.getUf());
                    Estado updated = repository.save(u);
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
