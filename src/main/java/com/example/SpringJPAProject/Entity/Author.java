package com.example.SpringJPAProject.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Table(name = "author")
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Author {
    @Id
    private String username;
    private String name;

    @Column(unique = true)
    private String email;

    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "authorsList")
    private List<Course> courseList;

    public List<Course> getCourseList() {
        return courseList;
    }
}
