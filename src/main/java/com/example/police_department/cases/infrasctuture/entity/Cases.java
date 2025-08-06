package com.example.police_department.cases.infrasctuture.entity;


import com.example.police_department.agents.infrastructure.entity.Agent;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "police_cases")
@Entity
public class Cases {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer case_id;
    
    @Column(name = "titulo")
    private String titulo;
    
    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "Status")
    private String status;
    
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "agente_id",
    referencedColumnName = "agente_id",
    foreignKey = @ForeignKey(name="fk_cases_agent"))
    private Agent agente;
    
    
    
    public enum Status {
        ABERTO, SOLUCIONADO
    }
    
    
    
}
