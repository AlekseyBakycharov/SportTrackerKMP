package aleksei.bakycharov.sporttracker.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

object DateHelper {

    fun today(): LocalDate = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .date

    fun plus(date: LocalDate): LocalDate = date.plus(1, DateTimeUnit.DAY)

    fun minus(date: LocalDate): LocalDate = date.minus(1, DateTimeUnit.DAY)

    fun formatShort(date: LocalDate): String {
        val month = listOf(
            "янв.", "февр.", "мар.", "апр.", "мая", "июн.",
            "июл.", "авг.", "сент.", "окт.", "нояб.", "дек."
        )
        return "${date.dayOfMonth} ${month[date.monthNumber - 1]}"
    }

    fun formatWithDay(date: LocalDate): String {
        val days = listOf("пн", "вт", "ср", "чт", "пт", "сб", "вс")
        val dayName = days[date.dayOfWeek.ordinal]
        return "$dayName, ${formatShort(date)}"
    }

}