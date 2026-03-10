package aleksei.bakycharov.sporttracker.android.ui.components

import aleksei.bakycharov.sporttracker.android.ui.theme.BlueBg
import aleksei.bakycharov.sporttracker.android.ui.theme.FitnessTrackerTheme
import aleksei.bakycharov.sporttracker.android.ui.theme.GreenDark
import aleksei.bakycharov.sporttracker.android.ui.theme.Orange
import aleksei.bakycharov.sporttracker.android.ui.theme.Teal
import aleksei.bakycharov.sporttracker.android.ui.theme.TextSecondary
import aleksei.bakycharov.sporttracker.android.ui.theme.TooltipBackground
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@Composable
fun SmoothLineChart(
    lines: List<ChartLine>,
    animationPlayed: Boolean,
    modifier: Modifier = Modifier,
    showTooltip: Boolean = true,
    showEveryNthDate: Int = 1  // 1 = все даты, 2 = через одну
) {
    val animatedProgress by animateFloatAsState(
        targetValue = if (animationPlayed) 1f else 0f,
        animationSpec = tween(1500, easing = FastOutSlowInEasing),
        label = "chartProgress"
    )

    var selectedIndex by remember { mutableStateOf<Int?>(null) }

    val paddingStart = 16f
    val paddingEnd = 16f
    val dates = lines.first().data.map { it.first }

    Column(modifier = modifier) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .then(
                    if (showTooltip) {
                        Modifier.pointerInput(Unit) {
                            detectTapGestures { offset ->
                                val chartWidth = size.width - paddingStart - paddingEnd
                                val index =
                                    ((offset.x - paddingStart) / chartWidth * (dates.size - 1))
                                        .roundToInt()
                                        .coerceIn(0, dates.size - 1)
                                selectedIndex = if (selectedIndex == index) null else index
                            }
                        }
                    } else Modifier
                )
        ) {
            val width = size.width
            val height = size.height
            val paddingTop = 20f
            val paddingBottom = 20f
            val chartHeight = height - paddingTop - paddingBottom
            val chartWidth = width - paddingStart - paddingEnd

            // Сетка
            for (i in 0..4) {
                val y = paddingTop + chartHeight * (1 - i / 4f)
                drawLine(
                    color = Color.Gray.copy(alpha = 0.1f),
                    start = Offset(0f, y),
                    end = Offset(width, y),
                    strokeWidth = 1f
                )
            }

            fun buildPoints(data: List<Pair<String, Float>>, maxValue: Float): List<Offset> {
                return data.mapIndexed { index, (_, value) ->
                    Offset(
                        x = paddingStart + chartWidth * index / (data.size - 1).coerceAtLeast(1),
                        y = paddingTop + chartHeight * (1 - value / maxValue)
                    )
                }
            }

            fun buildSmoothPath(points: List<Offset>): Path {
                val path = Path()
                path.moveTo(points.first().x, points.first().y)
                for (i in 0 until points.size - 1) {
                    val p0 = points[i]
                    val p1 = points[i + 1]
                    val cx = (p0.x + p1.x) / 2
                    path.cubicTo(cx, p0.y, cx, p1.y, p1.x, p1.y)
                }
                return path
            }

            lines.forEach { line ->
                val maxValue = line.data.maxOf { it.second }.coerceAtLeast(1f)
                val points = buildPoints(line.data, maxValue)
                val path = buildSmoothPath(points)

                // Анимированная линия
                val measure = PathMeasure()
                measure.setPath(path, false)
                val animatedPath = Path()
                measure.getSegment(0f, measure.length * animatedProgress, animatedPath, true)

                drawPath(
                    path = animatedPath,
                    color = line.color,
                    style = Stroke(width = 2.5.dp.toPx(), cap = StrokeCap.Round)
                )

                // Area заливка
                if (line.fillAlpha > 0f && animatedProgress > 0.9f) {
                    val fillPath = Path().apply {
                        addPath(path)
                        lineTo(points.last().x, height - paddingBottom)
                        lineTo(points.first().x, height - paddingBottom)
                        close()
                    }
                    drawPath(
                        path = fillPath,
                        color = line.color.copy(alpha = line.fillAlpha * animatedProgress)
                    )
                }
            }

            // Точки при выборе
            if (showTooltip) {
                selectedIndex?.let { index ->
                    val x = paddingStart + chartWidth * index / (dates.size - 1).coerceAtLeast(1)

                    drawLine(
                        color = Color.Gray.copy(alpha = 0.3f),
                        start = Offset(x, paddingTop),
                        end = Offset(x, height - paddingBottom),
                        strokeWidth = 1.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f))
                    )

                    lines.forEach { line ->
                        val maxValue = line.data.maxOf { it.second }.coerceAtLeast(1f)
                        val y = paddingTop + chartHeight * (1 - line.data[index].second / maxValue)
                        drawCircle(line.color, 6.dp.toPx(), Offset(x, y))
                        drawCircle(Color.White, 3.dp.toPx(), Offset(x, y))
                    }
                }
            }
        }

        if (showTooltip) {
            selectedIndex?.let { index ->
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = TooltipBackground,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(dates[index], color = Color.White, fontSize = 12.sp)
                        lines.forEach { line ->
                            Text(
                                "${line.label}: ${
                                    line.data[index].second.let {
                                        if (it % 1f == 0f) it.toInt().toString() else it.toString()
                                    }
                                }",
                                color = line.color,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            dates.forEachIndexed { index, date ->
                if (index % showEveryNthDate == 0) {
                    Text(
                        text = date,
                        fontSize = 10.sp,
                        color = TextSecondary,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            lines.forEachIndexed { index, line ->
                if (index > 0) Spacer(modifier = Modifier.width(16.dp))
                Canvas(modifier = Modifier.size(8.dp)) { drawCircle(line.color) }
                Text(" ${line.label}", fontSize = 12.sp, color = TextSecondary)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SmoothLineChartPreview() {
    val stepsData = listOf(
        "20.02" to 3500f,
        "21.02" to 2800f,
        "22.02" to 3200f,
        "23.02" to 1800f,
        "24.02" to 2500f,
        "25.02" to 4200f,
        "26.02" to 4500f
    )
    val caloriesData = listOf(
        "20.02" to 200f,
        "21.02" to 150f,
        "22.02" to 180f,
        "23.02" to 100f,
        "24.02" to 250f,
        "25.02" to 300f,
        "26.02" to 280f
    )

    FitnessTrackerTheme {
        SmoothLineChart(
            lines = listOf(
                ChartLine(stepsData, Teal, "Шаги"),
                ChartLine(caloriesData, Orange, "Калории")
            ),
            animationPlayed = true,
            showTooltip = true
        )
    }
}

data class ChartLine(
    val data: List<Pair<String, Float>>,
    val color: Color,
    val label: String,
    val fillAlpha: Float = 0f
)