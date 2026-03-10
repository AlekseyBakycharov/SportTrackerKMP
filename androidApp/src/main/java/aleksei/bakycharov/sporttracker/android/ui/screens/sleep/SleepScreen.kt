package aleksei.bakycharov.sporttracker.android.ui.screens.sleep

import aleksei.bakycharov.sporttracker.android.ui.screens.sleep.components.SleepChartSection
import aleksei.bakycharov.sporttracker.android.ui.screens.sleep.components.SleepHeader
import aleksei.bakycharov.sporttracker.android.ui.screens.sleep.components.SleepHistorySection
import aleksei.bakycharov.sporttracker.android.ui.screens.sleep.components.SleepStatsGrid
import aleksei.bakycharov.sporttracker.android.ui.screens.sleep.components.SleepTipsSection
import aleksei.bakycharov.sporttracker.android.ui.screens.sleep.model.SleepNightItem
import aleksei.bakycharov.sporttracker.android.ui.theme.Background
import aleksei.bakycharov.sporttracker.android.ui.theme.FitnessTrackerTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SleepScreen(animationKey: String = "") {
    // Mock
    val nights = listOf(
        SleepNightItem("чт, 26 февр.", 9.6f, 2.9f, 30, 6.8f, 71, 10, "Отлично"),
        SleepNightItem("ср, 25 февр.", 6.3f, 1.8f, 29, 4.4f, 70, 7, "Хорошо"),
        SleepNightItem("вт, 24 февр.", 7.5f, 2.5f, 33, 5.0f, 67, 7, "Хорошо"),
        SleepNightItem("пн, 23 февр.", 9.3f, 2.8f, 30, 6.5f, 70, 7, "Хорошо")
    )

    Scaffold(
        topBar = { SleepHeader() }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .background(Background),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            SleepStatsGrid(animationKey = animationKey)
            SleepChartSection(animationKey = animationKey)
            SleepHistorySection(nights = nights, animationKey = animationKey)
            SleepTipsSection()
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
fun SleepScreenPreview() {
    FitnessTrackerTheme {
        SleepScreen()
    }
}