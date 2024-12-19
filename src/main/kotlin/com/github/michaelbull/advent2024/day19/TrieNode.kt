package com.github.michaelbull.advent2024.day19

@JvmInline
value class TrieNode private constructor(private val children: IntArray) {

    private constructor(init: (Int) -> Int) : this(IntArray(CHILD_INDEX_RANGE.size, init))

    init {
        require(children.size == CHILD_INDEX_RANGE.size)
    }

    val isTowel: Boolean
        get() = children[TOWEL_INDEX] == 1

    fun asTowel(): TrieNode {
        return withChild(TOWEL_INDEX, 1)
    }

    fun colorCount(char: Char): Int {
        return children[char.toColorIndex()]
    }

    fun withColorCount(color: Char, count: Int): TrieNode {
        return withChild(color.toColorIndex(), count)
    }

    private fun withChild(index: Int, element: Int): TrieNode {
        return TrieNode {
            if (it == index) element else children[it]
        }
    }

    private fun Char.toColorIndex(): Int {
        return COLORS.indexOf(this)
    }

    companion object {
        private const val COLORS = "wubrg"
        private val TOWEL_INDEX = COLORS.lastIndex + 1
        private val CHILD_INDEX_RANGE = COLORS.indices + TOWEL_INDEX

        val ROOT = TrieNode { 0 }
    }
}
