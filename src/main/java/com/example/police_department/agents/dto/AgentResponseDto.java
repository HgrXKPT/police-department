package com.example.police_department.agents.dto;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgentResponseDto {
    private Integer agente_id;
    private String name;
    private LocalDate dataDeIncorporacao;
    private String cargo;
}
