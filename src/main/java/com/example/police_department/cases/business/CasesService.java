package com.example.police_department.cases.business;


import com.example.police_department.agents.infrastructure.entity.Agent;
import com.example.police_department.agents.infrastructure.repositories.AgentRepository;
import com.example.police_department.cases.dto.CasesRequestDto;
import com.example.police_department.cases.dto.CasesResponseDTO;
import com.example.police_department.cases.infrasctuture.entity.Cases;
import com.example.police_department.cases.infrasctuture.repositories.CasesRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class CasesService {
    private final CasesRepository casesRepository;
    private final AgentRepository agentRepository;
    
    private final ModelMapper modelMapper;
    
    
    public List<CasesResponseDTO> getAll(){
        return casesRepository.findAll().stream()
                .map(c -> modelMapper.map(c, CasesResponseDTO.class))
                .toList();
    }
    
    public CasesResponseDTO getById(Integer id){
        return modelMapper.map(
                casesRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException("Caso não foi encontrado")
                ) , CasesResponseDTO.class
        );
    }
    
    public CasesResponseDTO addCase(CasesRequestDto  casesRequestDto){
        
        Agent agente = agentRepository.findById(casesRequestDto.getAgente_id())
                .orElseThrow(() -> new EntityNotFoundException("Agente não encontrado"));
        
        
        Cases cases = Cases.builder()
                .titulo(casesRequestDto.getTitulo())
                .descricao(casesRequestDto.getDescricao())
                .status(casesRequestDto.getStatus())
                .agente(agente)
                .build();
        System.out.println(cases.getAgente());
       
        
        Cases savedCase = casesRepository.save(cases);
        
        return modelMapper.map(savedCase, CasesResponseDTO.class);
        
        //gambiarra comentada📝
        //return modelMapper.map(casesRepository.save(modelMapper.map(casesRequestDto, Cases.class)), CasesResponseDTO.class);
    }
    
    public CasesResponseDTO updateCase(Integer id, CasesRequestDto  casesRequestDto){
        
        Cases cases = casesRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Caso não encontrado")
        );
        
        modelMapper.map(casesRequestDto, cases);
        casesRepository.save(cases);
        cases.setCase_id(id);
        
        return modelMapper.map(cases, CasesResponseDTO.class);
        
    }
    
    public CasesResponseDTO partialUpdateCase(Integer id, CasesRequestDto  casesRequestDto){
        Cases existingCase = casesRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Caso não encontrado")
        );
        
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(casesRequestDto, existingCase);
        Cases savedCase =casesRepository.save(existingCase);
        
        return modelMapper.map(savedCase, CasesResponseDTO.class);
    }
    
    public void deleteCase(Integer id){
        if(casesRepository.existsById(id)){
            throw new EntityNotFoundException("Caso não encontrado");
        }
        casesRepository.deleteById(id);
    }
    
    

}
