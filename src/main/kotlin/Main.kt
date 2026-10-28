package com.ekaterina

//data class MoodEntry(
//    val score: Double,
//    val category: String
//)

data class MoodStats(
    val average: Double,
    val positivePercent: Double,
    val negativePercent: Double,
    val targetPercent: Double
)

fun getMoodCategory(score: Double): String {
    return when {
        (score >= 1 && score < 4) -> "Плохое"
        (score >= 4 && score < 7) -> "Нейтральное"
        (score in 7.0..10.0) -> "Хорошее"
        else -> "Нет данных"
    }
}

fun outputHistoryIsNotEmpty(history: List<Double>): Boolean {
    return if (history.isEmpty()) {
        println("История пока пуста")
        false
    } else true
}

fun showMoodHistory(history: List<Double>) {
    if (outputHistoryIsNotEmpty(history)) {
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

fun countPositiveMoods(history: List<Double>): Int {
    return history.count { it in 7.0..10.0 }
}

fun countNeutralMoods(history: List<Double>): Int {
    return history.count { it >= 4.0 && it < 7.0 }
}

fun countNegativeMoods(history: List<Double>): Int {
    return history.count { it >= 1.0 && it < 4.0 }
}

fun showMoodCategories(history: List<Double>) {
    val categories = history.map { getMoodCategory(it) }
    if (outputHistoryIsNotEmpty(history)) {
        for (i in categories.indices) {
            println("${i + 1}: ${categories[i]}")
        }
    }
}

fun showAnalyticsMood(history: List<Double>) {
    if (outputHistoryIsNotEmpty(history)) {
        println(
            """
        === Аналитика настроения ===
        Количество всех записей: ${history.count()}
        Количество положительных записей: ${countPositiveMoods(history)}
        Количество нейтральных записей: ${countNeutralMoods(history)}
        Количество отрицательных оценок: ${countNegativeMoods(history)}
        Самая высокая оценка: ${history.maxOrNull()}
        Самая низкая оценка: ${history.minOrNull()}
        Средняя оценка настроения: ${history.average()}
    """.trimIndent()
        )
    }
}

fun distributionScoreMood(history: List<Double>) {
    if (outputHistoryIsNotEmpty(history)) {
        val distributionScoreMoodCount: MutableMap<Int, Int> = mutableMapOf()
        for (score in 1..10) {
            distributionScoreMoodCount[score] = 0
        }
        for (i in history) {
            val score = i.toInt()
            if (score in 1..10) distributionScoreMoodCount[score] =
                distributionScoreMoodCount.getOrDefault(score, 0) + 1
        }
        for (i in distributionScoreMoodCount) {
            println("${i.key} - ${i.value} ${toCountString(i.value)}")
        }
    }
}

fun toCountString(i: Int) = when (i) {
    in setOf(2, 3, 4) -> "раза"
    else -> "раз"
}

fun outputSearchEntriesCategory(history: MutableMap<Int, Double>) {
    println("Найденные записи: ")
    for (entry in history) {
        println("${entry.key + 1}: ${entry.value}")
    }
}

fun searchEntryCategories(history: List<Double>) {
    if (outputHistoryIsNotEmpty(history)) {
        println("Выберите номер категории: \n1.Плохое\n2.Нейтральное\n3.Положительное")
        val numberCatalog = readln().toIntOrNull()
        val historyCatalog: MutableMap<Int, Double> = mutableMapOf()
        when (numberCatalog) {
            1 -> {
                for (i in history.indices) {
                    if (getMoodCategory(history[i]) == "Плохое") historyCatalog[i] = history[i]
                }
                outputSearchEntriesCategory(historyCatalog)
            }

            2 -> {
                for (i in history.indices) {
                    if (getMoodCategory(history[i]) == "Нейтральное") historyCatalog[i] = history[i]

                }
                outputSearchEntriesCategory(historyCatalog)
            }

            3 -> {
                for (i in history.indices) {
                    if (getMoodCategory(history[i]) == "Хорошее") historyCatalog[i] = history[i]
                }
                outputSearchEntriesCategory(historyCatalog)
            }

            else -> println("Неверный номер категории")
        }
    }
}

fun removeEntryMoodQuery(history: List<Double>) {
    if (outputHistoryIsNotEmpty(history)) {
        showMoodHistory(history)
        println("Введите номер записи для удаления: ")
        val numberEntry = readln().toIntOrNull()
        if (numberEntry != null && numberEntry in 1..history.size) {
            println("Удалить запись $numberEntry с оценкой ${history[numberEntry - 1]}? (да, нет)")
            when (readln()) {
                "да" -> {
                    removeEntryMood(numberEntry)
                    println("Запись удалена")
                }

                "нет" -> println("Отмена удаления")
                else -> println("Неверный ответ")
            }
        }
    } else println("Неверный номер записи")

}

fun removeEntryMood(number: Int) {
    moodNotesHistory.removeAt(number - 1)
}

fun editEntryMoodQuery(history: List<Double>) {
    if (outputHistoryIsNotEmpty(history)) {
        showMoodHistory(history)
        println("Введите номер записи: ")
        val numberEntry = readln().toIntOrNull()
        if (numberEntry != null && numberEntry <= history.size + 1 && numberEntry in history.indices + 1) {
            println("Текущая оценка: ${history[numberEntry - 1]}")
            val newScore = readMoodScore()
            val oldScore = history[numberEntry - 1]
            editEntryMood(numberEntry, newScore)
            println("Оценка изменена: $oldScore->$newScore")
        } else println("Неверный номер записи")
    }
}

fun editEntryMood(number: Int, score: Double) {
    moodNotesHistory[number - 1] = score
}

fun searchEntryMoodQuery(history: List<Double>) {
    if (outputHistoryIsNotEmpty(history)) {
        println("История: $history")
        println("Введите оценку для поиска: ")
        val searchScore = readln().toDoubleOrNull()
        if (searchScore != null && searchScore in 1.0..10.0) {
            val searchEntry: MutableMap<Int, Double> = mutableMapOf()
            for ((index, value) in history.withIndex()) {
                if (value == searchScore) searchEntry[index] = value
            }
            if (searchEntry.isNotEmpty()) {
                for (i in searchEntry) {
                    println("${i.key + 1} ${i.value}")
                }
            } else println("Оценка не найдена")
        } else println("Неверная оценка")
    }
}

val moodNotesHistory: MutableList<Double> = mutableListOf()
val userName = "Иван"
val userYears = 20
val userCity = "Москва"
val userActivityTracker = true
var moodMiddleScore = 0.0
var moodScoreAll = 0.0

//    val favoriteSymbol = ')'
val moodTargetNotes = 10.0
var moodTargetPercent = 0.0

//    var trackerCountDays = 13
val trackerCountDatsTarget = "30"
var moodScorePositivePercent = 0.0
var moodScoreNegativePercent = 0.0

val moodCategoryStats = mutableMapOf("Плохое" to 0, "Нейтральное" to 0, "Хорошее" to 0)

val menu = """Главное меню
        1. Записать настроение
        2. Показать статистику
        3. Выход
        4. Сбросить статистику
        5. Показать историю настроения
        6. Показать положительные оценки
        7. Показать категории настроения
        8. Подробная аналитика настроения
        9. Найти записи по категории
        10. Распределение оценок
        11. Удалить запись
        12. Изменить оценку
        13. Найти оценку
        Выберите действие
    """.trimIndent()
var moodScore = 0.0

fun main() {
    val userActivityTrackerInfo = if (!userActivityTracker) {
        "Вернуться к заполнению дневника?"
    } else "Дневник ведется регулярно"
    while (true) {
        println(menu)
        val menuSelector = readln().toIntOrNull()
        when (menuSelector) {
            1 -> {
                moodScore = readMoodScore()
                addMoodEntry(moodScore, moodNotesHistory, moodCategoryStats)
                moodScoreAll += moodScore
                moodTargetPercent = (moodNotesHistory.size.toDouble() / moodTargetNotes) * 100
                moodScorePositivePercent =
                    (countPositiveMoods(moodNotesHistory).toDouble() / moodNotesHistory.size) * 100
                moodScoreNegativePercent =
                    (countNegativeMoods(moodNotesHistory).toDouble() / moodNotesHistory.size) * 100
                moodMiddleScore = moodScoreAll / moodNotesHistory.size
            }

            2 -> {
                val moodTargetNotesInfo = if (moodNotesHistory.size > moodTargetNotes.toInt()) {
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

            8 -> showAnalyticsMood(moodNotesHistory)

            9 -> searchEntryCategories(moodNotesHistory)

            10 -> distributionScoreMood(moodNotesHistory)

            11 -> removeEntryMoodQuery(moodNotesHistory)

            12 -> editEntryMoodQuery(moodNotesHistory)

            13 -> searchEntryMoodQuery(moodNotesHistory)

            else -> println("Неизвестная команда. Попробуйте еще раз.")
        }

    }
}




