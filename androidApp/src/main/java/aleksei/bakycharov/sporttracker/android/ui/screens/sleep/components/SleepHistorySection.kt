package aleksei.bakycharov.sporttracker.android.ui.screens.sleep.components

import aleksei.bakycharov.sporttracker.android.ui.screens.sleep.model.SleepNightItem
import aleksei.bakycharov.sporttracker.android.ui.theme.CardBackground
import aleksei.bakycharov.sporttracker.android.ui.theme.FitnessTrackerTheme
import aleksei.bakycharov.sporttracker.android.ui.theme.Purple
import aleksei.bakycharov.sporttracker.android.ui.theme.PurpleBg
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
import androidx.compose.material.icons.filled.Nightlight
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
fun SleepHistorySection(
    nights: List<SleepNightItem>,
    modifier: Modifier = Modifier,
    animationKey: String = ""
) {
    var animationPlayed by remember(animationKey) { mutableStateOf(false) }

    LaunchedEffect(animationKey) {
        animationPlayed = true
    }

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
                Surface(shape = CircleShape, color = PurpleBg) {
                    Icon(
                        imageVector = Icons.Filled.Nightlight, null,
                        tint = Purple,
                        modifier = Modifier.padding(8.dp).size(20.dp)
                    )
                }
                Text("История ночей", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(12.dp))

            nights.forEachIndexed { index, night ->
                SleepNightCard(
                    night = night,
                    animationPlayed = animationPlayed,
                    delay = index * 100
                )
                if (index < nights.lastIndex) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun SleepHistorySectionPreview() {
    FitnessTrackerTheme {
        val list = listOf(SleepNightItem("чт, 26 февр.", 9.6f, 2.9f, 30, 6.8f, 71, 10, "Отлично"))
        SleepHistorySection(
            nights = list
        )
    }
}
