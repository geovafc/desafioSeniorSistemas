package com.backendapp.controller;

import com.backendapp.model.Estado;
import com.backendapp.repository.EstadoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping({"/processos"})
public class EstadoController {
    private EstadoRepository repository;

    EstadoController(EstadoRepository estadoRepository) {
        this.repository = estadoRepository;
    }

    /*
    @GetMapping
    public List findAll(){
        return repository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity   processoById(@PathVariable long id){
        return repository.findById(id)
                .map(record ->ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Estado create(@RequestBody Estado estado){
        estado.setDataCriacao(LocalDate.now());
        return repository.save(estado);
    }


    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity<?> delete(@PathVariable long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    */
}
