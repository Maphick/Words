package com.example.words.ui

class WorcoutListEntyty {
    data class OneWorkoutEntity(
        val workouts: List<OneExerciseEntity>
    )

    data class OneExerciseEntity(
        val bodyPart: String,
        val equipment: String,
        val gifUrl: String,
        val id: String,
        val name: String,
        val target: String,
        val secondaryMuscles: List<String>,
        val instructions: List<String>,
    )

    data class OneSecondaryMusclesEntity(
        val muscle: String
    )
    data class OneInstructionEntity(
        val step: String
    )

}