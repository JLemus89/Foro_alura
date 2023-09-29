package com.example.ia_foro.model.course;

public record ResponseCourseDTO(
        Long course_id,
        String nombre,
        String categoria
) {
    public ResponseCourseDTO(Course course) {
        this(
                course.getCourse_id(),
                course.getNombre(),
                course.getCategoria().toString()
        );
    }
}
