package com.github.michaelbull.advent2024.day17

import com.github.michaelbull.advent2024.day17.Day17
import com.github.michaelbull.advent2024.day17.Day17.part1
import com.github.michaelbull.advent2024.day17.Day17.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day17Test {

    @Test
    fun `example 1`() {
        val input = """
            Register A: 729
            Register B: 0
            Register C: 0

            Program: 0,1,5,4,3,0
        """

        assertEquals("4,6,3,5,6,3,5,2,1,0", Day17.solve(::part1, input))
    }

    @Test
    fun `example 2`() {
        val input = """
            Register A: 2024
            Register B: 0
            Register C: 0

            Program: 0,3,5,4,3,0
        """

        assertEquals("117440", Day17.solve(::part2, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals("5,1,3,4,3,7,2,1,7", Day17.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals("216584205979245", Day17.solve(::part2))
    }
}
