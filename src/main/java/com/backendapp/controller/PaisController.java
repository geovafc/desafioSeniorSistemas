package com.backendapp.controller;

import com.backendapp.model.Pais;
import com.backendapp.service.PaisService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping({"/paises"})
public class PaisController {
    private PaisService service;


    PaisController(PaisService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Pais> findAll(){
        return service.findAll();
    }

    @GetMapping("/search")
    public Page<Pais> search(
            @RequestParam("searchTerm") String searchTerm,
            @RequestParam(
                    value = "page") int page,
            @RequestParam(
                    value = "size") int size) {
        return service.search(searchTerm, page, size);

    }


    @GetMapping(path = {"/{id}"})
    public ResponseEntity   paisesById(@PathVariable long id){
        return service.paisesById(id)
                .map(record ->ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pais create(@RequestBody Pais pais){
        return service.savePais(pais);
    }


    @PutMapping(path ="/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Pais pais) {
        Optional<Pais> updated = service.update(id, pais);
        return Optional.ofNullable(ResponseEntity.ok().body(updated))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity<?> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
