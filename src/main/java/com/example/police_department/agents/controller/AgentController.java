package com.example.police_department.agents.controller;

import com.example.police_department.agents.business.AgentService;
import com.example.police_department.agents.dto.AgentRequestDto;
import com.example.police_department.agents.dto.AgentResponseDto;
import com.example.police_department.exception.NotFoundErrorResponse;
import com.example.police_department.exception.ValidationErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/agents")
@RequiredArgsConstructor
@Tag(name = "AgentsControllers")
public class AgentController {
    
    private final AgentService agentService;
    
    @Operation(summary = "Retorna uma lista paginada de todos os agentes policiais cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agentes retornados com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum agente encontrado",
            content = @Content(schema = @Schema(hidden = true)),
            headers = @Header(name = "X-Empty-Reason", description = "Indica o motivo da lista vazia"))
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AgentResponseDto>> getAll(){
        List<AgentResponseDto> agents = agentService.getAll();
        if(agents.isEmpty()){
            return ResponseEntity.noContent()
                    .header("X-Empty-Reason", "no_agents_found")
                    .build();
        }
        return ResponseEntity.ok(agents);
    }
    
    @Operation(summary = "Retorna um único agente com base no ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agente retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Agente não encontrado",
                    content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class))),
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AgentResponseDto> getById(@PathVariable Integer id){
        
        try{
            AgentResponseDto agent = agentService.findById(id);
            return ResponseEntity.ok(agent);
        }catch(EntityNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
        
        
    }
    
    @Operation(summary = "Cadastra um novo agente no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agentes retornados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Payload no formato incorreto",
                    content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AgentResponseDto> addAgent( @RequestBody @Valid AgentRequestDto agent){
        
        AgentResponseDto createdAgent = agentService.addAgent(agent);
        
        URI location = URI.create("/agents/" + createdAgent.getAgente_id());
        return ResponseEntity.created(location).body(createdAgent);
    
    }
    
    
    @Operation(summary = "Atualiza um agente por completo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agentes retornados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum agente encontrado",
                    content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Payload no formato incorreto",
            content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
    })
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AgentResponseDto> updateAgent(@PathVariable Integer id, @RequestBody @Valid AgentRequestDto agent){
        try{
            AgentResponseDto updatedAgent = agentService.updateAgent(id, agent, false);
            return ResponseEntity.ok(updatedAgent);
        }catch(EntityNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
        
        
    }
    
    @Operation(summary = "Atualiza um agente parcialmente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agentes retornados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum agente encontrado",
                    content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Payload no formato incorreto",
                    content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
    })
    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AgentResponseDto> updatedAgent(@PathVariable Integer id, @RequestBody AgentRequestDto agent){
        try{
            AgentResponseDto updatedAgent = agentService.updateAgent(id, agent, true);
            return ResponseEntity.ok(updatedAgent);
        }catch(EntityNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
        
    }
    
    @Operation(summary = "Atualiza um agente por completo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Agentes retornados com sucesso",
            content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "Nenhum agente encontrado",
                    content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Payload no formato incorreto",
                    content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
    })
    
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> DeleteAgent(@PathVariable Integer id){
        try{
            agentService.deleteAgent(id);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
    }
    


}
