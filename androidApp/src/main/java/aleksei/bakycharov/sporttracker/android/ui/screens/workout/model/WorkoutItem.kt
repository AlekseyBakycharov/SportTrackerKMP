package aleksei.bakycharov.sporttracker.android.ui.screens.workout.model

import aleksei.bakycharov.sporttracker.android.ui.screens.workout.components.Intensity

data class WorkoutItem(
    val name: String,
    val date: String,
    val duration: Int,
    val calories: Int,
    val intensity: Intensity
)