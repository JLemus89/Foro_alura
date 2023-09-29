package com.example.ia_foro.controller;

import com.example.ia_foro.model.topic.RegisterTopicDTO;
import com.example.ia_foro.model.topic.ResponseTopicDTO;
import com.example.ia_foro.model.topic.Topic;
import com.example.ia_foro.model.topic.UpdateTopicDTO;
import com.example.ia_foro.repository.TopicRepository;
import com.example.ia_foro.service.TopicService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/api/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private TopicRepository topicRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseTopicDTO> registerTopic(@RequestBody @Valid RegisterTopicDTO registerTopicDTO, UriComponentsBuilder uriComponentsBuilder) {
        Topic topic = topicRepository.save(topicService.createTopic(registerTopicDTO));
        ResponseTopicDTO responseTopicDTO = new ResponseTopicDTO(topic);
        URI uri = uriComponentsBuilder.path("/api/topics/{id}").buildAndExpand(topic.getTopic_id()).toUri();
        return ResponseEntity.created(uri).body(responseTopicDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ResponseTopicDTO>> getTopics(@PageableDefault(size = 10) Pageable paginacion) {
        Page<Topic> topicPage = topicRepository.findAll(paginacion);
        Page<ResponseTopicDTO> responseTopicDTOPage = topicPage.map(ResponseTopicDTO::new);
        return ResponseEntity.ok(responseTopicDTOPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseTopicDTO> getTopicById(@PathVariable Long id) {
        Topic topic = topicRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Topic not found")
        );
        ResponseTopicDTO responseTopicDTO = new ResponseTopicDTO(topic);
        return ResponseEntity.ok(responseTopicDTO);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseTopicDTO> updateTopic(@PathVariable Long id, @RequestBody @Valid UpdateTopicDTO updateTopicDTO) {
        Topic topic = topicRepository.getReferenceById(updateTopicDTO.topic_id());
        topic = topicService.updateTopic(topic, updateTopicDTO);
        return ResponseEntity.ok(new ResponseTopicDTO(topic));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTopic(@PathVariable Long id) {
        Topic topic = topicRepository.getReferenceById(id);
        topicRepository.delete(topic);
        return ResponseEntity.ok(new ResponseTopicDTO(topic));
    }

}
