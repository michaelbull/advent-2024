package com.github.michaelbull.advent2024.day23

import com.github.michaelbull.itertools.combinations

fun Sequence<String>.toNetwork(): Network {
    val connections = map(String::toConnection).toList()
    return Network(connections)
}

class Network(
    private val connections: List<Connection>,
) {

    fun countStartsWith(char: Char): String {
        return groups().interconnections().count { computers ->
            computers.any { computer ->
                computer.startsWith(char)
            }
        }.toString()
    }

    fun password(): String {
        return groups()
            .computers()
            .sorted()
            .joinToString(",")
    }

    private fun groups(): Map<String, Set<String>> {
        return buildMap<String, MutableSet<String>> {
            for (connection in connections) {
                val (from, to) = connection

                getOrPut(from, ::mutableSetOf) += to
                getOrPut(to, ::mutableSetOf) += from
            }
        }
    }

    private fun Map<String, Set<String>>.interconnections() = sequence {
        for (a in keys) {
            val aToB = get(a) ?: continue

            for (b in aToB) {
                val bToC = get(b) ?: continue

                for (c in bToC) {
                    val cToA = get(c) ?: continue

                    if (a in cToA && a < b && b < c) {
                        yield(listOf(a, b, c))
                    }
                }
            }
        }
    }

    private fun Map<String, Set<String>>.computers(): List<String> {
        val maxSize = maxOf { it.value.size }

        for (size in maxSize downTo 2) {
            val a = filter { it.value.size >= size }.keys
            if (a.size < size) {
                continue
            }

            for (computer in a) {
                val b = getValue(computer)
                if (b.size < size) {
                    continue
                }

                for (combination in b.toList().combinations(size)) {
                    val c = combination.filter { getValue(it).containsAll(combination - it + computer) }
                    if (c.size < size) {
                        continue
                    }

                    return combination + computer
                }
            }
        }

        return emptyList()
    }
}
