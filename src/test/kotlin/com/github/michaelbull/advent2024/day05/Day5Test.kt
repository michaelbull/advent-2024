package com.github.michaelbull.advent2024.day05

import com.github.michaelbull.advent2024.day05.Day5.part1
import com.github.michaelbull.advent2024.day05.Day5.part2
import kotlin.test.Test
import kotlin.test.assertEquals

class Day5Test {

    @Test
    fun `example 1`() {
        val input = """
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13

            75,47,61,53,29
            97,61,53,29,13
            75,29,13
            75,97,47,61,53
            61,13,29
            97,13,75,29,47
        """

        assertEquals(143, Day5.solve(::part1, input))
    }

    @Test
    fun `example 2`() {
        val input = """
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13

            75,47,61,53,29
            97,61,53,29,13
            75,29,13
            75,97,47,61,53
            61,13,29
            97,13,75,29,47
        """

        assertEquals(123, Day5.solve(::part2, input))
    }

    @Test
    fun `answer 1`() {
        assertEquals(3608, Day5.solve(::part1))
    }

    @Test
    fun `answer 2`() {
        assertEquals(4922, Day5.solve(::part2))
    }
}
