package com.backendapp.controller;

import com.backendapp.model.Cidade;
import com.backendapp.service.CidadeService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/cidades"})
public class CidadeController {
    private CidadeService service;



    CidadeController(CidadeService service) {
        this.service = service;
    }


    @GetMapping
    public Iterable<Cidade> findAll(){
        return service.findAll();
    }

    @GetMapping("/search")
    public Page<Cidade> search(
            @RequestParam("searchTerm") String searchTerm,
            @RequestParam(
                    value = "page") int page,
            @RequestParam(
                    value = "size") int size) {
        return service.search(searchTerm, page, size);

    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity   CidadeById(@PathVariable long id){
        return service.CidadesById(id)
                .map(record ->ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = {"/byCep/{cep}"})
    public ResponseEntity   CidadeByCep(@PathVariable String cep){
        return service.CidadesByCep(cep)
                .map(record ->ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = {"/{ibgeEstado}"})
    public Cidade createByIBGEEstado(@PathVariable long ibgeEstado, @RequestBody Cidade Cidade){
        return service.saveCidade(ibgeEstado,Cidade);
    }


    @PutMapping(path ="/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Cidade Cidade) {
        Optional<Cidade> updated = service.update(id, Cidade);
        return Optional.ofNullable(ResponseEntity.ok().body(updated))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity<?> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
