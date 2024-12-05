package com.github.michaelbull.advent2024.day05

typealias Rule = Pair<Int, Int>
typealias Update = List<Int>

private fun String.toRule(): Rule {
    val (before, after) = split("|", limit = 2)

    return Rule(
        first = before.toInt(),
        second = after.toInt(),
    )
}

private fun String.toUpdate(): Update {
    return split(",").map(String::toInt)
}

fun Sequence<String>.toSafetyManual(): SafetyManual {
    val rules = mutableListOf<Rule>()
    val updates = mutableListOf<Update>()
    var readRules = true

    for (line in this) {
        if (line.isBlank()) {
            readRules = false
        } else if (readRules) {
            rules += line.toRule()
        } else {
            updates += line.toUpdate()
        }
    }

    return SafetyManual(
        rules = rules.toList(),
        updates = updates.toList(),
    )
}

data class SafetyManual(
    val rules: List<Rule>,
    val updates: List<Update>,
) {

    fun findOrderedUpdates(): List<Update> {
        return updates.filter(::isCorrect)
    }

    fun reorderIncorrectUpdates(): List<Update> {
        return updates.filter(::isIncorrect).map(::reorder)
    }

    private fun isCorrect(update: Update): Boolean {
        return update.zipWithNext().all(::isCorrect)
    }

    private fun isIncorrect(update: Update): Boolean {
        return !isCorrect(update)
    }

    private fun isCorrect(rule: Rule): Boolean {
        return rule in rules
    }

    private fun isIncorrect(rule: Rule): Boolean {
        return !isCorrect(rule)
    }

    private fun reorder(update: Update): Update {
        val reordered = update.toMutableList()
        var incorrect = true

        while (incorrect) {
            val index = reordered.indexOfFirstPair(::isIncorrect)

            if (index == -1) {
                incorrect = false
            } else {
                reordered.swapWithNext(index)
            }
        }

        return reordered
    }
}

private inline fun <T> List<T>.indexOfFirstPair(predicate: (Pair<T, T>) -> Boolean): Int {
    return zipWithNext().indexOfFirst(predicate)
}

private fun <T> List<T>.pairAt(index: Int): Pair<T, T> {
    val curr = this[index]
    val next = this[index + 1]
    return curr to next
}

private fun <T> MutableList<T>.swapWithNext(index: Int) {
    val (curr, next) = pairAt(index)
    this[index] = next
    this[index + 1] = curr
}
