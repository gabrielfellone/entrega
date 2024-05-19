package com.example.entrega.controller;

import com.example.entrega.entity.Entrega;
import com.example.entrega.service.EntregaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/v1/entrega")
@RequiredArgsConstructor
public class EntregaController {

    private final EntregaService entregaService;

    @GetMapping("recuperaTodasEntregas")
    public ResponseEntity<List<Entrega>> recuperaTodasEntregas() {
        log.info("Buscando todas as entregas na base");
        return ResponseEntity.ok(entregaService.recuperaTodasEntregas());
    }

    @GetMapping("recuperaEntregaPorId")
    public ResponseEntity<Optional<Entrega>> recuperaEntregaPorId(@RequestParam(value = "id", required = true) UUID id) {
        log.info("Buscando Entrega por id {}", id);
        return ResponseEntity.ok(entregaService.recuperaEntregaPorId(id));
    }
}
