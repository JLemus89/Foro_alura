package com.example.ia_foro.service;

import com.example.ia_foro.model.answer.Answer;
import com.example.ia_foro.model.answer.RegisterAnswerDTO;
import com.example.ia_foro.model.answer.UpdateAnswerDTO;
import com.example.ia_foro.model.topic.Topic;
import com.example.ia_foro.model.user.User;
import com.example.ia_foro.repository.AnswerRepository;
import com.example.ia_foro.repository.TopicRepository;
import com.example.ia_foro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AnswerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    public Answer createAnswer(RegisterAnswerDTO registerAnswerDTO) {
        User user = userRepository.findById(registerAnswerDTO.autor()).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        Topic topic = topicRepository.findById(registerAnswerDTO.topico()).orElseThrow(
                () -> new RuntimeException("Topic not found")
        );
        return new Answer(
                registerAnswerDTO.mensaje(),
                topic,
                user
        );
    }

    public Answer updateAnswer(Answer answer, UpdateAnswerDTO updateAnswerDTO) {
            if (updateAnswerDTO.user() != null) {
                User user = userRepository.findById(updateAnswerDTO.user()).orElseThrow(
                        () -> new RuntimeException("User not found")
            );
                answer.setUser(user);
            }
            if (updateAnswerDTO.topic() != null) {
                Topic topic = topicRepository.findById(updateAnswerDTO.topic()).orElseThrow(
                        () -> new RuntimeException("Topic not found")
                );
                answer.setTopic(topic);
            }
            if (updateAnswerDTO.mensaje() != null) {
                answer.setMensaje(updateAnswerDTO.mensaje());
            }
            return answer;
    }
}
