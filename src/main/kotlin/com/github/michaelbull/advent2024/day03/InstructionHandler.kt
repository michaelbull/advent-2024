package com.github.michaelbull.advent2024.day03

object InstructionHandler {

    private var enabled = true

    fun handle(instructions: Sequence<Instruction>): Int {
        enabled = true

        return instructions.sumOf(::handle)
    }

    private fun handle(instruction: Instruction): Int {
        return when (instruction) {
            is MultiplyInstruction -> instruction.handle()
            is ConditionalInstruction -> instruction.handle()
        }
    }

    private fun MultiplyInstruction.handle(): Int {
        return if (enabled) {
            left * right
        } else {
            0
        }
    }

    private fun ConditionalInstruction.handle(): Int {
        enabled = when (this) {
            is DoInstruction -> true
            is DontInstruction -> false
        }

        return 0
    }
}
