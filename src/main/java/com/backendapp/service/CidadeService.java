package com.backendapp.service;

import com.backendapp.model.Cep;
import com.backendapp.model.Cidade;
import com.backendapp.model.Estado;
import com.backendapp.repository.CepRepository;
import com.backendapp.repository.CidadeRepository;
import com.backendapp.repository.EstadoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class CidadeService {
    private CidadeRepository repository;
    private CepRepository cepRepository;
    private EstadoRepository estadoRepository;

    CidadeService(CidadeRepository CidadeRepository, EstadoRepository estadoRepository, CepRepository cepRepository) {
        this.repository = CidadeRepository;
        this.estadoRepository = estadoRepository;
        this.cepRepository = cepRepository;
    }


    public Cidade saveCidade(long ibgeEstado, Cidade Cidade){
        Optional<Estado> e = estadoRepository.findById(ibgeEstado);
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

    public Cidade CidadesByCep( String cep){
        return Optional.ofNullable(repository.cidadeByCep(cep))
                .orElseGet(()->cidadeByAPIViaCep(cep));
    }

    private Cidade  cidadeByAPIViaCep(String cep)
    {
        final String uri = "https://viacep.com.br/ws/"+cep+"/json/";
        RestTemplate restTemplate = new RestTemplate();

        Cep result = restTemplate.getForObject(uri, Cep.class);
        result.setCep(result.getCep().replace("-",""));

        return findOrCreateCidadeByNomeAndlinkCepToCidade(result);

    }

    private Cidade  findOrCreateCidadeByNomeAndlinkCepToCidade(Cep cep) {
       return repository.findByNome(cep.getLocalidade()).map(c -> {
           cep.setCidade(c);
           cepRepository.save(cep);
           return c;
       }).orElseGet(()->createCidadeAndCepAndlinkCepToCidade(cep));

    }

    private Cidade
    createCidadeAndCepAndlinkCepToCidade(Cep cep) {
        Optional<Estado> e = estadoRepository.findByUf(cep.getUf());

        Cidade cidade = new Cidade();
        cidade.setNome(cep.getLocalidade());
        cidade.setIbge(Long.valueOf(cep.getIbge()));
        cidade.setEstado(e.get());
        repository.save(cidade);

        cep.setCidade(cidade);
        cepRepository.save(cep);

         return cidade;
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
