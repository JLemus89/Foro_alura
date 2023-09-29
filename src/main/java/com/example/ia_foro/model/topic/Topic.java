package com.example.ia_foro.model.topic;


import com.example.ia_foro.model.answer.Answer;
import com.example.ia_foro.model.course.Course;
import com.example.ia_foro.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "topic")
@Table(name = "topics")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long topic_id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha_creacion = LocalDateTime.now();
    private EStatusTopic status = EStatusTopic.OPEN;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @OneToMany(mappedBy = "topic")
    private List<Answer> answers = new ArrayList<>();

    public Topic(String titulo, String mensaje, LocalDateTime fecha_creacion, User user, Course course) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fecha_creacion = fecha_creacion;
        this.user = user;
        this.course = course;
    }

    public Topic updateTopic(Topic topic, UpdateTopicDTO updateTopicDTO, User user, Course course) {
        if (updateTopicDTO.titulo() != null) {
            topic.setTitulo(updateTopicDTO.titulo());
        }
        if (updateTopicDTO.mensaje() != null) {
            topic.setMensaje(updateTopicDTO.mensaje());
        }
        this.user = user;
        this.course = course;
        return this;
    }
}
