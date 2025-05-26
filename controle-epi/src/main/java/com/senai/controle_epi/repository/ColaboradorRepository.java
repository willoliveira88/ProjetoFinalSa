package com.senai.controle_epi.repository;



import com.senai.controle_epi.models.ColaboradorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ColaboradorRepository extends JpaRepository<ColaboradorModel, Long> {

    Optional<ColaboradorModel> findByEmail(String email);
}
