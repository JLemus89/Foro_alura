package com.example.ia_foro.service;


import com.example.ia_foro.model.course.Course;
import com.example.ia_foro.model.topic.RegisterTopicDTO;
import com.example.ia_foro.model.topic.Topic;
import com.example.ia_foro.model.topic.UpdateTopicDTO;
import com.example.ia_foro.model.user.User;
import com.example.ia_foro.repository.CourseRepository;
import com.example.ia_foro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class TopicService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;

    public Topic createTopic(RegisterTopicDTO registerTopicDTO) {
        User user = userRepository.findById(registerTopicDTO.user_id()).orElseThrow(
                () -> new NoSuchElementException("User not found")
        );
        Course course = courseRepository.findById(registerTopicDTO.course_id()).orElseThrow(
                () -> new NoSuchElementException("Course not found")
        );
        var fecha_creacion = LocalDateTime.now();
        Topic topic = new Topic(
                registerTopicDTO.titulo(),
                registerTopicDTO.mensaje(),
                fecha_creacion,
                user,
                course
        );
        return topic;
    }

    public Topic updateTopic(Topic topic, UpdateTopicDTO updateTopicDTO) {
        User user = null;
        Course course = null;

        if(updateTopicDTO.user_id() != null) {
            user = userRepository.findById(updateTopicDTO.user_id()).orElseThrow(
                    () -> new NoSuchElementException("User not found")
            );
        } else {
            user = topic.getUser();
        }
        
        if(updateTopicDTO.course_id() != null) {
            course = courseRepository.findById(updateTopicDTO.course_id()).orElseThrow(
                    () -> new NoSuchElementException("Course not found")
            );
        } else {
            course = topic.getCourse();
        }
        return updateTopic(topic, updateTopicDTO, user, course);
    }

    private Topic updateTopic(Topic topic, UpdateTopicDTO updateTopicDTO, User user, Course course) {
        topic.setTitulo(updateTopicDTO.titulo());
        topic.setMensaje(updateTopicDTO.mensaje());
        topic.setFecha_creacion(LocalDateTime.now());
        topic.setUser(user);
        topic.setCourse(course);
        return topic;
    }

}