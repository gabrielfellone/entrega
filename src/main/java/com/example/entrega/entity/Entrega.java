package com.example.entrega.entity;

import com.example.entrega.entity.dto.EntregaDto;
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
@Table("entrega")
public class Entrega {

    @Column
    @PrimaryKey
    private UUID idEntregador;
    private String cep;
    private String endereco;
    private UUID idCliente;
    private UUID idProduto;


    public Entrega(EntregaDto entregaDto){
        this.idEntregador = entregaDto.idEntregador();
        this.cep = entregaDto.cep();
        this.endereco = entregaDto.endereco();
        this.idCliente = entregaDto.idCliente();
        this.idProduto = entregaDto.idProduto();
    }
}
