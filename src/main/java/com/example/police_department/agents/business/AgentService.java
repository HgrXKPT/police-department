package com.example.police_department.agents.business;
import com.example.police_department.agents.infrastructure.entity.Agent;
import com.example.police_department.agents.infrastructure.repositories.AgentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.police_department.agents.dto.*;
import org.modelmapper.ModelMapper;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class AgentService {
    
    private final AgentRepository agentRepository;
    
    private final ModelMapper modelMapper;
    
    @Transactional(readOnly = true)
    
    public List<AgentResponseDto> getAll(){
        
         return agentRepository.findAll()
                 .stream()
                 .map(agents->modelMapper.map(agents,AgentResponseDto.class))
                 .toList();
    }
    
    @Transactional(readOnly = true)
    
    public AgentResponseDto findById(Integer id) {
        return modelMapper.map(
                agentRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException("Agente não encontrado")),
                AgentResponseDto.class);
    }
    
    public AgentResponseDto addAgent(AgentRequestDto agent){
        
        
        Agent newAgent = modelMapper.map(agent, Agent.class);
        Agent savedAgent = agentRepository.save(newAgent);
        
        return modelMapper.map(savedAgent, AgentResponseDto.class);
    }
    
    public AgentResponseDto updateAgent(Integer id, AgentRequestDto agentDetails, boolean partial){
        
        Agent existingAgent = agentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Agente a ser atualizado não foi encontrado")
        );
        
        modelMapper.getConfiguration().setSkipNullEnabled(partial);
        
        if (partial) {
            modelMapper.map(agentDetails, existingAgent);
        }else{
            Agent updatedAgent = modelMapper.map(agentDetails, Agent.class);
            updatedAgent.setAgente_id(existingAgent.getAgente_id());
            existingAgent = updatedAgent;
        }
        
        Agent savedAgent = agentRepository.saveAndFlush(existingAgent);
        
        return  modelMapper.map(savedAgent, AgentResponseDto.class);
    }
    
    public void deleteAgent(Integer id){
        if(agentRepository.existsById(id)){
            throw new EntityNotFoundException("Agente não encontrado");
        }
        agentRepository.deleteById(id);
    }
}
