package aleksei.bakycharov.sporttracker.android.ui.screens.sleep.components

import aleksei.bakycharov.sporttracker.android.ui.screens.sleep.model.SleepTip
import aleksei.bakycharov.sporttracker.android.ui.theme.FitnessTrackerTheme
import aleksei.bakycharov.sporttracker.android.ui.theme.TextPrimary
import aleksei.bakycharov.sporttracker.android.ui.theme.TextSecondary
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
import androidx.compose.material.icons.filled.Nightlight
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
fun SleepTipCard(
    tip: SleepTip,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = YellowLight.copy(alpha = 0.4f)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Surface(
                shape = CircleShape,
                color = YellowLight
            ) {
                Icon(
                    tip.icon, null,
                    tint = Yellow,
                    modifier = Modifier.padding(8.dp).size(20.dp)
                )
            }
            Column {
                Text(
                    text = tip.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = tip.description,
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }
        }
    }
}

@Preview
@Composable
fun SleepTipCardPreview() {
    FitnessTrackerTheme {
        SleepTipCard(
            tip = SleepTip(
                icon = Icons.Filled.Nightlight,
                title = "Оптимальное время сна",
                description = "Взрослым рекомендуется 7–9 часов в сутки"
            )
        )
    }
}
