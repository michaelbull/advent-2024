package com.github.michaelbull.advent2024.day09

fun String.toDiskMap(): FileSystem {
    val blocks = mutableListOf<Block>()
    val files = mutableListOf<File>()
    val spaces = mutableListOf<Space>()

    var fileId = 0L
    var position = 0

    for ((index, char) in withIndex()) {
        val digit = char.digitToInt()
        val file = index % 2 == 0

        if (file) {
            val file = File(
                id = fileId,
                startPosition = position,
                size = digit
            )

            repeat(digit) {
                blocks += file
            }

            files += file
            fileId++
        } else {
            val space = Space(
                startPosition = position,
                size = digit
            )

            repeat(digit) {
                blocks += space
            }

            spaces += space
        }

        position += digit
    }

    return FileSystem(
        blocks = blocks,
        files = files,
        spaces = spaces
    )
}

data class FileSystem(
    val blocks: MutableList<Block>,
    val files: List<File>,
    val spaces: List<Space>,
) {

    fun compact() {
        var head = 0
        var tail = blocks.lastIndex

        while (head < tail) {
            if (blocks[head] is File) {
                head += 1
            } else if (blocks[tail] is Space) {
                tail -= 1
            } else {
                blocks.swapByIndex(head, tail)
                head += 1
                tail -= 1
            }
        }
    }

    fun defragment() {
        files.reversed().forEach(::defragment)
    }

    private fun defragment(file: File) {
        findSpaceFor(file)?.let { space ->
            if (file.canOccupy(space)) {
                file.occupy(space)
            }
        }
    }

    private fun findSpaceFor(file: File): Space? {
        return spaces.find { space -> space.canFit(file) }
    }

    private fun Space.canFit(file: File): Boolean {
        return size >= file.size
    }

    private fun File.canOccupy(space: Space): Boolean {
        return startPosition >= space.startPosition
    }

    private fun File.occupy(space: Space) {
        space.size -= size
        startPosition = space.startPosition
        space.startPosition += size
    }

    fun blocksChecksum(): Long {
        return blocks.withIndex().sumOf { (index, block) ->
            index * block.id
        }
    }

    fun filesChecksum(): Long {
        return files.sumOf { file ->
            file.positionRange.sumOf { position ->
                position * file.id
            }
        }
    }
}

private fun <T> MutableList<T>.swapByIndex(a: Int, b: Int) {
    this[a] = this[b].also {
        this[b] = this[a]
    }
}
