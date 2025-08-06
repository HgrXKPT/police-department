package com.example.police_department.cases.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CasesResponseDTO {
    
    private Integer case_id;
    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;
    @NotNull
    private Integer agente_id;
    
    
    
    public enum Status {
        ABERTO, SOLUCIONADO
    }
}
