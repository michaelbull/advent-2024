package com.github.michaelbull.advent2024.day16

import com.github.michaelbull.advent2024.day16.Day16.part1
import com.github.michaelbull.advent2024.day16.Day16.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day16Test {

    @Test
    fun `example 1`() {
        val input = """
            ###############
            #.......#....E#
            #.#.###.#.###.#
            #.....#.#...#.#
            #.###.#####.#.#
            #.#.#.......#.#
            #.#.#####.###.#
            #...........#.#
            ###.#.#####.#.#
            #...#.....#.#.#
            #.#.#.###.#.#.#
            #.....#...#.#.#
            #.###.#.#.#.#.#
            #S..#.....#...#
            ###############
        """

        assertEquals(7036, Day16.solve(::part1, input))
    }

    @Test
    fun `example 2`() {
        val input = """
            #################
            #...#...#...#..E#
            #.#.#.#.#.#.#.#.#
            #.#.#.#...#...#.#
            #.#.#.#.###.#.#.#
            #...#.#.#.....#.#
            #.#.#.#.#.#####.#
            #.#...#.#.#.....#
            #.#.#####.#.###.#
            #.#.#.......#...#
            #.#.###.#####.###
            #.#.#...#.....#.#
            #.#.#.#####.###.#
            #.#.#.........#.#
            #.#.#.#########.#
            #S#.............#
            #################
        """

        assertEquals(11048, Day16.solve(::part1, input))
    }

    @Test
    fun `example 3`() {
        val input = """
            ###############
            #.......#....E#
            #.#.###.#.###.#
            #.....#.#...#.#
            #.###.#####.#.#
            #.#.#.......#.#
            #.#.#####.###.#
            #...........#.#
            ###.#.#####.#.#
            #...#.....#.#.#
            #.#.#.###.#.#.#
            #.....#...#.#.#
            #.###.#.#.#.#.#
            #S..#.....#...#
            ###############
        """

        assertEquals(45, Day16.solve(::part2, input))
    }

    @Test
    fun `example 4`() {
        val input = """
            #################
            #...#...#...#..E#
            #.#.#.#.#.#.#.#.#
            #.#.#.#...#...#.#
            #.#.#.#.###.#.#.#
            #...#.#.#.....#.#
            #.#.#.#.#.#####.#
            #.#...#.#.#.....#
            #.#.#####.#.###.#
            #.#.#.......#...#
            #.#.###.#####.###
            #.#.#...#.....#.#
            #.#.#.#####.###.#
            #.#.#.........#.#
            #.#.#.#########.#
            #S#.............#
            #################
        """

        assertEquals(64, Day16.solve(::part2, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(85396, Day16.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(428, Day16.solve(::part2))
    }
}
