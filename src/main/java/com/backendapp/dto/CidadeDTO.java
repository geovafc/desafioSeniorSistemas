package com.backendapp.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CidadeDTO {

    private String texto;
    private Long idProcesso;
    private Long idUsuario;
}
