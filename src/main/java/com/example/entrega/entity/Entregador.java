package com.example.entrega.entity;

import com.example.entrega.controller.resources.EntregadorRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("entregador")
public class Entregador {

    @Column
    @PrimaryKey
    private UUID idEntregador;
    private String nome;
    private String cep;
    private String cpf;


    public Entregador(EntregadorRequest request){
        this.idEntregador = UUID.randomUUID();
        this.nome = request.getNome();
        this.cep = request.getCep();
        this.cpf = request.getCpf();
    }

}
