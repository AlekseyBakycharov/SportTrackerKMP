package aleksei.bakycharov.sporttracker.android.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen(val route: String) {
    @Serializable
    data object Home : Screen("home")
    @Serializable
    data object Workout : Screen("workout")
    @Serializable
    data object Sleep : Screen("sleep")
    @Serializable
    data object Goals : Screen("goals")
}