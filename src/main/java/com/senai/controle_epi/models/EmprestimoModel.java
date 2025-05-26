package com.senai.controle_epi.models;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmprestimoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private ColaboradorModel colaborador;

    @ManyToOne(optional = false)
    private EquipamentoModel equipamento;

    @Column(nullable = false)
    private LocalDate data;

    private LocalDate devolucao;

    private String observacao;
}
