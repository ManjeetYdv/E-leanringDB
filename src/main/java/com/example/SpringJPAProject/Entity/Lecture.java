package com.example.SpringJPAProject.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer lectureId;
    private String lectureName;

    @ManyToOne
    @JoinColumn(name="section_id")
    private Section section;

    @OneToOne
    @JoinColumn(name="resource_id")
    private Resource resource;
}
