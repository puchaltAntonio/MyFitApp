package com.backend.fitapp.domain.exercise.muscle;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class MuscleConverter implements AttributeConverter<Muscle, String> {
    @Override
    public String convertToDatabaseColumn(Muscle muscle) {
        if(muscle == null){
            return null;
        }
        return muscle.getName();
    }

    @Override
    public Muscle convertToEntityAttribute(String name) {
        if(name == null){
            return null;
        }

        return Stream.of(Muscle.values())
                .filter(m -> m.getName().equals(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
