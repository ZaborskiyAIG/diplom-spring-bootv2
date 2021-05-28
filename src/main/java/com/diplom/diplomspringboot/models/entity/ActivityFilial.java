package com.diplom.diplomspringboot.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "activity_filial")
public class ActivityFilial {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "activityFilial", cascade = CascadeType.REMOVE)
    private List<Demands> demands;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Activity.class, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "activity_id", nullable = true)
    private Activity activity;

}
