package com.example.police_department.exception;

import io.swagger.v3.oas.annotations.media.Schema;
@Schema(description = "Resposta para erros de validação (HTTP 400)")
public record ValidationErrorResponse(
        @Schema(description = "HTTP status code", example = "400")
        int status,
        
        @Schema(description = "Tipo do erro", example = "BAD_REQUEST")
        String error,
        
        @Schema(description = "Mensagem resumida", example = "Erro de validação nos dados enviados")
        String message
) {

    
}