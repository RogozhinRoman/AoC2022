fun main() {
    fun getPriority(c: Char): Int {
        return if (c.isLowerCase()) c.code - 'a'.code + 1
        else c.code - 'A'.code + 27
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { it ->
            it.subSequence(0 until it.length / 2)
                .toSet()
                .intersect(it.subSequence(it.length / 2, it.length).toSet())
                .map { getPriority(it) }
                .single()
        }
    }

    fun part2(input: List<String>): Int {
        return input.chunked(3).sumOf { it ->
            it[0].toSet()
                .intersect(it[1].toSet())
                .intersect(it[2].toSet())
                .map { getPriority(it) }
                .single()
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
