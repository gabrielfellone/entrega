package com.example.entrega.integration;

import com.example.entrega.entity.dto.EntregaDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class EntregaProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void pedidoEntregue(EntregaDto message) throws JsonProcessingException {
        amqpTemplate.convertAndSend(
                "pedido-entregue-sucesso-exchange",
                "pedido-entregue-sucesso-rout-key",
                objectMapper.writeValueAsString(message)
        );
    }

    public void validaEntrega(EntregaDto message) throws JsonProcessingException {
        amqpTemplate.convertAndSend(
                "pedido-clienteentrega-queue",
                "pedido-clienteentrega-rout-key",
                objectMapper.writeValueAsString(message)
        );
    }

}
