package com.diplom.diplomspringboot.models.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "projects")
    private Set<User> users;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Activity> activities;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Pattern.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "project_pattern", joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "pattern_id"))
    private Set<Pattern> patterns;


    public Project(Long id) {
        this.id = id;
    }
//    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Criteria.class)
//    @JoinColumn(name = "criteria_id", nullable = false)
//    private Criteria criteria;


}
