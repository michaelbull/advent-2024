package com.github.michaelbull.advent2024.day20

import com.github.michaelbull.advent2024.day20.Day20.part1
import com.github.michaelbull.advent2024.day20.Day20.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day20Test {

    @Test
    fun `example 1`() {
        val input = """
            ###############
            #...#...#.....#
            #.#.#.#.#.###.#
            #S#...#.#.#...#
            #######.#.#.###
            #######.#.#...#
            #######.#.###.#
            ###..E#...#...#
            ###.#######.###
            #...###...#...#
            #.#####.#.###.#
            #.#...#.#.#...#
            #.#.#.#.#.#.###
            #...#...#...###
            ###############
        """

        val racetrack = Day20.parse(input)

        assertEquals(1, racetrack.countSaves(64, 2))
    }

    @Test
    fun `example 2`() {
        val input = """
            ###############
            #...#...#.....#
            #.#.#.#.#.###.#
            #S#...#.#.#...#
            #######.#.#.###
            #######.#.#...#
            #######.#.###.#
            ###..E#...#...#
            ###.#######.###
            #...###...#...#
            #.#####.#.###.#
            #.#...#.#.#...#
            #.#.#.#.#.#.###
            #...#...#...###
            ###############
        """

        val racetrack = Day20.parse(input)

        assertEquals(3, racetrack.countSaves(76, 20))
    }

    @Test
    fun `answer 1`() {
        assertEquals(1417, Day20.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(1014683, Day20.solve(::part2))
    }
}
