package aleksei.bakycharov.sporttracker.android.ui.screens.sleep.components

import aleksei.bakycharov.sporttracker.android.ui.components.GradientHeader
import aleksei.bakycharov.sporttracker.android.ui.theme.FitnessTrackerTheme
import aleksei.bakycharov.sporttracker.android.ui.theme.SleepGradient
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
import androidx.compose.material.icons.filled.Bed
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SleepHeader() {
    GradientHeader(
        gradientColors = SleepGradient
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Анализ отдыха",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Трекер сна",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Surface(
                shape = CircleShape,
                color = Color.White.copy(alpha = 0.2f)
            ) {
                Icon(
                    imageVector = Icons.Filled.Nightlight,
                    contentDescription = null,
                    tint = Color.White.copy(alpha = 0.7f),
                    modifier = Modifier
                        .padding(10.dp)
                        .size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White.copy(alpha = 0.2f),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Bed,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Text("Прошлой ночью", color = Color.White, fontSize = 14.sp)
                }
                Text("9.6 ч", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White.copy(alpha = 0.3f)
                ) {
                    Text(
                        "Отлично",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SleepHeaderPreview() {
    FitnessTrackerTheme {
        SleepHeader()
    }
}