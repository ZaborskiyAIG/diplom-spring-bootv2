package com.diplom.diplomspringboot.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "demands")
public class Demands {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private Integer rank;

    @Column
    private String name;

    @Column
    private String color;

    @Column
    private String description;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Activity.class)
    @JoinColumn(name = "activity_id")
    private Activity activity;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = ActivityFilial.class)
    @JoinColumn(name = "activity_filial_id")
    private ActivityFilial activityFilial;
}
