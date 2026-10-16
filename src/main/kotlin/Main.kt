package com.ekaterina

fun main() {

    println("Введите оценку настроения от 1 до 10")
    var moodScoreInput = readln()
    var moodScoreInputDouble = moodScoreInput.toDoubleOrNull()
    while (moodScoreInputDouble == null || moodScoreInputDouble !in 1.0..10.0) {
        println("Некорректная оценка. Поробуйте еще раз.")
        moodScoreInput = readln()
        moodScoreInputDouble = moodScoreInput.toDoubleOrNull()
    }
    val moodScore = moodScoreInput.toDouble()

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
    val moodTargetNotesCurrent = moodTargetNotes - moodCountNotes

    //Процент выполнения цели по количеству записей
    val moodTargetPercent: Double = (moodCountNotes / moodTargetNotes) * 100

    var trackerCountDays = 13
    val trackerCountDatsTarget: String = "30"

    //Процент положительных дней от общего количества дней
    val moodDaysNotesPositivePercent = (moodDaysNotesPositive / moodDaysNotesAll) * 100
    val moodScoreCategory =
        when (moodScore) {
            in 1.0..3.99 -> "Плохое"
            in 4.0..6.99 -> "Нейтральное"
            else -> "Хорошее"
        }


    var moodTargetNotesInfo = if (moodCountNotes > moodTargetNotes) {
        "Записей больше цели: превышено на ${moodCountNotes - moodTargetNotes} записей"
    } else if (moodCountNotes == moodTargetNotes) {
        "Цель достигнута"
    } else "Записей меньше цели: осталось ${moodTargetNotes - moodCountNotes} записей"

    var userActivityTrackerInfo = if (!userActivityTracker) {
        "Вернуться к заполнению дневника?"
    } else "Дневник ведется регулярно"

    while (true){
        val menu = """Главное меню
        1. Записать настроение
        2. Показать статистику
        3. Выход
        Выберите действие
    """.trimIndent()
        println(menu)
        val menuSelector = readln().toIntOrNull()
        when(menuSelector){
            1 -> println("Запись настроения")
            2 -> println(
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
        Оценка настроения: $moodScore из 10
        Категория настроения: $moodScoreCategory
        Количество дней для выполнения цели: ${trackerCountDatsTarget.toInt()}
        $userActivityTrackerInfo
    """.trimIndent()
            )
            3 -> {
                println("До свидания!")
                break
            }
            else -> println("Неизвестная команда. Попробуйте еще раз.")
        }
    }

}
