package com.example.ia_foro.model.answer;


import com.example.ia_foro.model.topic.Topic;
import com.example.ia_foro.model.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "answer")
@Table(name = "answers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answer_id;
    private String mensaje;
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDateTime fecha_creacion = LocalDateTime.now();
    private Boolean solucion = false;

    public Answer(String mensaje, Topic topic, User user) {
        this.mensaje = mensaje;
        this.topic = topic;
        this.user = user;
    }

}
