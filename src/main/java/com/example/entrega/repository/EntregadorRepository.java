package com.example.entrega.repository;

import com.example.entrega.entity.Entrega;
import com.example.entrega.entity.Entregador;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface EntregadorRepository extends CassandraRepository<Entregador, UUID> {
}
