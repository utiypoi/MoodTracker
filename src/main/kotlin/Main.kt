package com.ekaterina

fun main() {
    val userName: String = "Иван"
    var userYears: Int = 20
    val userCity = "Москва"
    var userActivityTracker = true
    var moodMiddleScore: Double = 7.5
    val favoriteSymbol: Char = ')'

    var moodCountNotes: Int = 5
    val moodTargetNotes = 10.0
    var moodDaysNotesAll: Int = 5
    var moodDaysNotesPositive: Int
    var moodDaysNotesNegative: Int

    var moodTargetNotesCurrent = moodCountNotes / moodTargetNotes * 10
    var moodTargetPercent: Double = (moodTargetNotes - moodCountNotes) / moodTargetNotes * 100

    println(
        """
        === MOOD TRACKER ===
        
        Пользователь: $userName
        Возраст: $userYears
        Город: $userCity
        
        === Статистика ===
        Всего записей: $moodCountNotes
        Цель: $moodTargetNotes
        Выполнение цели: $moodTargetPercent %
        Осталось записей: $moodTargetNotesCurrent
    """.trimIndent()
    )
}
