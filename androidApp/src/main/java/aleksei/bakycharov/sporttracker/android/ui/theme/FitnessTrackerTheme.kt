package aleksei.bakycharov.sporttracker.android.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun FitnessTrackerTheme(content: @Composable () -> Unit) {
    val colors = lightColorScheme(
        primary = Blue,
        secondary = Purple,
        background = Background,
        surface = Color.White,
        onPrimary = Color.White,
        onBackground = TextPrimary,
        onSurface = TextPrimary
    )

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}