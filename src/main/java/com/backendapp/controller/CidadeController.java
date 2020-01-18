package com.backendapp.controller;

import com.backendapp.dto.CidadeDTO;
import com.backendapp.model.Cidade;
import com.backendapp.repository.CidadeRepository;
import com.backendapp.repository.EstadoRepository;
import com.backendapp.repository.PaisRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping({"/cidades"})
public class CidadeController {
    private CidadeRepository repository;
    private EstadoRepository estadoRepository;
    private PaisRepository paisRepository;



    CidadeController(CidadeRepository cidadeRepository, EstadoRepository estadoRepository, PaisRepository paisRepository) {
        this.repository = cidadeRepository;
        this.estadoRepository = estadoRepository;
        this.paisRepository = paisRepository;

    }


/*

    @PostMapping
    public ResponseEntity create(@RequestBody CidadeDTO cidadeDTO){
     return estadoRepository.findById(cidadeDTO.getIdProcesso())
                .map(record -> {

                    Cidade cidade = new Cidade();
                    cidade.setDataCriacao(LocalDate.now());
                    cidade.setTexto(cidadeDTO.getTexto());
                    cidade.setPais(paisRepository.findById(cidadeDTO.getIdUsuario()).get());
                    repository.save(cidade);

                    record.setCidade(cidade);
                    estadoRepository.save(record);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
     }

*/

}
