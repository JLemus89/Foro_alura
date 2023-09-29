package com.example.ia_foro.model.topic;

import java.time.LocalDateTime;

public record ResponseTopicDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha_creacion,
        EStatusTopic status,
        String user,
        String course
) {
    public ResponseTopicDTO(Topic topic) {
        this(
                topic.getTopic_id(),
                topic.getTitulo(),
                topic.getMensaje(),
                topic.getFecha_creacion(),
                topic.getStatus(),
                topic.getUser().getNombre(),
                topic.getCourse().getNombre()
        );
    }
}
