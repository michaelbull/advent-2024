package com.github.michaelbull.advent2024.day19

fun Sequence<String>.toTowel(): Towel {
    val iterator = iterator()
    val patterns = iterator.next().split(", ")

    require(iterator.next().isEmpty())

    val designs = iterator.asSequence().toList()

    return Towel(patterns, designs)
}

class Towel(
    private val patterns: List<String>,
    private val designs: List<String>,
) {

    fun countWaysToDesign(): Long {
        val patterns = patternsTrie()

        return designs.count { design ->
            patterns.canMake(design)
        }.toLong()
    }

    fun sumWaysToDesign(): Long {
        val patterns = patternsTrie()

        return designs.sumOf { design ->
            patterns.waysToMake(design)
        }
    }

    private fun patternsTrie(): List<TrieNode> {
        val trie = mutableListOf(TrieNode.ROOT)

        for (pattern in patterns) {
            var nodeIndex = 0

            for (color in pattern) {
                val node = trie[nodeIndex]
                val count = node.colorCount(color)

                if (count == 0) {
                    trie[nodeIndex] = node.withColorCount(color, trie.size)
                    trie += TrieNode.ROOT
                }

                nodeIndex = if (count > 0) count else trie.lastIndex
            }

            trie[nodeIndex] = trie[nodeIndex].asTowel()
        }

        return trie
    }

    private fun List<TrieNode>.canMake(design: String): Boolean {
        return waysToMake(design) > 0L
    }

    private fun List<TrieNode>.waysToMake(design: String): Long {
        val ways = MutableList(design.length + 1) {
            if (it == 0) 1L else 0L
        }

        for (left in design.indices) {
            if (ways[left] > 0) {
                var node = first()

                for (right in design.indices.drop(left)) {
                    val color = design[right]
                    val count = node.colorCount(color)

                    node = if (count > 0) get(count) else break

                    if (node.isTowel) {
                        ways[right + 1] += ways[left]
                    }
                }
            }
        }

        return ways.last()
    }
}
