package com.ekaterina

fun main() {

    println("Введите оценку настроения от 1 до 10")
    val moodScoreInput = readln()
    val moodScoreInputDouble = moodScoreInput.toDoubleOrNull()
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
    var moodScoreCategory =
        if (moodScoreInputDouble != null && (1.0 <= moodScoreInputDouble && moodScoreInputDouble <= 10.0)) {
            when (moodScoreInputDouble) {
                in 1.0..3.99 -> "Плохое"
                in 4.0..6.99 -> "Нейтральное"
                else -> "Хорошее"
            }
        } else "Оценка некорректна"

    var moodTargetNotesInfo = if (moodCountNotes > moodTargetNotes) {
        "Записей больше цели: превышено на ${moodCountNotes - moodTargetNotes} записей"
    } else if (moodCountNotes == moodTargetNotes) {
        "Цель достигнута"
    } else "Записей меньше цели: осталось ${moodTargetNotes - moodCountNotes} записей"

    var userActivityTrackerInfo = if (!userActivityTracker) {
        "Вернуться к заполнению дневника?"
    } else "Дневник ведется регулярно"
    println(
        """
        === MOOD TRACKER ===
        
        Пользователь: $userName
        Возраст: $userYears
        Город: $userCity
        
        === Статистика ===
        Всего записей: ${moodCountNotes.toInt()}
        Положительные записи: $moodDaysNotesPositivePercent %
        Цель: ${moodTargetNotes.toInt()}
        Выполнение цели: ${String.format("%.2f", moodTargetPercent)} %
        $moodTargetNotesInfo
        Средняя оценка настроения: ${String.format("%.2f", moodMiddleScore)}
        Оценка настроения: ${moodScoreInputDouble ?: "Некорректный ввод"} из 10
        Категория настроения: $moodScoreCategory
        Количество дней для выполнения цели: ${trackerCountDatsTarget.toInt()}
        $userActivityTrackerInfo
    """.trimIndent()
    )


}
