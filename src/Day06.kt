fun main() {
    fun getChunkStart(input: String, limit: Int): Int {
        val buffer = ArrayDeque<Char>()
        for (i in input.indices) {
            if (i != 0 && buffer.contains(input[i])) {
                do {
                    val first = buffer.removeFirst()
                } while (first != input[i])
            }

            buffer.addLast(input[i])
            if (buffer.size == limit) {
                return i + 1
            }
        }

        return 0
    }

    fun part1(input: String) = getChunkStart(input, 4)
    fun part2(input: String) = getChunkStart(input, 14)

    val testInput = readInput("Day06_test")
    check(part2(testInput.first()) == 19)

    val input = readInput("Day06")
    println(part2(input.first()))
}
