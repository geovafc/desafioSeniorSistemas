package com.backendapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Estado {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_ibge")
    private Long ibge;
    private String nome;
    private String uf;
    @ManyToOne
    @JoinColumn(name = "pais", referencedColumnName = "id_ibge")
    private Pais pais;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Cidade> cidades;


}
