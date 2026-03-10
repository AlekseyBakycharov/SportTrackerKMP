package aleksei.bakycharov.sporttracker.android.ui.screens.sleep.components

import aleksei.bakycharov.sporttracker.android.ui.theme.Blue
import aleksei.bakycharov.sporttracker.android.ui.theme.BlueLightBg
import aleksei.bakycharov.sporttracker.android.ui.theme.CardBackground
import aleksei.bakycharov.sporttracker.android.ui.theme.FitnessTrackerTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SleepChartSection(
    modifier: Modifier = Modifier,
    animationKey: String = ""
) {
    var animationPlayed by remember(animationKey) { mutableStateOf(false) }

    LaunchedEffect(animationKey) {
        animationPlayed = true
    }

    // Mock
    val totalData = listOf(
        "13.02" to 7.2f, "14.02" to 6.5f, "15.02" to 8.0f, "16.02" to 7.8f,
        "17.02" to 6.0f, "18.02" to 7.5f, "19.02" to 8.2f, "20.02" to 5.5f,
        "21.02" to 9.0f, "22.02" to 7.0f, "23.02" to 9.3f, "24.02" to 7.5f,
        "25.02" to 6.3f, "26.02" to 9.6f
    )
    val deepData = listOf(
        "13.02" to 2.1f, "14.02" to 1.8f, "15.02" to 2.5f, "16.02" to 2.3f,
        "17.02" to 1.5f, "18.02" to 2.0f, "19.02" to 2.8f, "20.02" to 1.2f,
        "21.02" to 3.0f, "22.02" to 2.0f, "23.02" to 2.8f, "24.02" to 2.5f,
        "25.02" to 1.8f, "26.02" to 2.9f
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Surface(shape = CircleShape, color = BlueLightBg) {
                    Icon(
                        Icons.Filled.TrendingUp, null,
                        tint = Blue,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(20.dp)
                    )
                }
                Text("График за 2 недели", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(16.dp))
            SleepChart(
                totalData = totalData,
                deepData = deepData,
                animationPlayed = animationPlayed
            )
        }
    }
}

@Preview
@Composable
fun SleepChartSectionPreview() {
    FitnessTrackerTheme {
        SleepChartSection()
    }
}