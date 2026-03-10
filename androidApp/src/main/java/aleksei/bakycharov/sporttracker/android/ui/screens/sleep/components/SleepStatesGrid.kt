package aleksei.bakycharov.sporttracker.android.ui.screens.sleep.components

import aleksei.bakycharov.sporttracker.android.ui.components.StatCard
import aleksei.bakycharov.sporttracker.android.ui.theme.Blue
import aleksei.bakycharov.sporttracker.android.ui.theme.BlueBg
import aleksei.bakycharov.sporttracker.android.ui.theme.FitnessTrackerTheme
import aleksei.bakycharov.sporttracker.android.ui.theme.Orange
import aleksei.bakycharov.sporttracker.android.ui.theme.OrangeBg
import aleksei.bakycharov.sporttracker.android.ui.theme.Purple
import aleksei.bakycharov.sporttracker.android.ui.theme.PurpleBg
import aleksei.bakycharov.sporttracker.android.ui.theme.Yellow
import aleksei.bakycharov.sporttracker.android.ui.theme.YellowLight
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Waves
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SleepStatsGrid(
    modifier: Modifier = Modifier,
    animationKey: String = ""
) {
    var animationPlayed by remember(animationKey) { mutableStateOf(false) }

    LaunchedEffect(animationKey) {
        animationPlayed = true
    }

    Column(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            StatCard(
                icon = Icons.Filled.Nightlight,
                iconColor = Blue,
                iconBackground = BlueBg,
                title = "Посл. сон",
                value = "9.6 ч",
                valueColor = Blue,
                animatedPlayed = animationPlayed,
                delay = 0,
                modifier = Modifier.weight(1f)
            )
            StatCard(
                icon = Icons.Filled.TrendingUp,
                iconColor = Orange,
                iconBackground = OrangeBg,
                title = "Среднее",
                value = "8.1 ч",
                valueColor = Orange,
                animatedPlayed = animationPlayed,
                delay = 100,
                modifier = Modifier.weight(1f)
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            StatCard(
                icon = Icons.Filled.Star,
                iconColor = Yellow,
                iconBackground = YellowLight,
                title = "Качество",
                value = "10/10",
                valueColor = Yellow,
                animatedPlayed = animationPlayed,
                delay = 200,
                modifier = Modifier.weight(1f)
            )
            StatCard(
                icon = Icons.Filled.Waves,
                iconColor = Purple,
                iconBackground = PurpleBg,
                title = "Глуб. сон",
                value = "31.6 ч",
                valueColor = Purple,
                animatedPlayed = animationPlayed,
                delay = 300,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview
@Composable
fun SleepStatesGridPreview() {
    FitnessTrackerTheme {
        SleepStatsGrid()
    }
}