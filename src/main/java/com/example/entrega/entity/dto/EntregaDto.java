package com.example.entrega.entity.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record EntregaDto(UUID idCliente, UUID idEntregador, UUID idProduto, String endereco, String cep) {
}
