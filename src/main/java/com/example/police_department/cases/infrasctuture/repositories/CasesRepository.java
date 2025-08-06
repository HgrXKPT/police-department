package com.example.police_department.cases.infrasctuture.repositories;

import com.example.police_department.cases.infrasctuture.entity.Cases;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CasesRepository extends JpaRepository<Cases,Integer> {
}
