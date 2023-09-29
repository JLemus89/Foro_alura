package com.example.ia_foro.repository;

import com.example.ia_foro.model.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
