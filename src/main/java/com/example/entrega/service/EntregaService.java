package com.example.entrega.service;

import com.example.entrega.controller.resources.EntregadorRequest;
import com.example.entrega.entity.Entrega;
import com.example.entrega.entity.Entregador;
import com.example.entrega.entity.dto.EntregaDto;
import com.example.entrega.entity.dto.PedidoDto;
import com.example.entrega.exception.EntregadorException;
import com.example.entrega.repository.EntregaRepository;
import com.example.entrega.repository.EntregadorRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class EntregaService {

    @Autowired
    EntregaRepository entregaRepository;

    @Autowired
    EntregadorRepository entregadorRepository;

    final String MOCKENDERECO = "RUA ANTONIO SALAME";
    public void pedidoSolicitado(String payload) throws JSONException, JsonProcessingException {
        JSONObject jsonObject = new JSONObject(payload);
        log.info("Solicitando entrega do pedido {} ", jsonObject );

        String id = jsonObject.getString("id");
        String idCliente = jsonObject.getString("idCliente");
        String idProduto = jsonObject.getString("idProduto");

        solicitarEntrega(
                PedidoDto.builder().
                        id(UUID.fromString(id))
                        .idCliente(UUID.fromString(idCliente))
                        .idProduto(Long.parseLong(idProduto)).build());


    }

    public void solicitarEntrega(PedidoDto pedido){
        log.info("Registrando nova entrega no banco");
        try {
            List<Entregador> entregadores = this.recuperaTodosEntregadores();
            if(!entregadores.isEmpty()) {

                Optional<Entregador> entregadorProximo =
                        validaEntregadorNasProximidades(entregadores, pedido);

                registrarEntrega(entregadorProximo, pedido);

            } else {
                throw new EntregadorException("Lista de entregadores vazia!");
            }

        } catch (EntregadorException e) {
            throw new EntregadorException("Erro ao recuperar entregadores na base!");
        }
    }

    public Optional<Entregador> validaEntregadorNasProximidades(List<Entregador> entregadores,
                                                                PedidoDto pedido){
        log.info("Logica para encontrar um entregador com mesmo cep do pedido");

        //ToDo Criar logica para get no pedido e verificar cep com entregador na base

        return entregadores.stream().findFirst();

    }

    public void cadastraEntregador(EntregadorRequest request){
        log.info("Cadastrando um novo entregador na base {} ", request );
        try {
            entregadorRepository.save(new Entregador(request));
        } catch (EntregadorException e) {
            throw new EntregadorException("Erro ao salvar o entregador na base!");
        }
    }

    public List<Entregador> recuperaTodosEntregadores(){
        log.info("Recuperando todos os entregadores na base " );
        try {
            return entregadorRepository.findAll();
        } catch (EntregadorException e) {
            throw new EntregadorException("Erro ao encontrar entregadores na base!");
        }
    }

    public Optional<Entregador> recuperaPorIdEntregador(UUID idEntregador){
        log.info("Buscando entregador na base por ID  {} ", idEntregador );
        try {
            return entregadorRepository.findById(idEntregador);
        } catch (EntregadorException e) {
            throw new EntregadorException("Erro ao encontrar entregadores na base!");
        }
    }


    public List<Entrega> recuperaTodasEntregas(){
        log.info("Recuperando todos as entregas na base " );
        try {
            return entregaRepository.findAll();
        } catch (EntregadorException e) {
            throw new EntregadorException("Erro ao encontrar entregas na base!");
        }
    }

    public void registrarEntrega(Optional<Entregador> entregador,
                                 PedidoDto pedido){

        Entrega entrega = new Entrega(EntregaDto.builder()
                .cep(entregador.get().getCep())
                .idEntregador(entregador.get().getIdEntregador())
                .endereco(MOCKENDERECO)
                .idProduto(pedido.id())
                .idCliente(pedido.idCliente()).build());

        log.info("Registrando a entrega na base... {} " , entrega );

        try {
             entregaRepository.save(entrega);
        } catch (EntregadorException e) {
            throw new EntregadorException("Erro ao registrar a entrega na base!");
        }

    }

    public Optional<Entrega> recuperaEntregaPorId(UUID idEntregador){
        log.info("Buscando entrega por id do entregador na base por ID  {} ", idEntregador );
        try {
            return entregaRepository.findById(idEntregador);
        } catch (EntregadorException e) {
            throw new EntregadorException("Erro ao encontrar entrega na base!");
        }
    }

}
