package com.backend.fitapp.domain.exercise.muscle;

import lombok.Getter;

@Getter
public enum Muscle {
    ABDOMINALES("ABDOMINALES"),
    ABDUCTORES("ABDUCTORES"),
    ADDUCTORES("ADDUCTORES"),
    ANTEBRAZOS("ANTEBRAZOS"),
    BICEPS("BICEPS"),
    CARDIO("CARDIO"),
    CUELLO("CUELLO"),
    CUERPO_ENTERO("CUERPO_ENTERO"),
    DORSALES("DORSALES"),
    ESPALDA_BAJA("ESPALDA_BAJA"),
    ESPALDA_SUPERIOR("ESPALDA_SUPERIOR"),
    GEMELOS("GEMELOS"),
    GLUTEOS("GLUTEOS"),
    HOMBROS("HOMBROS"),
    ISQUIOTIBIALES("ISQUIOTIBIALES"),
    PECHO("PECHO"),
    TRAPECIO("TRAPECIO"),
    TRICEPS("TRICEPS"),
    OTRO("OTRO");

    private final String name;

    private Muscle(String name){
        this.name = name;
    }

}
