package com.example.SpringJPAProject.Repository;

import com.example.SpringJPAProject.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
    // Custom method to find a course by its name
    Optional<Course> findByTitle(String title);
}
