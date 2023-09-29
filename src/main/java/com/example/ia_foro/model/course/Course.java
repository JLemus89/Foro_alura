package com.example.ia_foro.model.course;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "course")
@Table(name = "courses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Course {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long course_id;
        private String nombre;
        @Enumerated(EnumType.STRING)
        private ECourseCategory categoria;

        public Course(RegisterCourseDTO registerCourseDTO) {
                this.nombre = registerCourseDTO.nombre();
                this.categoria = registerCourseDTO.categoria();
        }

        public Course updateCourse(RegisterCourseDTO registerCourseDTO) {
                if (registerCourseDTO.nombre() != null) {
                        this.nombre = registerCourseDTO.nombre();
                }
                if (registerCourseDTO.categoria() != null) {
                        this.categoria = registerCourseDTO.categoria();
                }
                return this;
        }
}
