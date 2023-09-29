package com.example.ia_foro.model.answer;

import lombok.NonNull;

public record UpdateAnswerDTO(
        @NonNull
        Long answer_id,
        String mensaje,
        Long user,
        Long topic
) {
}
