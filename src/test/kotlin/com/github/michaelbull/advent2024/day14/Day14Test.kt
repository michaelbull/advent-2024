package com.github.michaelbull.advent2024.day14

import com.github.michaelbull.advent2024.day14.Day14.part1
import com.github.michaelbull.advent2024.day14.Day14.part2
import com.github.michaelbull.advent2024.math.Vector2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day14Test {

    @Test
    fun `example 1`() {
        val input = """
            p=0,4 v=3,-3
            p=6,3 v=-1,-3
            p=10,3 v=-1,2
            p=2,0 v=2,-1
            p=0,0 v=1,3
            p=3,0 v=-2,-2
            p=7,6 v=-1,-3
            p=3,0 v=-1,-2
            p=9,3 v=2,3
            p=7,3 v=-1,2
            p=2,4 v=2,-3
            p=9,5 v=-3,-3
        """

        val map = Day14.parse(input)

        assertEquals(12, map.safetyFactor(Vector2(11, 7), 100))
    }

    @Test
    fun `answer 1`() {
        assertEquals(229069152, Day14.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(7383, Day14.solve(::part2))
    }
}
