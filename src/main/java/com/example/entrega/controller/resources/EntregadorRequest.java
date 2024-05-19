package com.example.entrega.controller.resources;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class EntregadorRequest {

    private String nome;
    private String cep;
    private String cpf;
}
