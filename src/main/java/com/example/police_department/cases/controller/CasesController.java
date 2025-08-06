package com.example.police_department.cases.controller;


import com.example.police_department.agents.infrastructure.repositories.AgentRepository;
import com.example.police_department.cases.business.CasesService;
import com.example.police_department.cases.dto.CasesRequestDto;
import com.example.police_department.cases.dto.CasesResponseDTO;
import com.example.police_department.exception.NotFoundErrorResponse;
import com.example.police_department.exception.ValidationErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/cases")
@RequiredArgsConstructor
@Tag(name = "CasesControllers")
public class CasesController {
    
    private final CasesService casesService;
    private final AgentRepository agentRepository;
    
    @Operation(summary = "Retorna uma lista paginada de todos os casos policiais cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Casos retornados com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum Caso encontrado",
                    content = @Content(schema = @Schema(hidden = true))),
            
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CasesResponseDTO>> getAll(){
            List<CasesResponseDTO> cases = casesService.getAll();
            if(cases.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(cases);
            
            
       
    }
    
    @Operation(summary = "Retorna um caso em especifico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Caso retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Caso não existe no banco de dados",
                    content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping(value = "/{id}", produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CasesResponseDTO> getById(@PathVariable Integer id){
        try{
            CasesResponseDTO cases = casesService.getById(id);
            return ResponseEntity.ok(cases);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    
    @Operation(summary = "Adiciona um caso novo no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Caso retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Agente não existe",
                    content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Payload incorreto",
                    content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CasesResponseDTO> addCase(@RequestBody @Valid CasesRequestDto casesRequestDto){
        try{
            CasesResponseDTO createdCase =  casesService.addCase(casesRequestDto);
            URI location = URI.create("/cases/" + createdCase.getCase_id());
            return ResponseEntity.created(location).build();
        }catch(EntityNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
        
    }
    
    @Operation(summary = "Atualiza um caso por completo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Caso retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Caso não encontrado no banco de dados",
                    content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Payload incorreto",
            content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
    })
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<CasesResponseDTO> updateCase(
            @PathVariable Integer id, @RequestBody  @Valid CasesRequestDto casesRequestDto) {
        try{
            CasesResponseDTO updatedCase =  casesService.updateCase(id, casesRequestDto);
            return ResponseEntity.ok(updatedCase);
        }catch(EntityNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
    }
    
    @Operation(summary = "Atualiza um caso parcialmente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Caso retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Caso não encontrado no banco de dados",
                    content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Payload incorreto",
                    content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
    })
    
    @PatchMapping(value ="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CasesResponseDTO> partialUpdateCase(
            @PathVariable Integer id, @RequestBody @Valid CasesRequestDto casesRequestDto){
        try{
            CasesResponseDTO updatedCase =  casesService.partialUpdateCase(id, casesRequestDto);
            return ResponseEntity.ok(updatedCase);
        }catch(EntityNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
    }
    
    @Operation(summary = "Atualiza um caso parcialmente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Caso retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Caso não encontrado no banco de dados",
                    content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class))),
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCase(@PathVariable Integer id){
        try{
            casesService.deleteCase(id);
            return  ResponseEntity.noContent().build();
        }catch(EntityNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
    }
    
    
}
