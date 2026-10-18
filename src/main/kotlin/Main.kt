package com.ekaterina

fun main() {
    val userName = "Иван"
    val userYears = 20
    val userCity = "Москва"
    val userActivityTracker = true
    var moodMiddleScore = 0.0
    var moodScoreAll = 0.0
    val favoriteSymbol = ')'

    val moodNotesHistory: MutableList<Double> = mutableListOf()
    val moodTargetNotes = 10.0
    var moodScorePositive = 0.0
    var moodScoreNegative = 0.0

    var moodTargetPercent = 0.0

    var trackerCountDays = 13
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
        Выберите действие
    """.trimIndent()
    //Ввод пользователя оценки настроения
    var moodScoreInput: String
    var moodScoreInputDouble: Double?
    var moodScore = 0.0
    var moodScoreCategory = "Нет данных"
    while (true) {
        println(menu)
        val menuSelector = readln().toIntOrNull()
        when (menuSelector) {
            1 -> {
                println("Введите оценку настроения от 1 до 10")
                moodScoreInput = readln()
                moodScoreInputDouble = moodScoreInput.toDoubleOrNull()
                while (moodScoreInputDouble == null || moodScoreInputDouble !in 1.0..10.0) {
                    println("Некорректная оценка. Поробуйте еще раз.")
                    moodScoreInput = readln()
                    moodScoreInputDouble = moodScoreInput.toDoubleOrNull()
                }
                moodScore = moodScoreInputDouble
                moodNotesHistory.add(moodScore)
                moodScoreCategory =
                    when (moodScore) {
                        in 1.0..3.99 -> {
                            moodScoreNegative++
                            "Плохое"
                        }

                        in 4.0..6.99 -> {
                            "Нейтральное"
                        }

                        else -> {
                            moodScorePositive++
                            "Хорошее"
                        }
                    }
                moodCategoryStats[moodScoreCategory] = moodCategoryStats.getValue(moodScoreCategory) + 1
                moodScoreAll += moodScore
                println("Настроение записано: ${String.format("%.1f", moodScoreInputDouble)} из 10")
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
        Категория настроения: $moodScoreCategory
        Количество дней для выполнения цели: ${trackerCountDatsTarget.toInt()}
        $userActivityTrackerInfo
        === Статистика по категориям ===
        Плохое настроение: ${moodCategoryStats.get("Плохое")}
        Нейтральное настроение: ${moodCategoryStats.get("Нейтральное")}
        Хорошее настроение: ${moodCategoryStats.get("Хорошее")}
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
                        moodScoreCategory = "Нет данных"
                        moodCategoryStats.set("Плохое", 0)
                        moodCategoryStats.set("Нейтральное", 0)
                        moodCategoryStats.set("Хорошее", 0)
                    }

                    "нет" -> println("Сброс отменен")
                    else -> println("Некорректный ответ")
                }
            }

            5 -> {
                if (moodNotesHistory.isNotEmpty()) {
                    println("История настроения: ")
                    for (i in 0..<moodNotesHistory.size) {
                        println("${i + 1}. ${moodNotesHistory[i]}")
                    }
                } else println("История пока пуста")
            }

            else -> println("Неизвестная команда. Попробуйте еще раз.")
        }
    }
}

