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
@Table(name = "pattern")
public class Pattern {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, targetEntity = Formula.class)
    @JoinColumn(name = "formula_id")
    private Formula formula;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pattern")
    private Set<Criteria> criteria;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, targetEntity = Scale.class)
    @JoinColumn(name = "scale_id")
    private Scale scale;

}
