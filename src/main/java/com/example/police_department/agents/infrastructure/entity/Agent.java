package com.example.police_department.agents.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "agents")
@Entity

public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer agente_id;
    
    @Column(name = "nome", nullable = false)
    private String name;
    
    @Column(name = "dataDeIncorporacao", nullable = false)
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate dataDeIncorporacao;
    
    @Column(name = "cargo", nullable = false)
    private String cargo;
    
    
    
    
}
