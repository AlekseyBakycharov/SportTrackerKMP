package aleksei.bakycharov.sporttracker.android.ui.screens.sleep.components

import aleksei.bakycharov.sporttracker.android.ui.screens.sleep.model.SleepTip
import aleksei.bakycharov.sporttracker.android.ui.theme.CardBackground
import aleksei.bakycharov.sporttracker.android.ui.theme.FitnessTrackerTheme
import aleksei.bakycharov.sporttracker.android.ui.theme.Yellow
import aleksei.bakycharov.sporttracker.android.ui.theme.YellowLight
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
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Waves
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SleepTipsSection(modifier: Modifier = Modifier) {
    val tips = listOf(
        SleepTip(
            Icons.Filled.Nightlight,
            "Оптимальное время сна",
            "Взрослым рекомендуется 7–9 часов в сутки"
        ),
        SleepTip(
            Icons.Filled.Waves,
            "Глубокий сон",
            "20–25% от общего времени — важно для восстановления"
        ),
        SleepTip(
            Icons.Filled.Schedule,
            "Режим сна",
            "Ложитесь и вставайте в одно время каждый день"
        )
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
                Surface(shape = CircleShape, color = YellowLight) {
                    Icon(
                        imageVector = Icons.Filled.Lightbulb, null,
                        tint = Yellow,
                        modifier = Modifier.padding(8.dp).size(20.dp)
                    )
                }
                Text("Советы", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(12.dp))

            tips.forEachIndexed { index, tip ->
                SleepTipCard(tip = tip)
                if (index < tips.lastIndex) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun SleepTipsSectionPreview() {
    FitnessTrackerTheme {
        SleepTipsSection()
    }
}