package com.github.michaelbull.advent2024.day18

import com.github.michaelbull.advent2024.day18.Day18.part1
import com.github.michaelbull.advent2024.day18.Day18.part2
import com.github.michaelbull.advent2024.math.Vector2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day18Test {

    @Test
    fun `example 1`() {
        val input = """
            5,4
            4,2
            4,5
            3,0
            2,1
            6,3
            2,4
            1,5
            0,6
            3,3
            2,6
            5,1
            1,2
            5,5
            2,5
            6,5
            1,4
            0,4
            6,4
            1,1
            6,1
            1,0
            0,5
            1,6
            2,0
        """

        val memorySpace = Day18.parse(input)

        assertEquals(22, memorySpace.minStepsToExit(corrupted = 12, coordinates = 6))
    }

    @Test
    fun `example 2`() {
        val input = """
            5,4
            4,2
            4,5
            3,0
            2,1
            6,3
            2,4
            1,5
            0,6
            3,3
            2,6
            5,1
            1,2
            5,5
            2,5
            6,5
            1,4
            0,4
            6,4
            1,1
            6,1
            1,0
            0,5
            1,6
            2,0
        """

        val memorySpace = Day18.parse(input)

        assertEquals(Vector2(6, 1), memorySpace.firstUnreachableByte(coordinates = 6))
    }

    @Test
    fun `answer 1`() {
        assertEquals("324", Day18.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals("46,23", Day18.solve(::part2))
    }
}
