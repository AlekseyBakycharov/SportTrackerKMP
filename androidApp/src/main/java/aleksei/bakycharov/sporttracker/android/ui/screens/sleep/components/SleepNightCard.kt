package aleksei.bakycharov.sporttracker.android.ui.screens.sleep.components

import aleksei.bakycharov.sporttracker.android.ui.screens.sleep.model.SleepNightItem
import aleksei.bakycharov.sporttracker.android.ui.theme.Blue
import aleksei.bakycharov.sporttracker.android.ui.theme.CardBackground
import aleksei.bakycharov.sporttracker.android.ui.theme.FitnessTrackerTheme
import aleksei.bakycharov.sporttracker.android.ui.theme.Green
import aleksei.bakycharov.sporttracker.android.ui.theme.Purple
import aleksei.bakycharov.sporttracker.android.ui.theme.Red
import aleksei.bakycharov.sporttracker.android.ui.theme.TextPrimary
import aleksei.bakycharov.sporttracker.android.ui.theme.TextSecondary
import aleksei.bakycharov.sporttracker.android.ui.theme.Yellow
import aleksei.bakycharov.sporttracker.android.ui.theme.YellowLight
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SleepNightCard(
    modifier: Modifier = Modifier,
    night: SleepNightItem,
    animationPlayed: Boolean,
    delay: Int = 0,
) {
    val alpha by animateFloatAsState(
        targetValue = if (animationPlayed) 1f else 0f,
        animationSpec = tween(500, delayMillis = delay),
        label = "alpha"
    )
    val offsetY by animateDpAsState(
        targetValue = if (animationPlayed) 0.dp else 20.dp,
        animationSpec = tween(500, delayMillis = delay),
        label = "offset"
    )

    val qualityColor = when {
        night.quality >= 9 -> Green
        night.quality >= 6 -> Yellow
        else -> Red
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .alpha(alpha)
            .offset(y = offsetY),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        night.date,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary
                    )
                    Text(
                        "${night.totalHours} часов",
                        fontSize = 13.sp,
                        color = TextSecondary
                    )
                }
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = qualityColor.copy(alpha = 0.1f)
                ) {
                    Text(
                        "${night.quality}/10 · ${night.qualityLabel}",
                        color = qualityColor,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                // Глубокий сон
                Column(modifier = Modifier.weight(1f)) {
                    Text("Глубокий сон", fontSize = 12.sp, color = TextSecondary)
                    Spacer(modifier = Modifier.height(6.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp))
                            .background(Purple.copy(alpha = 0.15f))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(night.deepPercent / 100f)
                                .clip(RoundedCornerShape(3.dp))
                                .background(Purple)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "${night.deepHours} ч · ${night.deepPercent}%",
                        fontSize = 11.sp,
                        color = TextSecondary
                    )
                }
                // Лёгкий сон
                Column(modifier = Modifier.weight(1f)) {
                    Text("Лёгкий сон", fontSize = 12.sp, color = TextSecondary)
                    Spacer(modifier = Modifier.height(6.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp))
                            .background(Blue.copy(alpha = 0.15f))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(night.lightPercent / 100f)
                                .clip(RoundedCornerShape(3.dp))
                                .background(Blue)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "${night.lightHours} ч · ${night.lightPercent}%",
                        fontSize = 11.sp,
                        color = TextSecondary
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SleepNightCardPreview() {
    FitnessTrackerTheme {
        SleepNightCard(
            night = SleepNightItem(
                "чт, 26 февр.",
                9.6f,
                2.9f,
                30,
                6.8f,
                71,
                10,
                "Отлично"),
            animationPlayed = true
        )
    }
}