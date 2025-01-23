package com.example.SpringJPAProject.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "course")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    private String courseId;
    private String title;
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="authors_courses",
            joinColumns=@JoinColumn(name="course_id"),
            inverseJoinColumns=@JoinColumn(name = "author_id")
    )
    private List<Author> authorsList;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Section> sectionList;

    public List<Author> getAuthorsList() {
        return authorsList;
    }
}
