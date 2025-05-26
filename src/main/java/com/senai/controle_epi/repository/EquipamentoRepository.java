package com.senai.controle_epi.repository;


import com.senai.controle_epi.models.EquipamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipamentoRepository extends JpaRepository<EquipamentoModel, Long> {
}
