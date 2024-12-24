package com.github.michaelbull.advent2024.day24

fun Sequence<String>.toMonitor(): Monitor {
    val iterator = iterator()

    val wires = iterator.asSequence()
        .takeWhile(String::isNotEmpty)
        .map(String::toWire)
        .toList()

    val connections = iterator.asSequence()
        .map(String::toConnection)
        .toList()

    return Monitor(wires, connections)
}

class Monitor(
    private val wires: List<Wire>,
    private val connections: List<Connection>,
) {

    fun output(startingWith: Char): Long {
        return simulate()
            .toList()
            .decimal(startingWith)
    }

    fun swappedWiresCsv(): String {
        return wiresInSwap()
            .map(Connection::output)
            .sorted()
            .joinToString(",")
    }

    private fun simulate(outputNames: Map<String, String> = emptyMap()): Map<String, Boolean> {
        val result = wires.toMap(mutableMapOf())
        val queue = ArrayDeque(connections)

        while (queue.isNotEmpty()) {
            val connection = queue.removeFirst()
            val (from, operand, to, output) = connection

            val fromValue = result[from]
            val toValue = result[to]

            if (fromValue == null || toValue == null) {
                queue.addLast(connection)
            } else {
                val outputValue = when (operand) {
                    And -> fromValue && toValue
                    Or -> fromValue || toValue
                    Xor -> fromValue != toValue
                }

                val outputName = outputNames.getOrDefault(output, output)
                result[outputName] = outputValue
            }
        }

        return result
    }

    private fun List<Wire>.decimal(startingWith: Char): Long {
        return filter { it.first.startsWith(startingWith) }
            .sortedByDescending { it.first }
            .fold(0L) { acc, (_, value) -> (acc shl 1) + if (value) 1 else 0 }
    }

    private fun wiresInSwap(): List<Connection> {
        val first = connections.filterNot { connection ->
            connection.xorProduces(outputPrefix = 'z', lastOutput = "z45")
        }

        val second = connections.filterNot { connection ->
            connection.xorConsumes(outputPrefix = 'z')
        }

        val outputNames = first.swapOutputsWith(second, outputPrefix = 'z')

        val trailingZeroes = outputDecimal(outputNames)
            .countTrailingZeroBits()
            .toString()

        val third = connections.filter { connections ->
            connections.consumersEndWith(trailingZeroes)
        }

        return first + second + third
    }

    private fun outputDecimal(outputNames: Map<String, String>): Long {
        val simulation = simulate(outputNames).toList()

        val x = wires.decimal('x')
        val y = wires.decimal('y')
        val z = simulation.decimal('z')

        return (x + y) xor z
    }

    private fun consumersOf(output: String): List<Connection> {
        return connections.filter { connection -> connection consumes output }
    }

    private fun List<Connection>.swapOutputsWith(other: List<Connection>, outputPrefix: Char): Map<String, String> {
        return buildMap {
            for (a in other) {
                val output = consumersOf(a.output)
                    .findProducerOf(outputPrefix)
                    ?.nextOutput(outputPrefix)
                    ?: continue

                val b = find { connection -> connection.output == output } ?: continue

                this[a.output] = b.output
                this[b.output] = a.output
            }
        }
    }

    private fun List<Connection>.findProducerOf(outputPrefix: Char): Connection? {
        val connection = find { connection ->
            connection.output.startsWith(outputPrefix)
        }

        return if (connection != null) {
            connection
        } else {
            firstNotNullOfOrNull { connection ->
                consumersOf(connection.output).findProducerOf(outputPrefix)
            }
        }
    }

    private infix fun Connection.consumes(output: String): Boolean {
        return from == output || to == output
    }

    private fun Connection.consumersEndWith(suffix: String): Boolean {
        return from.endsWith(suffix) && to.endsWith(suffix)
    }

    private fun Connection.xorProduces(outputPrefix: Char, lastOutput: String): Boolean {
        return if (output.startsWith(outputPrefix)) {
            return operand == Xor || output == lastOutput
        } else {
            true
        }
    }

    private fun Connection.xorConsumes(outputPrefix: Char): Boolean {
        return if (output.startsWith(outputPrefix)) {
            true
        } else if (operand == Xor) {
            return from.isInitialValue() || to.isInitialValue()
        } else {
            true
        }
    }

    private fun Connection.nextOutput(prefix: Char): String {
        val nextOutput = output.drop(1).toInt() - 1
        return prefix + nextOutput.toString()
    }

    private fun String.isInitialValue(): Boolean {
        return startsWith('x') || startsWith('y')
    }
}

