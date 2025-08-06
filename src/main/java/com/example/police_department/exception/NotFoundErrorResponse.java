package com.example.police_department.exception;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta para erros de busca (HTTP 404)")
public record NotFoundErrorResponse(
        @Schema(description = "HTTP status code", example = "404")
        int status,
        
        @Schema(description = "Tipo do erro", example = "NOT_FOUND")
        String error,
        
        @Schema(description = "Mensagem resumida", example = "Erro ao encontrar entidade no banco de dados")
        String message
) {
}
