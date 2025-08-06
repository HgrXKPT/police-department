package com.example.police_department.agents.infrastructure.repositories;

import com.example.police_department.agents.infrastructure.entity.Agent;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, Integer> {

}
