package com.example.police_department.cases.dto;

import com.example.police_department.cases.infrasctuture.entity.Cases;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CasesRequestDto {
    
    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    
    @NotNull
    private String status;
    @NotNull
    private Integer agente_id;
    
    
    
   
}
