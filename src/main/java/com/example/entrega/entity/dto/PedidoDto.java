package com.example.entrega.entity.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record PedidoDto(UUID id, UUID idCliente, Long idProduto) {
}