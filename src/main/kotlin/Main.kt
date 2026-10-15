package com.ekaterina

fun main() {
    val userName: String = "Иван"
    var userYears: Int = 20
    val userCity = "Москва"
    var userActivityTracker = true
    var moodMiddleScore: Double = 7.5
    val favoriteSymbol: Char = ')'

    var moodCountNotes = 7.0
    val moodTargetNotes = 10.0
    var moodDaysNotesAll: Double = 5.0
    var moodDaysNotesPositive: Double = 4.0
    var moodDaysNotesNegative: Double = 1.0

    //Текущее количество записей до цели
    var moodTargetNotesCurrent = moodTargetNotes - moodCountNotes

    //Процент выполнения цели по количеству записей
    var moodTargetPercent: Double = (moodCountNotes / moodTargetNotes) * 100

    var trackerCountDays = 13
    val trackerCountDatsTarget: String = "30"
    var moodScore: String = "7.5"

    //Процент положительных дней от общего количества дней
    var moodDaysNotesPositivePercent = (moodDaysNotesPositive / moodDaysNotesAll) * 100

    println(
        """
        === MOOD TRACKER ===
        
        Пользователь: $userName
        Возраст: $userYears
        Город: $userCity
        
        === Статистика ===
        Всего записей: ${moodCountNotes.toInt()}
        Цель: ${moodTargetNotes.toInt()}
        Выполнение цели: ${String.format("%.2f", moodTargetPercent)} %
        Осталось записей: ${moodTargetNotesCurrent.toInt()}
        Средняя оценка настроения: ${String.format("%.2f", moodMiddleScore)}
        Количество дней для выполнения цели: ${trackerCountDatsTarget.toInt()}
        Оценка настроения: ${moodScore.toDouble()} из 10
    """.trimIndent()
    )
}
