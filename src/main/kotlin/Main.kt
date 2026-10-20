package com.ekaterina

//data class MoodEntry(
//    val score: Double,
//    val category: String
//)

fun getMoodCategory(score: Double): String {
    return when (score) {
        in 1.0..3.99 -> "Плохое"
        in 4.0..6.99 -> "Нейтральное"
        in 7.00..10.0 -> "Хорошее"
        else -> "Нет данных"
    }
}

fun showMoodHistory(history: List<Double>) {
    if (history.isEmpty()) println("История пока пуста")
    else {
        println("История настроения: ")
        for (i in history.indices) {
            println("${i + 1}. ${history[i]}")
        }
    }
}

fun readMoodScore(): Double {
    println("Введите оценку настроения от 1 до 10")
    var moodScore = readln().toDoubleOrNull()
    while (moodScore == null || moodScore !in 1.0..10.0) {
        println("Некорректная оценка. Поробуйте еще раз.")
        moodScore = readln().toDoubleOrNull()
    }
    println("Настроение записано: ${String.format("%.1f", moodScore)} из 10")
    return moodScore
}

fun addMoodEntry(score: Double, history: MutableList<Double>, categoryStats: MutableMap<String, Int>) {
    history.add(score)
    val category = getMoodCategory(score)
    categoryStats[category] = categoryStats.getValue(category) + 1
}

fun showPositiveMoods(history: List<Double>) {
    val positiveMoods = history.filter { it in 7.0..10.0 }
    if (positiveMoods.isEmpty()) println("Положительных оценок пока нет")
    else {
        println("Положительные оценки: ")
        for (i in positiveMoods.indices) {
            println("${i + 1}: ${positiveMoods[i]}")
        }
    }
}

fun countPositiveMoods(history: List<Double>): Int{
    return history.count { it in 7.0..10.0 }
}

fun showMoodCategories(history: List<Double>) {
    val categories = history.map { getMoodCategory(it) }
    if (history.isEmpty()) println("История пока пуста")
    else {
        for (i in categories.indices) {
            println("${i + 1}: ${categories[i]}")
        }
    }
}

fun main() {
    val userName = "Иван"
    val userYears = 20
    val userCity = "Москва"
    val userActivityTracker = true
    var moodMiddleScore = 0.0
    var moodScoreAll = 0.0
//    val favoriteSymbol = ')'
    val moodNotesHistory: MutableList<Double> = mutableListOf()
    val moodTargetNotes = 10.0
    var moodScorePositive = 0.0
    var moodScoreNegative = 0.0

    var moodTargetPercent = 0.0

//    var trackerCountDays = 13
    val trackerCountDatsTarget = "30"

    var moodScorePositivePercent = 0.0
    var moodScoreNegativePercent = 0.0

    val moodCategoryStats = mutableMapOf("Плохое" to 0, "Нейтральное" to 0, "Хорошее" to 0)

    var moodTargetNotesInfo: String

    val userActivityTrackerInfo = if (!userActivityTracker) {
        "Вернуться к заполнению дневника?"
    } else "Дневник ведется регулярно"

    val menu = """Главное меню
        1. Записать настроение
        2. Показать статистику
        3. Выход
        4. Сбросить статистику
        5. Показать историю настроения
        6. Показать положительные оценки
        7. Показать категории настроения
        Выберите действие
    """.trimIndent()
    //Ввод пользователя оценки настроения
//    var moodScoreInput: String
//    var moodScoreInputDouble: Double?
    var moodScore = 0.0

    while (true) {
        println(menu)
        val menuSelector = readln().toIntOrNull()
        when (menuSelector) {
            1 -> {
                moodScore = readMoodScore()
                addMoodEntry(moodScore, moodNotesHistory, moodCategoryStats)
                moodScoreAll += moodScore
                moodTargetPercent = (moodNotesHistory.size.toDouble() / moodTargetNotes) * 100
                moodScorePositivePercent = (moodScorePositive / moodNotesHistory.size) * 100
                moodScoreNegativePercent = (moodScoreNegative / moodNotesHistory.size) * 100
                moodMiddleScore = moodScoreAll / moodNotesHistory.size
            }

            2 -> {
                moodTargetNotesInfo = if (moodNotesHistory.size > moodTargetNotes.toInt()) {
                    "Записей больше цели: превышено на ${moodNotesHistory.size - moodTargetNotes.toInt()} записей"
                } else if (moodNotesHistory.size == moodTargetNotes.toInt()) {
                    "Цель достигнута"
                } else "Записей меньше цели: осталось ${moodTargetNotes.toInt() - moodNotesHistory.size} записей"
                println(
                    """
        === MOOD TRACKER ===
        
        Пользователь: $userName
        Возраст: $userYears
        Город: $userCity
        
        === Статистика ===
        Всего записей: ${moodNotesHistory.size}
        Положительные записи: ${"%.2f".format(moodScorePositivePercent)} %
        Отрицательные записи: ${"%.2f".format(moodScoreNegativePercent)} %
        Цель: ${moodTargetNotes.toInt()}
        Выполнение цели: ${String.format("%.2f", moodTargetPercent)} %
        $moodTargetNotesInfo
        Средняя оценка настроения: ${String.format("%.2f", moodMiddleScore)}
        Оценка настроения: ${"%.2f".format(moodScore)} из 10
        Категория настроения: ${
                        if (moodNotesHistory.isEmpty()) "Нет данных"
                        else getMoodCategory(moodScore)
                    }
        Количество дней для выполнения цели: ${trackerCountDatsTarget.toInt()}
        $userActivityTrackerInfo
        === Статистика по категориям ===
                        Плохое настроение: ${moodCategoryStats["Плохое"]}
                        Нейтральное настроение: ${moodCategoryStats["Нейтральное"]}
                        Хорошее настроение: ${moodCategoryStats["Хорошее"]}
    """.trimIndent()
                )
            }

            3 -> {
                println("До свидания!")
                break
            }

            4 -> {
                println("Сбросить всю статистику? (да/нет)")
                when (readln()) {
                    "да" -> {
                        moodNotesHistory.clear()
                        moodScoreAll = 0.0
                        moodMiddleScore = 0.0
                        moodScorePositive = 0.0
                        moodScoreNegative = 0.0
                        moodScorePositivePercent = 0.0
                        moodScoreNegativePercent = 0.0
                        moodTargetPercent = 0.0
                        moodScore = 0.0
                        getMoodCategory(moodScore)
                        moodCategoryStats["Плохое"] = 0
                        moodCategoryStats["Нейтральное"] = 0
                        moodCategoryStats["Хорошее"] = 0
                    }

                    "нет" -> println("Сброс отменен")
                    else -> println("Некорректный ответ")
                }
            }

            5 -> showMoodHistory(moodNotesHistory)

            6 -> {
                showPositiveMoods(moodNotesHistory)
                println("Количество: ${countPositiveMoods(moodNotesHistory)}")
            }

            7 -> showMoodCategories(moodNotesHistory)


            else -> println("Неизвестная команда. Попробуйте еще раз.")
        }
    }
}