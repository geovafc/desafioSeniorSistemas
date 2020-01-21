package com.backendapp.controller;

import com.backendapp.model.Estado;
import com.backendapp.model.Pais;
import com.backendapp.service.EstadoService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping({"/estados"})
public class EstadoController {
    private EstadoService service;

    EstadoController(EstadoService EstadoService) {
        this.service = EstadoService;
    }

    @GetMapping
    public Iterable<Estado> findAll(){
        return service.findAll();
    }

    @GetMapping("/search")
    public Page<Estado> search(
            @RequestParam("searchTerm") String searchTerm,
            @RequestParam(
                    value = "page") int page,
            @RequestParam(
                    value = "size") int size) {
        return service.search(searchTerm, page, size);

    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity   estadoById(@PathVariable long id){
        return service.estadosById(id)
                .map(record ->ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = {"/{ibgePais}"})
    public Estado createByIBGEPais(@PathVariable long ibgePais, @RequestBody Estado estado){
        return service.saveEstado(ibgePais,estado);
    }


    @PutMapping(path ="/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Estado estado) {
        Optional<Estado> updated = service.update(id, estado);
        return Optional.ofNullable(ResponseEntity.ok().body(updated))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity<?> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
