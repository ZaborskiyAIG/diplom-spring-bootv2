package com.diplom.diplomspringboot.models.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "scale")
public class Scale {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @Column
    private String firstElement;

    @Column
    private String lastElement;

    @Column
    private String step;

}
