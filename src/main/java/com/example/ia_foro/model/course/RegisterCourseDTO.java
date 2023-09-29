package com.example.ia_foro.model.course;

public record RegisterCourseDTO(
        Long course_id,
        String nombre,
        ECourseCategory categoria
) {
}
