package com.github.michaelbull.advent2024.day25

import com.github.michaelbull.advent2024.day25.Day25.part1
import kotlin.test.Test
import kotlin.test.assertEquals

class Day25Test {

    @Test
    fun `example 1`() {
        val input = """
            #####
            .####
            .####
            .####
            .#.#.
            .#...
            .....

            #####
            ##.##
            .#.##
            ...##
            ...#.
            ...#.
            .....

            .....
            #....
            #....
            #...#
            #.#.#
            #.###
            #####

            .....
            .....
            #.#..
            ###..
            ###.#
            ###.#
            #####

            .....
            .....
            .....
            #....
            #.#..
            #.#.#
            #####
        """

        assertEquals(3, Day25.solve(::part1, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(2978, Day25.solve(::part1))
    }
}
