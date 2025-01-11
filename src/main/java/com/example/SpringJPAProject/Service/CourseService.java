package com.example.SpringJPAProject.Service;

import com.example.SpringJPAProject.Entity.Course;
import com.example.SpringJPAProject.Repository.AuthorRepository;
import com.example.SpringJPAProject.Repository.CourseRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/course")
public class CourseService {
    private CourseRepository courseRepository;
    private AuthorRepository authorRepository;
    public CourseService(CourseRepository courseRepository, AuthorRepository authorRepository) {
        this.courseRepository = courseRepository;
        this.authorRepository = authorRepository;
    }

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public Course getCourseByCourseId(String courseId){
        return courseRepository.findById(courseId).orElseThrow();
    }
}
