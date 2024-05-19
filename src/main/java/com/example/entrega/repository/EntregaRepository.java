package com.example.entrega.repository;

import com.example.entrega.entity.Entrega;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface EntregaRepository extends CassandraRepository<Entrega, UUID> {
}
