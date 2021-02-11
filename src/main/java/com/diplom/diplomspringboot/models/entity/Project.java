package com.diplom.diplomspringboot.models.entity;

import lombok.*;

import javax.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Activity.class)
    @JoinColumn(name = "activity_id", nullable = false)
    private Activity activity;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Formula.class)
    @JoinColumn(name = "formula_id", nullable = false)
    private Formula formula;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Scale.class)
    @JoinColumn(name = "scale_id", nullable = false)
    private Scale scale;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Criteria.class)
    @JoinColumn(name = "criteria_id", nullable = false)
    private Criteria criteria;

}
