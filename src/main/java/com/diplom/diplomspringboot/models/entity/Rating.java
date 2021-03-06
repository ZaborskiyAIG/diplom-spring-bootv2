package com.diplom.diplomspringboot.models.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private Integer mark;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Criteria.class)
    @JoinColumn(name = "criteria_id", nullable = false)
    private Criteria criteria;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Demands.class)
    @JoinColumn(name = "demand_id", nullable = false)
    private Demands demands;
}


