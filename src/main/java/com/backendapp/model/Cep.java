package com.backendapp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Cep {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private String ibge;
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String unidade;
    private String gia;
    @ManyToOne
    @JoinColumn(name = "ibge_cidade", referencedColumnName = "id_ibge", nullable=false)
    private Cidade cidade;

}
