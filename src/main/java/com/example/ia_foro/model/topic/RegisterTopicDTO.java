package com.example.ia_foro.model.topic;

import jakarta.validation.constraints.NotBlank;

public record RegisterTopicDTO(
        Long topic_id,
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        Long user_id,
        Long course_id
) {
}
