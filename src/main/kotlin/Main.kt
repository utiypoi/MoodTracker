package com.ekaterina

const val nameProgram = "MoodTracker"
const val versionProgram = 1.0

fun main() {
    val nameUser: String
    var countNote = 0
    print("Введите имя: ")
    nameUser = readln()
    val outputText = """
        $nameProgram $versionProgram
        Имя пользователя: $nameUser
        Количество записей: $countNote
        Приветствую, $nameUser!
        Приложение позволит пользователю записывать настроение, вести дневник, анализировать статистику, искать записи и управлять ими. Сначала оно будет состоять из простых переменных и функций, а постепенно превратится в полноценное приложение с классами, интерфейсами, коллекциями, сохранением данных и асинхронными операциями.
    """.trimIndent()
    print(outputText)
}
