package com.senai.controle_epi.repository;


import com.senai.controle_epi.models.EmprestimoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmprestimoRepository extends JpaRepository<EmprestimoModel, Long> {
    List<EmprestimoModel> findByColaboradorIdAndDevolucaoIsNull(Long colaboradorId);
}
