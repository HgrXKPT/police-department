package com.example.police_department.agents.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;



import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgentRequestDto {
    @NotBlank
    private String name;
    @NotNull
    private LocalDate dataDeIncorporacao;
    @NotBlank
    private String cargo;
    
}


