package com.example.SpringJPAProject.Service;

import com.example.SpringJPAProject.Entity.Author;
import com.example.SpringJPAProject.Entity.Course;
import com.example.SpringJPAProject.Exception.AuthorNotFoundException;
import com.example.SpringJPAProject.Exception.CourseNotFoundException;
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

    public Course findById(String courseId){
        return courseRepository.findById(courseId).orElseThrow(()->new CourseNotFoundException("Course with id "+courseId+" not found"));
    }

    public Course addCourse(Course course){
        return courseRepository.save(course);
    }
    public Course addCourse(Course course, String authorUserName){
        Author author = authorRepository.findById(authorUserName)
                .orElseThrow(() -> new AuthorNotFoundException("Author Not Found with username: " + authorUserName));

        if (!course.getAuthorsList().contains(author)) {
            course.getAuthorsList().add(author);
        }
        if (!author.getCourseList().contains(course)) {
            author.getCourseList().add(course);
        }

        // Save the course and author to persist the changes
        courseRepository.save(course);
        authorRepository.save(author);

        return course;
    }

    public Course addAuthorToCourse(Course course, String authorUserName){
        return addCourse(course, authorUserName);
    }
    public List<Author> getAllAuthorsOfCourse(String courseId){
        Course course = courseRepository.findById(courseId).orElseThrow(()->new CourseNotFoundException("Course with id "+courseId+" not found"));
        return course.getAuthorsList();
    }

    public void deleteCourse(String courseId){
        Course course= courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException("Course with id "+courseId+" not found"));

        for(Author author : course.getAuthorsList()){
            author.getCourseList().remove(course);
            authorRepository.save(author);
        }
        courseRepository.delete(course);
    }
    public long count(){
        return courseRepository.count();
    }
}
