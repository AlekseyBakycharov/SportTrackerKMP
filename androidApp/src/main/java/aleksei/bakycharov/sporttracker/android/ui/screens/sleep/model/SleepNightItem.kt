package aleksei.bakycharov.sporttracker.android.ui.screens.sleep.model

data class SleepNightItem(
    val date: String,
    val totalHours: Float,
    val deepHours: Float,
    val deepPercent: Int,
    val lightHours: Float,
    val lightPercent: Int,
    val quality: Int,
    val qualityLabel: String
)