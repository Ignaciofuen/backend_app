package com.example.demo.repository;

import com.example.demo.model.entity.CarritoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarritoRepository extends JpaRepository<CarritoEntity, Long> {

    Optional<CarritoEntity> findByUsuarioIdusu(Long idusu);
}