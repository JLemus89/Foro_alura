package com.example.ia_foro.model.topic;

import jakarta.validation.constraints.NotNull;

public record UpdateTopicDTO(
        @NotNull
        Long topic_id,
        String titulo,
        String mensaje,
        Long user_id,
        Long course_id
) {
}
