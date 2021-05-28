package com.diplom.diplomspringboot.models.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Project.class)
    @JoinColumn(name = "project_id", nullable = true)
    private Project project;

    @Column
    private String name;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.REMOVE)
    private List<Demands> demands;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.REMOVE)
    private List<ActivityFilial> activityFilials;
}
