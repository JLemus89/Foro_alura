package com.example.ia_foro.controller;

import com.example.ia_foro.model.course.Course;
import com.example.ia_foro.model.course.RegisterCourseDTO;
import com.example.ia_foro.model.course.ResponseCourseDTO;
import com.example.ia_foro.repository.CourseRepository;
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
@RequestMapping("api/course")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseCourseDTO> rgisterCourse(@RequestBody @Valid RegisterCourseDTO registerCourseDTO, UriComponentsBuilder uriComponentsBuilder) {
        Course course = courseRepository.save(new Course(registerCourseDTO));
        ResponseCourseDTO responseCourseDTO = new ResponseCourseDTO(course);
        URI uri = uriComponentsBuilder.path("/api/course/{id}").buildAndExpand(course.getCourse_id()).toUri();
        return ResponseEntity.created(uri).body(responseCourseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ResponseCourseDTO>> getCourses(@PageableDefault(size = 10) Pageable paginacion) {
        Page<Course> coursePage = courseRepository.findAll(paginacion);
        Page<ResponseCourseDTO> responseCourseDTOPage = coursePage.map(ResponseCourseDTO::new);
        return ResponseEntity.ok(responseCourseDTOPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCourseDTO> getCourseById(@PathVariable Long id) {
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Course not found")
        );
        ResponseCourseDTO responseCourseDTO = new ResponseCourseDTO(course);
        return ResponseEntity.ok(responseCourseDTO);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseCourseDTO> updateCourse(@PathVariable Long id, @RequestBody @Valid RegisterCourseDTO registerCourseDTO) {
        Course course = courseRepository.getReferenceById(registerCourseDTO.course_id());
        course.updateCourse(registerCourseDTO);
        return ResponseEntity.ok(new ResponseCourseDTO(course));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteCourse(@PathVariable Long id) {
        courseRepository.deleteById(id);
    }
}
