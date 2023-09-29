package com.example.ia_foro.model.answer;

import java.time.LocalDateTime;

public record AnswerDTO(
        Long id,
        String mensaje,
        LocalDateTime fecha_creacion,
        String user,
        String topic
) {
    public AnswerDTO(Answer answer) {
        this(
                answer.getAnswer_id(),
                answer.getMensaje(),
                answer.getFecha_creacion(),
                answer.getUser().getNombre(),
                answer.getTopic().getTitulo()
        );
    }
}
