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
public class Cidade {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long ibge;
    private String nome;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Cep> ceps;

}
