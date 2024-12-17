package com.github.michaelbull.advent2024.day17

fun Sequence<String>.toComputer(): Computer {
    val iterator = iterator()
    val registers = iterator.nextRegisterFile()

    require(iterator.next().isEmpty())

    val program = iterator.next().toProgram()

    return Computer(registers, program)
}

data class Computer(
    private val registers: RegisterFile,
    private val program: List<Int>,
    private val instructionPointer: Int = 0,
    private val output: Int = NO_OUTPUT,
) {

    private val nextInstructionPointer: Int
        get() = instructionPointer + INSTRUCTION_LENGTH

    private val opcode: Opcode
        get() = program[instructionPointer].toOpcode()

    private val operand: Int
        get() = program[instructionPointer + 1]

    private val comboOperand: Int
        get() = when (operand) {
            in 0..3 -> operand
            4 -> registers.a.toOperand()
            5 -> registers.b.toOperand()
            6 -> registers.c.toOperand()
            7 -> throw IllegalArgumentException("$operand is reserved")
            else -> throw IllegalArgumentException("operand must be in $OPERAND_RANGE, but was $operand")
        }

    fun outputs(): Sequence<Int> {
        return run()
            .filter(Computer::hasOutput)
            .map(Computer::output)
    }

    fun minReplicatingRegisterA(): Long {
        return replicatingRegisterA().min()
    }

    private fun run(): Sequence<Computer> {
        return generateSequence(this) { computer ->
            if (computer.hasNext()) {
                computer.step()
            } else {
                null
            }
        }
    }

    private fun hasNext(): Boolean {
        return instructionPointer < program.size
    }

    private fun hasOutput(): Boolean {
        return output != NO_OUTPUT
    }

    private fun step() = when (opcode) {
        ADV -> nextRegisters(adv())
        BDV -> nextRegisters(bdv())
        CDV -> nextRegisters(cdv())
        BXL -> nextRegisters(bxl())
        BXC -> nextRegisters(bxc())
        BST -> nextRegisters(bst())
        JNZ -> jumpIf { registers.a != 0L }
        OUT -> nextOutput()
    }

    private fun nextRegisters(registers: RegisterFile): Computer {
        return copy(
            registers = registers,
            instructionPointer = nextInstructionPointer,
            output = NO_OUTPUT,
        )
    }

    private fun adv(): RegisterFile {
        return registers.copy(a = registers.a ushr comboOperand)
    }

    private fun bdv(): RegisterFile {
        return registers.copy(b = registers.a ushr comboOperand)
    }

    private fun cdv(): RegisterFile {
        return registers.copy(c = registers.a ushr comboOperand)
    }

    private fun bxl(): RegisterFile {
        return registers.copy(b = registers.b xor operand.toLong())
    }

    private fun bxc(): RegisterFile {
        return registers.copy(b = registers.b xor registers.c)
    }

    private fun bst(): RegisterFile {
        return registers.copy(b = comboOperand.toLong())
    }

    private inline fun jumpIf(predicate: () -> Boolean): Computer {
        return copy(
            instructionPointer = if (predicate()) operand else nextInstructionPointer,
            output = NO_OUTPUT,
        )
    }

    private fun nextOutput(): Computer {
        return copy(
            instructionPointer = nextInstructionPointer,
            output = comboOperand.toInt(),
        )
    }

    private fun replicatingRegisterA(): List<Long> {
        return program.asReversed().fold(listOf(0L)) { accumulator, element ->
            accumulator.flatMap { candidate ->
                candidate aRegisterProducersOf element
            }
        }
    }

    private infix fun Long.aRegisterProducersOf(element: Int): List<Long> {
        return registerCandidates().filter { candidate ->
            candidate aRegisterProduces element
        }
    }

    private infix fun Long.aRegisterProduces(element: Int): Boolean {
        return withRegisterA(this).hasOutput(element)
    }

    private fun Long.registerCandidates(): List<Long> {
        return OPERAND_RANGE.map { bits ->
            (this shl 3) or bits
        }
    }

    private fun withRegisterA(a: Long): Computer {
        return copy(registers = registers.copy(a = a))
    }

    private fun hasOutput(output: Int): Boolean {
        return outputs().first() == output
    }

    private fun Long.toOperand(): Int {
        return (this and OPERAND_RANGE.last).toInt()
    }

    private companion object {
        private const val INSTRUCTION_LENGTH = 2
        private const val NO_OUTPUT = -1
        private val OPERAND_RANGE = 0L..7L
    }
}
