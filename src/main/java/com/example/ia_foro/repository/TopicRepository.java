package com.example.ia_foro.repository;

import com.example.ia_foro.model.topic.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
