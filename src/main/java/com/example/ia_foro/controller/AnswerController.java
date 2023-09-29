package com.example.ia_foro.controller;

import com.example.ia_foro.model.answer.Answer;
import com.example.ia_foro.model.answer.AnswerDTO;
import com.example.ia_foro.model.answer.RegisterAnswerDTO;
import com.example.ia_foro.model.answer.UpdateAnswerDTO;
import com.example.ia_foro.repository.AnswerRepository;
import com.example.ia_foro.service.AnswerService;
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
@RequestMapping("api/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;
    @Autowired
    private AnswerRepository answerRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<AnswerDTO> registerAnswer(@RequestBody @Valid RegisterAnswerDTO registerAnswerDTO, UriComponentsBuilder uriComponentsBuilder) {
        Answer answer = answerRepository.save(answerService.createAnswer(registerAnswerDTO));
        AnswerDTO answerDTO = new AnswerDTO(answer);
        URI uri = uriComponentsBuilder.path("/api/answer/{id}").buildAndExpand(answer.getAnswer_id()).toUri();
        return ResponseEntity.created(uri).body(answerDTO);
    }

    @GetMapping
    public ResponseEntity<Page<AnswerDTO>> getAnswers(@PageableDefault(size = 10) Pageable paginacion) {
        Page<Answer> answerPage = answerRepository.findAll(paginacion);
        Page<AnswerDTO> answerDTOPage = answerPage.map(AnswerDTO::new);
        return ResponseEntity.ok(answerDTOPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnswerDTO> getAnswerById(@PathVariable Long id) {
        Answer answer = answerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Answer not found")
        );
        AnswerDTO answerDTO = new AnswerDTO(answer);
        return ResponseEntity.ok(answerDTO);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<AnswerDTO> updateAnswer(@PathVariable Long id, @RequestBody @Valid UpdateAnswerDTO updateAnswerDTO) {
        Answer answer = answerRepository.getReferenceById(updateAnswerDTO.answer_id());
        answer = answerService.updateAnswer(answer, updateAnswerDTO);
        return ResponseEntity.ok(new AnswerDTO(answer));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteAnswer(@PathVariable Long id) {
        Answer answer = answerRepository.getReferenceById(id);
        answerRepository.delete(answer);
    }
}
