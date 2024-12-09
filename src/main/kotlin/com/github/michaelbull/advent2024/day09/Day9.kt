package com.github.michaelbull.advent2024.day09

import com.github.michaelbull.advent2024.Puzzle

object Day9 : Puzzle<FileSystem, Long>(day = 9) {

    override fun parse(lines: Sequence<String>): FileSystem {
        return lines.first().toDiskMap()
    }

    override fun solutions() = listOf(
        ::part1,
        ::part2,
    )

    fun part1(input: FileSystem): Long {
        input.compact()
        return input.blocksChecksum()
    }

    fun part2(input: FileSystem): Long {
        input.defragment()
        return input.filesChecksum()
    }
}

