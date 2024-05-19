package com.example.entrega.integration;

import com.example.entrega.service.EntregaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EntregaConsumer {

    @Autowired
    private EntregaService entregaService;

    @RabbitListener(queues = {"pedido-request-queue"})
    public void pedidoSolicitado(@Payload Message message) throws JSONException, JsonProcessingException {
        String payload = String.valueOf(message.getPayload());
        entregaService.pedidoSolicitado(payload);
    }
    @RabbitListener(queues = {"pedido-entreganaofinalizada-queue"})
    public void pedidoNaoEntregue(@Payload Message message) throws JSONException, JsonProcessingException {
        String payload = String.valueOf(message.getPayload());
        //entregaService.pedidoNaoEntregue(payload);
    }

    @RabbitListener(queues = {"pedido-entregafinalizada-queue"})
    public void pedidoEntregue(@Payload Message message) throws JSONException, JsonProcessingException {
        String payload = String.valueOf(message.getPayload());
        //entregaService.pedidoEntregue(payload);
    }

}
