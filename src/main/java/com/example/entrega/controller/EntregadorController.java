package com.example.entrega.controller;

import com.example.entrega.controller.resources.EntregadorRequest;
import com.example.entrega.entity.Entregador;
import com.example.entrega.service.EntregaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequestMapping("/v1/entregador")
@RequiredArgsConstructor
public class EntregadorController {

    private final EntregaService entregaService;

    @PostMapping("cadastraEntregador")
    public ResponseEntity<String> cadastraEntregador(@RequestBody EntregadorRequest request) {
        log.info("Cadastrando um novo entregador {}", request);
        entregaService.cadastraEntregador(request);
        return ResponseEntity.status(CREATED).body("Cadastro do entregador efetuado com sucesso!");
    }

    @GetMapping("recuperaTodosEntregadores")
    public ResponseEntity<List<Entregador>> recuperaTodosEntregadores() {
        log.info("Buscando todos os entregadores na base");
        return ResponseEntity.ok(entregaService.recuperaTodosEntregadores());
    }
}
