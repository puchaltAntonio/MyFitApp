package com.backend.fitapp.domain.exercise.model;

import com.backend.fitapp.domain.workout.model.Workout;
import com.backend.fitapp.domain.exercise.muscle.Muscle;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="exercises")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Muscle primaryMuscle;

    private Muscle secondaryMuscle;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "exerciseSet")
    @JsonIgnore
    @ToString.Exclude
    private Set<Workout> workoutsSet;

    @OneToMany(mappedBy = "exercise")
    @ToString.Exclude
    Set<ExerciseRecord> exerciseRecords;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Exercise exercise = (Exercise) o;
        return getId() != null && Objects.equals(getId(), exercise.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
