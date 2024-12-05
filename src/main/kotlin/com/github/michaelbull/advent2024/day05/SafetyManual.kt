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
    val iterator = iterator()

    val rules = iterator.asSequence()
        .takeWhile(String::isNotEmpty)
        .map(String::toRule)
        .toSet()

    val updates = iterator.asSequence()
        .map(String::toUpdate)
        .toList()

    return SafetyManual(
        rules = rules,
        updates = updates,
    )
}

data class SafetyManual(
    val rules: Set<Rule>,
    val updates: List<Update>,
) {

    fun findOrderedUpdates(): List<Update> {
        return updates.filter(::isCorrect)
    }

    fun reorderIncorrectUpdates(): List<Update> {
        return updates.filterNot(::isCorrect).map(::reorder)
    }

    private fun isCorrect(update: Update): Boolean {
        return update.zipWithNext().all(::isCorrect)
    }

    private fun isCorrect(rule: Rule): Boolean {
        return rule in rules
    }

    private fun reorder(update: Update): Update {
        return update.sortedWith(updateComparator)
    }

    private val updateComparator = Comparator<Int> { a, b ->
        when {
            isCorrect(a to b) -> -1
            isCorrect(b to a) -> +1
            else -> 0
        }
    }
}
