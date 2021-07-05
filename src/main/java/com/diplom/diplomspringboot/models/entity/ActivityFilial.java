package com.diplom.diplomspringboot.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
