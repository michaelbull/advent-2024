package com.github.michaelbull.advent2024.day13

import com.github.michaelbull.advent2024.day13.Day13.part1
import com.github.michaelbull.advent2024.day13.Day13.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day13Test {

    @Test
    fun `example 1`() {
        val input = """
            Button A: X+94, Y+34
            Button B: X+22, Y+67
            Prize: X=8400, Y=5400

            Button A: X+26, Y+66
            Button B: X+67, Y+21
            Prize: X=12748, Y=12176

            Button A: X+17, Y+86
            Button B: X+84, Y+37
            Prize: X=7870, Y=6450

            Button A: X+69, Y+23
            Button B: X+27, Y+71
            Prize: X=18641, Y=10279
        """

        assertEquals(480, Day13.solve(::part1, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(35729, Day13.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(88584689879723, Day13.solve(::part2))
    }
}
