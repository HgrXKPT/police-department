package com.example.police_department;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "Swagger OpenApi", version = "1", description = "API desenvolvida para aprimoramento pessoal"))
@SpringBootApplication
public class PoliceDepartmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoliceDepartmentApplication.class, args);
	}

}
