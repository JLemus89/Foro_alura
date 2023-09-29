package com.example.ia_foro.model.answer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterAnswerDTO(
        @NotNull
        Long autor,
        @NotNull
        Long topico,
        @NotBlank
        String mensaje
) {
}
